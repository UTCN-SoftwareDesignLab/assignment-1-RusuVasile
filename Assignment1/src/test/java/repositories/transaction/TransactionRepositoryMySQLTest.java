package repositories.transaction;

import database.DataBaseConnectionFactory;
import model.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionRepositoryMySQLTest {
    private static TransactionRepository transactionRepository;

    @BeforeAll
    public static void setupClass(){
        transactionRepository=new TransactionRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
    }

    @Test
    void findAll() {
        Transaction transaction1=new Transaction();
    }

    @Test
    void findById() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void beforeSpeciffiedTransactionDate() {
    }

    @Test
    void afterSpeciffiedTransactionDate() {
    }

    @Test
    void save() {
    }

    @Test
    void removeAll() {
        transactionRepository.removeAll();
        List<Transaction> transactions=transactionRepository.findAll();
        assertEquals(transactions.size(),0);
    }
}