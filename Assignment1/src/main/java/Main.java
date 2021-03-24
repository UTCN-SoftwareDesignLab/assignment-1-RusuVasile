import com.itextpdf.text.DocumentException;
import database.DataBaseConnectionFactory;
import repositories.EntityNotFoundException;
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
import services.bill.BillService;
import services.bill.BillServiceImpl;
import services.transaction.TransactionService;
import services.transaction.TransactionServiceImpl;
import services.user.AuthenticationService;
import services.user.AuthenticationServiceMySQL;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    private static  ClientRepository clientRepository;
    private static BillRepository billRepository;
    private static AccountRepository accountRepository;
    private static TransactionRepository transactionRepository;
    public static final String TEST_USERNAME = "test@username.com";
    public static final String TEST_PASSWORD = "TestPassword1@";
    private static AuthenticationService authenticationService;
    private static UserRepository userRepository;
    public static void main(String[] args) throws EntityNotFoundException, ParseException, SQLException, FileNotFoundException, DocumentException {
        clientRepository=new ClientRepositoryMySQL( new DataBaseConnectionFactory().getConnectionWrapper(true));
        accountRepository=new AccountRepositoryMySQL( new DataBaseConnectionFactory().getConnectionWrapper(true));
        transactionRepository=new TransactionRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
        Connection connection = new DataBaseConnectionFactory().getConnectionWrapper(true).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        billRepository=new BillRepositoryMySQL( new DataBaseConnectionFactory().getConnectionWrapper(true));
        authenticationService = new AuthenticationServiceMySQL(
                userRepository,
                rightsRolesRepository
        );
        TransactionService transactionService=new TransactionServiceImpl(transactionRepository,accountRepository);
        BillService billService=new BillServiceImpl(billRepository,accountRepository);
        AdminService adminService=new AdminServiceImpl(billRepository,transactionRepository,authenticationService);
        adminService.createEmployee(TEST_USERNAME,TEST_PASSWORD);
     //   System.out.println(Long.parseLong("Account nr:123".substring(11)));
        /*
        Client client1=new ClientBuilder().setName("Client1").setAddress("Address1").setCNP(1L).build();
        Client client2=new ClientBuilder().setName("Client2").setAddress("Address2").setCNP(2L).build();
        clientRepository.save(client1);
        clientRepository.save(client2);



        Account account1=new AccountBuilder().setSold(1000).setClientId(1L).setType("Finance").build();
        Account account2=new AccountBuilder().setSold(3000).setClientId(2L).setType("Savings").build();
        accountRepository.save(account1);
        accountRepository.save(account2);


       // authenticationService.register(TEST_USERNAME,TEST_PASSWORD);
        Transaction transaction1=new TransactionBuilder().setValue(1000).setFromId(1L).setToId(2L).setUserId(1L).build();
        Transaction transaction2=new TransactionBuilder().setValue(1200).setFromId(2L).setToId(1L).setUserId(1L).build();
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        System.out.println(transactionRepository.findAll());
           UtilityBill utilityBill1=new UtilityBillBuilder().setValue(500).setDescription("Water").setUserId(1L).setAccountId(1L).build();
        UtilityBill utilityBill2=new UtilityBillBuilder().setValue(550).setDescription("Water").setUserId(1L).setAccountId(2L).build();

        billRepository.save(utilityBill1);
        billRepository.save(utilityBill2);


        System.out.println(transactionRepository.betweenDates(date1,date2,1L));
        System.out.println(billRepository.betweenDates(date1,date2,1L));


        System.out.println(accountRepository.getSoldById(1L));
        accountRepository.addToSoldById(1L,5000);
        System.out.println(accountRepository.getSoldById(1L));
        accountRepository.subFromSoldById(1L,4000);
        System.out.println(accountRepository.getSoldById(1L));
           Client client1=new ClientBuilder().setName("Client1").setAddress("Address1").setCNP(1L).build();
        Client client2=new ClientBuilder().setName("Client2").setAddress("Address2").setCNP(2L).build();
        clientRepository.save(client1);
        clientRepository.save(client2);



        Account account1=new AccountBuilder().setSold(1000).setClientId(11L).setType("Finance").build();
        Account account2=new AccountBuilder().setSold(3000).setClientId(12L).setType("Savings").build();
        accountRepository.save(account1);
        accountRepository.save(account2);


            // authenticationService.register(TEST_USERNAME,TEST_PASSWORD);
        Transaction transaction1=new TransactionBuilder().setValue(1000).setFromId(1L).setToId(2L).setUserId(1L).build();
        Transaction transaction2=new TransactionBuilder().setValue(1200).setFromId(2L).setToId(1L).setUserId(1L).build();
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        Transaction transaction3=new TransactionBuilder().setValue(1000).setFromId(1L).setToId(2L).setUserId(1L).build();
        TransactionService transactionService=new TransactionServiceImpl(transactionRepository,accountRepository);
        transactionService.addTransaction(transaction1);

        Date date1=new Date(110,8,21);
        Date date2=new Date(121,3,21);
        PDFgenerator pdFgenerator=new PDFgenerator(1L,date1,date2,billRepository,transactionRepository);
        pdFgenerator.generateReport();



        UtilityBill utilityBill1=new UtilityBillBuilder().setValue(500).setDescription("Water").setUserId(12L).setAccountId(17L).build();
        UtilityBill utilityBill2=new UtilityBillBuilder().setValue(550).setDescription("Electricity").setUserId(12L).setAccountId(17L).build();

        billService.addBill(utilityBill1);
        billService.addBill(utilityBill2);

         */
      //  Date date1=new Date(110,8,21);
      //  System.out.println(date1);
       // Date date2=new Date(121,3,21);
      //  PDFgenerator pdFgenerator=new PDFgenerator(12L,date1,date2,billRepository,transactionRepository);
      //  pdFgenerator.generateReport();



    }

}
