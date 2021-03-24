package services.transaction;

import database.DataBaseConnectionFactory;
import model.Transaction;
import model.builder.TransactionBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;
import repositories.account.AccountRepositoryMySQL;
import repositories.transaction.TransactionRepository;
import repositories.transaction.TransactionRepositoryMySQL;

class TransactionServiceImplTest {
    private static TransactionRepository transactionRepository;
    private static AccountRepository accountRepository;
    private static TransactionService transactionService;


    @BeforeAll
    public static void setup(){
        transactionRepository=new TransactionRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
        accountRepository=new AccountRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
        transactionService=new TransactionServiceImpl(transactionRepository,accountRepository);
    }

    @Test
    void viewAll() {
        //assertEquals(3,transactionService.viewAll().size());
    }

    @Test
    void betweenDatesByUser() {
    }

    @Test
    void addTransaction() throws EntityNotFoundException {
        Integer initialToSold=accountRepository.findById(2L).getSOLD();
        Integer initialFromSold=accountRepository.findById(1L).getSOLD();

        Transaction transaction=new TransactionBuilder().setUserId(1L).setFromId(1L).setToId(2L).setValue(500).setDate().build();
        transactionService.addTransaction(transaction);
        Integer afterToSold=accountRepository.findById(2L).getSOLD();
        Integer afterFromSold=accountRepository.findById(1L).getSOLD();

        Assert.assertTrue(initialToSold+500==afterToSold && initialFromSold-500==afterFromSold);
    }
}