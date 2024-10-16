
package managedbean;

import entity.Event;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import session.stateless.EventSessionBeanLocal;

@Named(value = "dataGridViewManagedBean")
@ViewScoped
public class DataGridViewManagedBean implements Serializable {

    @EJB
    private EventSessionBeanLocal eventSessionBean;
    
    private List<Event> eventsInNus;
 
    private List<Event> otherEvents;
    
    private Event selectedEvent;

    @PostConstruct
    public void init() {
        eventsInNus = eventSessionBean.findEventByLocation("nus");
        otherEvents = eventSessionBean.findEventByNotLocation("nus");
        Collections.shuffle(eventsInNus);
        Collections.shuffle(otherEvents);
    }
    
    public DataGridViewManagedBean() {
    }

    public List<Event> getEventsInNus() {
        return eventsInNus;
    }

    public void setEventsInNus(List<Event> eventsInNus) {
        this.eventsInNus = eventsInNus;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public List<Event> getOtherEvents() {
        return otherEvents;
    }

    public void setOtherEvents(List<Event> otherEvents) {
        this.otherEvents = otherEvents;
    }
    
}
