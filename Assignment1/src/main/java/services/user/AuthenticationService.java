package services.user;

import model.User;
import model.validation.Notification;
import repositories.EntityNotFoundException;
import repositories.user.AuthenticationException;

import java.sql.SQLException;

/**
 * Created by Alex on 11/03/2017.
 */
public interface AuthenticationService {

    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password) throws AuthenticationException;
    Long getIdByUsername(String  name) throws SQLException, EntityNotFoundException;
    boolean logout(User user);

}
