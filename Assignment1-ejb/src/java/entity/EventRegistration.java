package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean attented = false;
    
    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;
    
    @ManyToOne
    @JoinColumn(name = "userAccountId")
    private UserAccount userAccount;

    public EventRegistration() {
    }
    
    public Long getId() {
        return id;
    }

    public boolean isAttented() {
        return attented;
    }

    public void setAttented(boolean attented) {
        this.attented = attented;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    public long getUserAccountId() {
        return this.userAccount.getUserAccountId();
    }
    
    public String getName() {
        return this.userAccount.getName();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventRegistration)) {
            return false;
        }
        EventRegistration other = (EventRegistration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EventRegistration[ id=" + id + " ]";
    }
    
}
