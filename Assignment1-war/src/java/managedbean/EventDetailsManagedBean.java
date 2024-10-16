package managedbean;

import entity.Event;
import entity.EventRegistration;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import session.stateless.EventRegistrationSessionBeanLocal;
import session.stateless.EventSessionBeanLocal;

@Named(value = "eventDetailsManagedBean")
@ViewScoped
public class EventDetailsManagedBean implements Serializable {

    @EJB
    private EventRegistrationSessionBeanLocal eventRegistrationSessionBean;

    @Inject
    private UserManagedBean userManagedBean;

    @EJB
    private EventSessionBeanLocal eventSessionBean;

    private Event event;
    private String title;
    private String location;
    private String description;
    private String photo;
    private int maxPax;
    private int remainingPax;
    private Date deadline;
    private Date eventDate;

    private List<EventRegistration> attendees;
    private String searchString;

    private EventRegistration eventRegistration;

    private boolean login = false;
    private boolean registered = false;

    private Part uploadedfile;
    private String filename = "";

    @PostConstruct
    public void init() {
        String eventIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");
        if (eventIdParam != null) {
            //event details
            long eventId = Long.parseLong(eventIdParam);
            setEvent(eventSessionBean.getEventById(eventId));
            setTitle(getEvent().getTitle());
            setLocation(getEvent().getLocation());
            setDescription(getEvent().getDescription());
            setMaxPax(getEvent().getMaxPax());
            setDeadline(getEvent().getDeadline());
            setEventDate(getEvent().getEventDate());
            setPhoto(getEvent().getPhoto());

            //list of registered atteendees
            setAttendees(eventRegistrationSessionBean.getEventRegistrationByEvent(eventId));
            try {
                setRemainingPax(maxPax - attendees.size());
            } catch (Exception e) {
                setRemainingPax(maxPax);
            }

            if (searchString != null && searchString != "") {
                setAttendees(eventRegistrationSessionBean.getEventRegistrationByEvent(eventId, searchString));
            }

            try {
                userManagedBean.getUserId();
                setLogin(true);
            } catch (Exception e) {
            }

            try {
                //attendee
                if (!isOrganizer()) {
                    EventRegistration eventRegistration = eventRegistrationSessionBean.getEventRegistrationByAttendee(userManagedBean.getUserId(), eventId);
                    System.out.println(eventRegistration);
                    if (eventRegistration != null) {
                        setEventRegistration(eventRegistration);
                        setRegistered(true);
                    }
                }
            } catch (Exception e) {
                System.out.println("not login");
            }
        }
    }

    public void upload() throws IOException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        //get the deployment path
        String UPLOAD_DIRECTORY = ctx.getRealPath("/") + "upload/";
        System.out.println("#UPLOAD_DIRECTORY : " + UPLOAD_DIRECTORY);

        //debug purposes
        setFilename(Paths.get(getUploadedfile().getSubmittedFileName()).getFileName().toString());
        System.out.println("filename: " + getFilename());
        //---------------------

        //replace existing file
        Path path = Paths.get(UPLOAD_DIRECTORY + getFilename());
        InputStream bytes = getUploadedfile().getInputStream();
        Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

        setPhoto("upload/" + getFilename());
        event.setPhoto("upload/" + getFilename());
        eventSessionBean.updateEvent(event);
    }

    public void registerEvent() {
        eventRegistrationSessionBean.createEventRegistration(userManagedBean.getUserId(), getEventId());
        init();
    }

    public void unregisterEvent() {
        eventRegistrationSessionBean.deleteEventRegistration(eventRegistration);
        init();
    }

    public void unregisterEvent(EventRegistration er) {
        eventRegistrationSessionBean.deleteEventRegistration(er);
        init();
    }

    public EventDetailsManagedBean() {
    }

    public void handleSearch() {
        init();
    }

    public void updateEvent() {
        getEvent().setTitle(title);
        getEvent().setLocation(location);
        getEvent().setDescription(description);
        getEvent().setMaxPax(maxPax);
        getEvent().setDeadline(deadline);
        getEvent().setEventDate(eventDate);
        eventSessionBean.updateEvent(getEvent());
    }

    public void updateAttendance(EventRegistration er) {
        eventRegistrationSessionBean.updateEventRegistration(er);
    }

    public long getEventId() {
        return this.getEvent().getEventId();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public Date getDeadline() {
        return deadline;
    }

    public boolean isOrganizer() {
        try {
            return userManagedBean.getUserId() == getEvent().getOrganizer().getUserAccountId();
        } catch (Exception e) {
            return false;
        }
    }

    public List<EventRegistration> getAttendeeRegistered() {
        return attendees;
    }

    public void setAttendees(List<EventRegistration> attendeeRegistered) {
        this.attendees = attendeeRegistered;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public int getRemainingPax() {
        return remainingPax;
    }

    public void setRemainingPax(int remainingPax) {
        this.remainingPax = remainingPax;
    }

    public EventRegistration getEventRegistration() {
        return eventRegistration;
    }

    public void setEventRegistration(EventRegistration eventRegistration) {
        this.eventRegistration = eventRegistration;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean showButton() {
        return this.login && !isOrganizer();
    }

    public boolean canRegister() {
        return new Date().before(deadline) && maxPax - attendees.size() > 0;
    }

    public boolean canUnregister() {
        return new Date().before(eventDate);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Part getUploadedfile() {
        return uploadedfile;
    }

    public void setUploadedfile(Part uploadedfile) {
        this.uploadedfile = uploadedfile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
