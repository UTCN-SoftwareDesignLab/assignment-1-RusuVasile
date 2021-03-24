package repositories.bill;

import database.DataBaseConnectionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BillRepositoryMySQLTest {
    private static BillRepository billRepository;

    @BeforeAll
    private static void setup(){
       billRepository=new BillRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
    }

    @Test
    void findAll() {
        /*
        UtilityBill utilityBill=new UtilityBillBuilder().setAccountId(1L).setValue(100).setUserId(1L).setDescription("ShaormaCuDetoate").setDate().build();
        UtilityBill utilityBill2=new UtilityBillBuilder().setAccountId(2L).setValue(400).setUserId(1L).setDescription("PitaCuUnsoare").setDate().build();
        billRepository.save(utilityBill);
        billRepository.save(utilityBill2);

         */
        assertEquals(2,billRepository.findAll().size());
    }

    @Test
    void findById() throws EntityNotFoundException {
        assertTrue(billRepository.findById(1L).getDescription().equals("ShaormaCuDetoate"));
    }

    @Test
    void findByUserId() throws EntityNotFoundException {
        assertEquals(2,billRepository.findByUserId(1L).size());
    }

    @Test
    void betweenDates() throws EntityNotFoundException {
        Date goodStartingDate=new Date(110,8,21);
        Date goodEndingDate=new Date(121,3,25);
        Date badEndingDate=new Date(111,3,25);
        Assert.assertTrue(billRepository.betweenDates(goodStartingDate,goodEndingDate,1L).size()==2
        && billRepository.betweenDates(goodEndingDate,badEndingDate,1L).size()==0);
    }

    @Test
    void save() {
    }

    @Test
    void removeAll() {
    }

}