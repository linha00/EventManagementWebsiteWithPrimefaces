package session.stateless;

import entity.UserAccount;
import exception.InvaildLoginException;
import exception.UsernameExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public UserAccount getUserAccount(long id) {
        return em.find(UserAccount.class, id);
    }

    @Override
    public void createUser(UserAccount ua) throws UsernameExistException {
        System.out.println("UserSessionBean.createUser(" + ua.getUsername() + ")");
        if (findUserByUsername(ua.getUsername()) != null) {
            throw new UsernameExistException();
        }
        em.persist(ua);
        
    }
    
    @Override
    public UserAccount login(String username, String password) throws InvaildLoginException {
        UserAccount userAccount = findUserByUsername(username);
        if (userAccount == null) {
            throw new InvaildLoginException();
        } else if (!userAccount.getPassword().equals(password)) {
            throw new InvaildLoginException(); 
        }
        return userAccount;
    }

    @Override
    public void updateUser(UserAccount ua) {
        em.merge(ua);
    }
    
    @Override
    public UserAccount findUserByUsername(String username) {
        TypedQuery<UserAccount> query = em.createQuery("SELECT u FROM UserAccount u WHERE u.username = :username", UserAccount.class);
        query.setParameter("username", username);
        List<UserAccount> userAccounts = query.getResultList();
        if (userAccounts.isEmpty()) {
            return null;
        }
        return userAccounts.get(0);
    }
}
