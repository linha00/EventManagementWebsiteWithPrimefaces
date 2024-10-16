package session.stateless;

import entity.Event;
import entity.EventRegistration;
import entity.UserAccount;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EventRegistrationSessionBean implements EventRegistrationSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createEventRegistration(long attendeeId, long eventId) {
        EventRegistration eventRegistration = new EventRegistration();
        UserAccount ua = em.find(UserAccount.class, attendeeId);
        Event event = em.find(Event.class, eventId);
        em.persist(eventRegistration);
        ua.getEventRegistrations().add(eventRegistration);
        eventRegistration.setUserAccount(ua);
        eventRegistration.setEvent(event);
        event.getEventRegistrations().add(eventRegistration);
    }

    @Override
    public void deleteEventRegistration(EventRegistration eventRegistration) {
        EventRegistration er = em.find(EventRegistration.class, eventRegistration.getId());
        em.remove(er);
    }

    @Override
    public void updateEventRegistration(EventRegistration er) {
        em.merge(er);
    }
    
    @Override
    public EventRegistration getEventRegistrationByAttendee(long attendeeId, long eventId) {
        UserAccount ua = em.find(UserAccount.class, attendeeId);
        if (ua != null) {
            Query query = em.createQuery("SELECT er FROM EventRegistration er LEFT JOIN er.event e WHERE er.userAccount = :userAccount "
                    + "AND e.eventId = :eventId");
            query.setParameter("userAccount", ua);
            query.setParameter("eventId", eventId);

            List<EventRegistration> resultList = query.getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                return resultList.get(0);
            }
        }
        return null;
    }

    @Override
    public List<EventRegistration> getEventRegistrationByEvent(long eventId) {
        Event event = em.find(Event.class, eventId);
        if (event != null) {
            Query query = em.createQuery("SELECT er FROM EventRegistration er WHERE er.event = :event");
            query.setParameter("event", event);
            return query.getResultList();
        } else {
            return Collections.emptyList();
        }
    }
    
    @Override
    public List<EventRegistration> getEventRegistrationByEvent(long eventId, String searchString) {
        Event event = em.find(Event.class, eventId);
        System.out.println(searchString);
        if (event != null) {
            Query query = em.createQuery("SELECT er FROM EventRegistration er LEFT JOIN FETCH er.userAccount ua"
                    + "WHERE er.event = :event AND ua.name LIKE :name");
            query.setParameter("event", event);
            query.setParameter("name", searchString);
            return query.getResultList();
        } else {
            return Collections.emptyList();
        }
    }
}
