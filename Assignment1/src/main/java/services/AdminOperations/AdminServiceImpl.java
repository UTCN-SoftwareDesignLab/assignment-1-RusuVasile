package services.AdminOperations;

import com.itextpdf.text.DocumentException;
import model.validation.Notification;
import repositories.EntityNotFoundException;
import repositories.bill.BillRepository;
import repositories.transaction.TransactionRepository;
import services.PDFgenerator;
import services.user.AuthenticationService;

import java.io.FileNotFoundException;
import java.util.Date;

public class AdminServiceImpl implements AdminService{
    private final BillRepository billService;
    private final TransactionRepository transaction;
    private final AuthenticationService authenticationService;


    public AdminServiceImpl(BillRepository billService, TransactionRepository transaction, AuthenticationService authenticationService) {
        this.billService = billService;
        this.transaction = transaction;
        this.authenticationService = authenticationService;
    }

    @Override
    public Notification<Boolean> createEmployee(String username, String password) {

        return authenticationService.register(username,password);
    }

    @Override
    public void generateReport(Long userId, Date date1, Date date2) throws EntityNotFoundException, DocumentException, FileNotFoundException {
        PDFgenerator pdFgenerator=new PDFgenerator(userId,date1,date2,billService,transaction);
        pdFgenerator.generateReport();
    }
}
