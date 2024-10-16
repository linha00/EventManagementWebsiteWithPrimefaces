package managedbean;

import entity.Event;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import session.stateless.EventSessionBeanLocal;

@Named(value = "eventSearchManagedBean")
@ViewScoped
public class EventSearchManagedBean implements Serializable {

    @EJB
    private EventSessionBeanLocal eventSessionBean;

    private String searchInput;
    private String searchType;

    private List<Event> events;

    public EventSearchManagedBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        searchType = params.get("searchType");
        searchInput = params.get("searchInput");
        if (searchInput == null || searchInput.equals("")) {
            events = eventSessionBean.getAllEvent();
        } else if (searchType.equals("Name")) {
            events = eventSessionBean.findEventByName(searchInput);
        } else {
            if (searchInput.equals("!nus")) {
                events = eventSessionBean.findEventByNotLocation("nus");
            } else {
                events = eventSessionBean.findEventByLocation(searchInput);
            }
        }
    }

    public String clickSearch() {
        // Construct the redirect URL
        String redirectUrl = "searchResults.xhtml?searchType=" + searchType + "&searchInput=" + searchInput;

        // Perform redirection
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
        } catch (IOException e) {
            // Handle IO Exception
        }
        return null; // Necessary to comply with JSF action method return type
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
