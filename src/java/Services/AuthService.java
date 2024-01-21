package Services;

import Controle.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@ManagedBean(name = "authService")
@SessionScoped
public class AuthService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean authenticate(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password);

        try {
            User user = query.getSingleResult();
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }

    // (You can add more methods or properties as needed)

}
