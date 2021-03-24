package services.bill;

import database.DataBaseConnectionFactory;
import model.UtilityBill;
import model.builder.UtilityBillBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;
import repositories.account.AccountRepositoryMySQL;
import repositories.bill.BillRepository;
import repositories.bill.BillRepositoryMySQL;

class BillServiceImplTest {
    private static BillRepository billRepository;
    private static AccountRepository accountRepository;
    private static BillService billService;
    @BeforeAll
    public static void setup(){
        billRepository = new BillRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
        accountRepository=new AccountRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
        billService=new BillServiceImpl(billRepository,accountRepository);
    }
    @Test
    void viewAll() {
        //assertEquals(3,billService.viewAll().size());
    }

    @Test
    void betweenDatesByUser() {
    }

    @Test
    void addBill() throws EntityNotFoundException {
        Integer initialSold=accountRepository.findById(1L).getSOLD();
        UtilityBill utilityBill1= new UtilityBillBuilder().setAccountId(1L).setUserId(1L).setValue(500).setDescription("CovrigiDinStatie").setDate().build();
        billService.addBill(utilityBill1);
        Integer afterSold=accountRepository.findById(1L).getSOLD();
        Assert.assertTrue(initialSold-500==afterSold);
    }
}