package repositories.security;

import model.Right;
import model.Role;
import model.User;
import repositories.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface RightsRolesRepository {
    void addRole(String role);

    void addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    Right findRightByTitle(String right);

    void addRolesToUser(User user, List<Role> roles);

    List<Role> findRolesForUser(Long userId);

    Long getIdByName(String  name) throws SQLException, EntityNotFoundException;


    void addRoleRight(Long roleId, Long rightId);

}
