package repositories.transaction;

import database.DataBaseConnectionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import java.util.Date;

import static org.junit.Assert.assertEquals;

class TransactionRepositoryMySQLTest {
    private static TransactionRepository transactionRepository;
    @BeforeAll
    public static void setup(){
        transactionRepository=new TransactionRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
    }

    @Test
    void findAll() {
        /*
        Transaction transaction1=new TransactionBuilder().setUserId(1L).setValue(300).setFromId(1L).setToId(2L).setDate().build();
        Transaction transaction2=new TransactionBuilder().setUserId(1L).setValue(500).setFromId(3L).setToId(2L).setDate().build();
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

         */
        assertEquals(2,transactionRepository.findAll().size());
    }

    @Test
    void findById() throws EntityNotFoundException {
        Assert.assertTrue(transactionRepository.findById(1L).getValue()==300);
    }

    @Test
    void findByUserId() throws EntityNotFoundException {

        Assert.assertTrue(transactionRepository.findByUserId(1L).size()==2);

    }

    @Test
    void betweenDates() throws EntityNotFoundException {
        Date goodStartingDate=new Date(110,8,21);
        Date goodEndingDate=new Date(121,3,25);
        Date badEndingDate=new Date(111,3,25);
        Assert.assertTrue(transactionRepository.betweenDates(goodEndingDate,goodEndingDate,1L).size()==2
                        &&         transactionRepository.betweenDates(goodEndingDate,badEndingDate,1L).size()==0);

    }

    @Test
    void save() {
    }

    @Test
    void removeAll() {
    }
}