package managedbean;

import entity.UserAccount;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.stateless.UserSessionBeanLocal;

@Named(value = "attendeeDetailsManagedBean")
@RequestScoped
public class AttendeeDetailsManagedBean {

    @EJB
    private UserSessionBeanLocal userSessionBean;

    private long selectedAttendeeId;
    private String name;
    private String Email;
    private String contact;


    public void loadSelectedCustomer() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            UserAccount ua = userSessionBean.getUserAccount(selectedAttendeeId);
            setName(ua.getName());
            setEmail(ua.getEmail());
            setContact(ua.getContact());
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to load customer"));
        }
    } //end loadSelectedCustomer

    public AttendeeDetailsManagedBean() {
    }

    public long getSelectedAttendeeId() {
        return selectedAttendeeId;
    }

    public void setSelectedAttendeeId(long selectedAttendeeId) {
        this.selectedAttendeeId = selectedAttendeeId;
        loadSelectedCustomer();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
