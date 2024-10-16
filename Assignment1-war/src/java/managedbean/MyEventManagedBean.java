package managedbean;

import entity.Event;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import session.stateless.EventSessionBeanLocal;

@Named(value = "myEventManagedBean")
@ViewScoped
public class MyEventManagedBean implements Serializable {

    @Inject
    private UserManagedBean userManagedBean;

    @EJB
    private EventSessionBeanLocal eventSessionBean;

    private List<Event> eventCreated;
    private List<Event> eventRegistered;

    private String searchType_created = "NAME";
    private String searchString_created;

    private String searchType_registered = "NAME";
    private String searchString_registered;

    @PostConstruct
    public void init() {
        //event created
        if (searchString_created == null || searchString_created.equals("")) {
            eventCreated = eventSessionBean.findEventByOrganizer(userManagedBean.getUserId());
        } else {
            switch (getSearchType_created()) {
                case "NAME":
                    eventCreated = eventSessionBean.findEventByName_organizer(userManagedBean.getUserId(), searchString_created);
                    break;
                case "LOCATION": {
                    eventCreated = eventSessionBean.findEventByLocation_organizer(userManagedBean.getUserId(), searchString_created);
                    break;
                }
            }
        }

        //event registered
        if (getSearchString_registered() == null || getSearchString_registered().equals("")) {
            eventRegistered = eventSessionBean.findEventByAttendee(userManagedBean.getUserId());
        } else {
            switch (getSearchType_registered()) {
                case "NAME":
                    eventRegistered = eventSessionBean.findEventByName_attendee(userManagedBean.getUserId(), searchString_registered);
                    break;
                case "LOCATION": {
                    eventRegistered = eventSessionBean.findEventByLocation_attendee(userManagedBean.getUserId(), searchString_registered);
                    break;
                }
            }
        }
    }

    public MyEventManagedBean() {
    }

    public void handleSearch() {
        init();
    }

    public String getSearchType_created() {
        return searchType_created;
    }

    public void setSearchType_created(String searchType_created) {
        this.searchType_created = searchType_created;
    }

    public String getSearchString_created() {
        return searchString_created;
    }

    public void setSearchString_created(String searchString_created) {
        this.searchString_created = searchString_created;
    }

    public List<Event> getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(List<Event> eventCreated) {
        this.eventCreated = eventCreated;
    }

    public List<Event> getEventRegistered() {
        return eventRegistered;
    }

    public void setEventRegistered(List<Event> eventRegistered) {
        this.eventRegistered = eventRegistered;
    }

    public String getSearchType_registered() {
        return searchType_registered;
    }

    public void setSearchType_registered(String searchType_registered) {
        this.searchType_registered = searchType_registered;
    }

    public String getSearchString_registered() {
        return searchString_registered;
    }

    public void setSearchString_registered(String searchString_registered) {
        this.searchString_registered = searchString_registered;
    }

}
