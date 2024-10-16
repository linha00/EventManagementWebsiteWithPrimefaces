package session.stateless;

import entity.Event;
import entity.UserAccount;
import exception.WrongDateException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EventSessionBean implements EventSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createEvent(Event e, long userId) throws WrongDateException {
        System.out.println("EventSessionBean.createEvent(" + e.getTitle() + ")");
        if (e.getEventDate().before(new Date())) {
            throw new WrongDateException();
        }
        em.persist(e);
        em.flush();
        UserAccount ua = em.find(UserAccount.class, userId);
        ua.getEventCreated().add(e);
        e.setOrganizer(ua);
    }

    @Override
    public List<Event> findEventByName_organizer(long userId, String name) {
        name = "%" + name + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT e FROM Event e WHERE e.title LIKE :name AND e.organizer.userAccountId = :userId",
                Event.class
        );
        query.setParameter("name", name);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Event> findEventByLocation_organizer(long userId, String location) {
        location = "%" + location + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT e FROM Event e WHERE e.location LIKE :location AND e.organizer.userAccountId = :userId",
                Event.class
        );
        query.setParameter("location", location);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Event> findEventByOrganizer(long userId) {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.organizer.userAccountId = :userId", Event.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Event getEventById(long eventId) {
        return em.find(Event.class, eventId);
    }

    @Override
    public void deleteEvent(long eventId) {
        Event event = em.find(Event.class, eventId);
        em.remove(event);
    }

    @Override
    public void updateEvent(Event event) {
        em.merge(event);
    }

    @Override
    public List<Event> findEventByAttendee(long attendeeId) {
        UserAccount ua = em.find(UserAccount.class, attendeeId);
        TypedQuery<Event> query = em.createQuery(
                "SELECT er.event FROM EventRegistration er WHERE er.userAccount = :userAccount",
                Event.class
        );
        query.setParameter("userAccount", ua);
        return query.getResultList();
    }

    @Override
    public List<Event> findEventByName_attendee(long attendeeId, String name) {
        UserAccount ua = em.find(UserAccount.class, attendeeId);
        name = "%" + name + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT DISTINCT er.event FROM EventRegistration er "
                + "JOIN er.event e "
                + "WHERE er.userAccount = :userAccount AND e.title LIKE :name",
                Event.class
        );
        query.setParameter("userAccount", ua);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Event> findEventByLocation_attendee(long attendeeId, String location) {
        UserAccount ua = em.find(UserAccount.class, attendeeId);
        location = "%" + location + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT er.event FROM EventRegistration er WHERE er.userAccount = :userAccount AND er.event.location LIKE :location",
                Event.class
        );
        query.setParameter("userAccount", ua);
        query.setParameter("location", location);
        return query.getResultList();
    }
    
    @Override
    public List<Event> getAllEvent() {
        TypedQuery<Event> query = em.createQuery(
                "SELECT e FROM Event e",
                Event.class
        );
        return query.getResultList();
    }
    
    @Override
    public List<Event> findEventByName(String name) {
        name = "%" + name + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT e FROM Event e WHERE e.title LIKE :name",
                Event.class
        );
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @Override
    public List<Event> findEventByLocation(String location) {
        location = "%" + location + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT e FROM Event e WHERE e.location LIKE :location",
                Event.class
        );
        query.setParameter("location", location);
        return query.getResultList();
    }
   
    @Override
    public List<Event> findEventByNotLocation(String location) {
        location = "%" + location + "%";
        TypedQuery<Event> query = em.createQuery(
                "SELECT e FROM Event e WHERE e.location NOT LIKE :location",
                Event.class
        );
        query.setParameter("location", location);
        return query.getResultList();
    }

}
