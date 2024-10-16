package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int maxPax;

    @Column(nullable = true)
    private String photo = "resources/image/fullLogo.webp";
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    
    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EventRegistration> eventRegistrations;
    
    @ManyToOne
    @JoinColumn(name = "organizerId")
    private UserAccount organizer;

    public Event() {
    }

    public Event(String title, String location, String description, int maxPax, Date deadline, Date eventDate) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.deadline = deadline;
        this.eventDate = eventDate;
        this.maxPax = maxPax;
    }
    
    public int remainingPax() {
        return this.maxPax - this.eventRegistrations.size();
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public UserAccount getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserAccount organizer) {
        this.organizer = organizer;
    }

    public List<EventRegistration> getEventRegistrations() {
        return eventRegistrations;
    }

    public void setEventRegistrations(List<EventRegistration> eventRegistrations) {
        this.eventRegistrations = eventRegistrations;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the eventId fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Event id(%d) %s", eventId, title);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
