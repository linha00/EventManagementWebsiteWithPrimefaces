package session.singleton;

import entity.Event;
import entity.UserAccount;
import enumeration.UserType;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.stateless.EventRegistrationSessionBeanLocal;
import session.stateless.EventSessionBeanLocal;
import session.stateless.UserSessionBeanLocal;

@Singleton
@Startup
@LocalBean
public class StartupSessionBean {

    @EJB
    private EventRegistrationSessionBeanLocal eventRegistrationSessionBean;

    @PersistenceContext
    private EntityManager em;

    @EJB
    private UserSessionBeanLocal userSessionBean;

    @EJB
    private EventSessionBeanLocal eventSessionBean;

    @PostConstruct
    public void PostConstruct() {
        if (em.find(UserAccount.class, 1l) == null) {
            try {
                userSessionBean.createUser(new UserAccount("organizer 1", "user0", "password", "1234 2222", "organizer1@gmail.com"));
                userSessionBean.createUser(new UserAccount("organizer 2", "organizer2", "password", "1234 3333", "organizer2@gmail.com"));
                userSessionBean.createUser(new UserAccount("user 1", "user1", "password", "1111 4444", "user1@gmail.com"));
                userSessionBean.createUser(new UserAccount("user 2", "user2", "password", "2222 1111", "user2@gmail.com"));
            } catch (Exception e) {
                System.out.println("Startup create test user account failed");
                System.out.println(e.toString());
                System.out.println("");
            }
        }

        if (em.find(Event.class, 1l) == null) {
                em.persist(new Event("unable to register test event", "nus", "unable to register event, no organizer", 50, new Date(2000 - 1900, 1, 1), new Date(2010 - 1900, 1, 25)));
                UserAccount ua = em.find(UserAccount.class, 1l);
                createEvent(new Event("test event", "nus", "test event location in nus", 50, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                
                createEvent(new Event("test event 2", "ntu", "test event location in ntu", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 3", "nus Lt19", "test event location in nus LT19", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 4", "nus Com3", "test event location in nus Com 3", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 5", "nus CLB", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 6", "nus asd", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 7", "nus dsa", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 8", "nus cxz", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 9", "nus vvv", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("test event 10", "nus qqq", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                
                createEvent(new Event("ntu event 1", "ntu CLB", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("ntu event 2", "CLB", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("event 2", "ntu LT1", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("ntu event 3", "999", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("event 2", "kent ridge", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("asd event", "asd", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                createEvent(new Event("zoo out", "zoo", "test event location in nus CLB", 501, new Date(2025 - 1900, 1, 1), new Date(2025 - 1900, 1, 25)), ua);
                
                
////                eventRegistrationSessionBean.createEventRegistration(3, 1);
        }
    }

    public void createEvent(Event e, UserAccount ua) {
        System.out.println("createEvent(" + e.getTitle() + ")");
        em.persist(e);
        em.merge(ua);
        em.flush();
        ua.getEventCreated().add(e);
        e.setOrganizer(ua);
    }
}
