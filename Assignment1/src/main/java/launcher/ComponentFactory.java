package launcher;


import controllers.LoginController;
import database.DataBaseConnectionFactory;
import database.JDBConnectionWrapper;
import repositories.account.AccountRepository;
import repositories.account.AccountRepositoryMySQL;
import repositories.bill.BillRepository;
import repositories.bill.BillRepositoryMySQL;
import repositories.client.ClientRepository;
import repositories.client.ClientRepositoryMySQL;
import repositories.security.RightsRolesRepository;
import repositories.security.RightsRolesRepositoryMySQL;
import repositories.transaction.TransactionRepository;
import repositories.transaction.TransactionRepositoryMySQL;
import repositories.user.UserRepository;
import repositories.user.UserRepositoryMySQL;
import services.AdminOperations.AdminService;
import services.AdminOperations.AdminServiceImpl;
import services.account.AccountService;
import services.account.AccountServiceImpl;
import services.bill.BillService;
import services.bill.BillServiceImpl;
import services.client.ClientService;
import services.client.ClientServiceImpl;
import services.transaction.TransactionService;
import services.transaction.TransactionServiceImpl;
import services.user.AuthenticationService;
import services.user.AuthenticationServiceMySQL;
import view.LoginView;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {


    private final LoginView loginView;

    private final LoginController loginController;
    private static AccountRepository accountRepository;
    private static BillRepository billRepository;
    private static ClientRepository clientRepository;
    private static TransactionRepository transactionRepository;
    private static AuthenticationService authenticationService;
    private static UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AdminService adminService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final BillService billService;
    private final TransactionService transactionService;


    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        JDBConnectionWrapper connectionWrapper = new DataBaseConnectionFactory().getConnectionWrapper(componentsForTests);
        Connection connection=connectionWrapper.getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.loginView = new LoginView();
        this.transactionRepository=new TransactionRepositoryMySQL(connectionWrapper);
        this.billRepository=new BillRepositoryMySQL(connectionWrapper);
        this.clientRepository=new ClientRepositoryMySQL(connectionWrapper);
        this.accountRepository=new AccountRepositoryMySQL(connectionWrapper);
        this.clientService=new ClientServiceImpl(clientRepository);
        this.accountService=new AccountServiceImpl(accountRepository);
        this.billService=new BillServiceImpl(billRepository,accountRepository);
        this.transactionService=new TransactionServiceImpl(transactionRepository,accountRepository);

        this.adminService=new AdminServiceImpl(billRepository,transactionRepository,authenticationService);
        this.loginController = new LoginController(loginView, authenticationService,adminService,clientService,accountService,transactionService,billService);
         }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }


    public LoginController getLoginController() {
        return loginController;
    }
}
