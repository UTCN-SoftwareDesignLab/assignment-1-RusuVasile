package repositories.account;

import database.DataBaseConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import static org.junit.Assert.assertEquals;

class AccountRepositoryMySQLTest {
    private static AccountRepository accountRepository;
    @BeforeAll
    public static void setup(){
        accountRepository=new AccountRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
    }

    @Test
    void findAll() {
        /*
        Account account1=new AccountBuilder().setType("Savings").setClientId(5L).setSold(1000).setDate().build();
        Account account2=new AccountBuilder().setType("To Invest").setClientId(5L).setSold(1500).setDate().build();
        Account account3=new AccountBuilder().setType("Savings").setClientId(6L).setSold(2000).setDate().build();
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

         */
        assertEquals(3,accountRepository.findAll().size());
    }

    @Test
    void findAllIds() {
        assertEquals(3,accountRepository.findAllIds().size());
    }

    @Test
    void findById() throws EntityNotFoundException {
        Assert.assertTrue(accountRepository.findById(1L).getType().equals("Savings"));
    }

    @Test
    void findByClientId() throws EntityNotFoundException {
        assertEquals(2,accountRepository.findByClientId(5L).size());
        assertEquals(1,accountRepository.findByClientId(6L).size());
    }

    @Test
    void save() {

    }

    @Test
    void removeById() {
        Account accountToRemove=new AccountBuilder().setType("Savings").setClientId(6L).setSold(1000).setDate().build();
    }

    @Test
    void addToSoldById() throws EntityNotFoundException {
       Integer initialSold=accountRepository.findById(1L).getSOLD();
        accountRepository.addToSoldById(1L,500);

        Assert.assertTrue(initialSold+500==accountRepository.findById(1L).getSOLD());

    }

    @Test
    void subFromSoldById() throws EntityNotFoundException {
        Integer initialSold=accountRepository.findById(1L).getSOLD();
        accountRepository.subFromSoldById(1L,500);
        Assert.assertTrue(initialSold-500==accountRepository.findById(1L).getSOLD());
    }

    @Test
    void removeAll() {
    }
}