package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAccountId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String email;

    @Lob
    @Column(nullable = true)
    private String photo;
    
    @OneToMany(mappedBy = "userAccount", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EventRegistration> eventRegistrations;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Event> eventCreated;

    public UserAccount() {
    }

    public UserAccount(String name, String username, String password, String contact, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.email = email;
    }

     public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public List<Event> getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(List<Event> eventCreated) {
        this.eventCreated = eventCreated;
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
        hash += (userAccountId != null ? userAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userAccountId fields are not set
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.userAccountId == null && other.userAccountId != null) || (this.userAccountId != null && !this.userAccountId.equals(other.userAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("User id(%d) %s", userAccountId, name);
    }

}
