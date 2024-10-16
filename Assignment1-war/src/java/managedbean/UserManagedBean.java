package managedbean;

import entity.UserAccount;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import session.stateless.UserSessionBeanLocal;

@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private UserSessionBeanLocal userSessionBean;

    private UserAccount user;

    private String name = null;
    private String password = null;
    private String contact = null;
    private String email = null;
    private String newPassword = null;

    private Part uploadedfile;
    private String filename = "";
    private String photo;

    public UserManagedBean() {
    }

    public void upload() throws IOException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        //get the deployment path
        String UPLOAD_DIRECTORY = ctx.getRealPath("/") + "upload/";
        System.out.println("#UPLOAD_DIRECTORY : " + UPLOAD_DIRECTORY);

        //debug purposes
        setFilename(Paths.get(uploadedfile.getSubmittedFileName()).getFileName().toString());
        System.out.println("filename: " + getFilename());
        //---------------------

        //replace existing file
        Path path = Paths.get(UPLOAD_DIRECTORY + getFilename());
        InputStream bytes = uploadedfile.getInputStream();
        Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);
        
        setPhoto(UPLOAD_DIRECTORY + getFilename());
        user.setPhoto(UPLOAD_DIRECTORY + getFilename());
        userSessionBean.updateUser(user);
    }

    public void clear() {
        user = null;
        setName("guest");
        setPassword(null);
        setContact(null);
        setEmail(null);
        setNewPassword(null);
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount ua) {
        user = ua;
        setName(ua.getName());
        setContact(ua.getContact());
        setEmail(ua.getEmail());
        setPhoto(ua.getPhoto());
    }

    public void updateProfile() {
        user.setEmail(email);
        user.setName(name);
        user.setContact(contact);
        userSessionBean.updateUser(user);
    }

    public void updatePassword() {
        System.out.println("UserManagedBean.updatePassword()");
        if (password.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userSessionBean.updateUser(user);
        }
    }

    public String getName() {
        return user.getName();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public long getUserId() {
        return user.getUserAccountId();
    }

    public String getContact() {
        return user.getContact();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
