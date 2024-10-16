package managedbean;

import entity.UserAccount;
import exception.InvaildLoginException;
import exception.UsernameExistException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import session.stateless.UserSessionBeanLocal;

@Named(value = "authenticationManagedBean")
@SessionScoped
public class AuthenticationManagedBean implements Serializable {

    @EJB
    private UserSessionBeanLocal userSessionBean;

    @Inject
    private UserManagedBean userManagedBean;

    private boolean login = false;

    private long userId = -1;
    private String name = "guest";
    private String username = null;
    private String password = null;
    private String email = null;
    private String contact = null;

    public AuthenticationManagedBean() {
    }

    public String login() {
        System.out.println("AuthenticationManagedBean.login()");
        System.out.println("username " + username);
        System.out.println("password " + password);
        try {
            UserAccount user = userSessionBean.login(username, password);
            userManagedBean.setUser(user);
            userId = user.getUserAccountId();
            login = true;
            System.out.println("succussful");
            return "index.xhtml?faces-redirect=true";
        } catch (InvaildLoginException e) {
            username = null;
            userId = -1;
            password = null;
            System.out.println("falied");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "Invalid username or password");
            facesContext.addMessage("loginForm:loginButton", facesMessage); 
            return "login.xhtml";
        }
    }

    public String signup() {
        System.out.println("AuthenticationManagedBean.signup()");
        System.out.println("username " + username);
        System.out.println("password " + password);
        System.out.println("name " + name);
        System.out.println("contact " + getContact());
        System.out.println("email " + getEmail());

        UserAccount ua = new UserAccount(name, username, password, contact, email);

        try {
            userSessionBean.createUser(ua);
            System.out.println("succussful");
            UserAccount updatedUa = userSessionBean.findUserByUsername(username);
            userManagedBean.setUser(updatedUa);
            login = true;
            return "index.xhtml?faces-redirect=true";
        } catch (UsernameExistException e) {
            username = null;
            password = null;
            userId = -1;
            System.out.println("falied");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Signup Failed", "Username already existed");
            facesContext.addMessage("signupForm:signupButton", facesMessage); 
            return "signup.xhtml";
        }
    }

    public String logout() {
        username = null;
        password = null;
        userId = -1;
        name = "guest";
        contact = null;
        email = null;
        login = false;
        userManagedBean.clear();
        return "/index.xhtml?faces-redirect=true";
    } //end logout

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}
