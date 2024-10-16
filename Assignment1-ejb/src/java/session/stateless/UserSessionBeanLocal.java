package session.stateless;

import entity.UserAccount;
import exception.InvaildLoginException;
import exception.UsernameExistException;
import javax.ejb.Local;

@Local
public interface UserSessionBeanLocal {

    void createUser(UserAccount ua) throws UsernameExistException ;

    public UserAccount findUserByUsername(String username);

    UserAccount login(String username, String password) throws InvaildLoginException;

    void updateUser(UserAccount ua);

    public UserAccount getUserAccount(long id);
    
}
