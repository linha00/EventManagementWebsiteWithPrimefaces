package managedbean;

import entity.Event;
import exception.WrongDateException;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import session.stateless.EventSessionBeanLocal;

@Named(value = "eventManagedBean")
@RequestScoped
public class EventManagedBean {

    @Inject
    private UserManagedBean userManagedBean;
    
    @EJB
    private EventSessionBeanLocal eventSessionBean;

    private Long eventId = -1l;
    private String title = null;
    private String description = null;
    private String location = null;
    private int maxPax = 0;
    private Date deadline;
    private Date date;

    public EventManagedBean() {
    }

    public String createEvent() {
        System.out.println("EventManagedBean.createEvent()");
        System.out.println("title " + title);
        System.out.println("description " + description);
        System.out.println("location " + location);
        System.out.println("maxPax " + getMaxPax());
        System.out.println("deadline " + deadline);
        System.out.println("date " + date);

        Event e = new Event(title, location, description, getMaxPax(), deadline, date);
        try {
            eventSessionBean.createEvent(e, userManagedBean.getUserId());
            return "myEvent.xhtml";
        } catch (WrongDateException ex) {
            System.out.println("falied");
            return "createEvent.xhtml";
        }
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
