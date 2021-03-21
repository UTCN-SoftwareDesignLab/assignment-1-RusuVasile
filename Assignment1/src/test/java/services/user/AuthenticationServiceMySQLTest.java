package services.user;

import database.DataBaseConnectionFactory;
import model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.security.RightsRolesRepository;
import repositories.security.RightsRolesRepositoryMySQL;
import repositories.user.AuthenticationException;
import repositories.user.UserRepository;
import repositories.user.UserRepositoryMySQL;

import java.sql.Connection;

class AuthenticationServiceMySQLTest {

    public static final String TEST_USERNAME = "test@username.com";
    public static final String TEST_PASSWORD = "TestPassword1@";
    private static AuthenticationService authenticationService;
    private static UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        Connection connection = new DataBaseConnectionFactory().getConnectionWrapper(true).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        authenticationService = new AuthenticationServiceMySQL(
                userRepository,
                rightsRolesRepository
        );
    }

    @Test
    void register() {
        userRepository.removeAll();
        Assert.assertTrue(
                authenticationService.register(TEST_USERNAME, TEST_PASSWORD).getResult()
        );
        userRepository.removeAll();
    }

    @Test
    void login() throws AuthenticationException {

        authenticationService.register(TEST_USERNAME, TEST_PASSWORD).getResult();
        User user = authenticationService.login(TEST_USERNAME, TEST_PASSWORD).getResult();

        Assert.assertNotNull(user);
    }

    @Test
    void logout() {
    }
}