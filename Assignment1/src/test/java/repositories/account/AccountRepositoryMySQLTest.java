package repositories.account;

import database.DataBaseConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountRepositoryMySQLTest {

    private static AccountRepository accountRepository;
    @BeforeAll
    public static void setupClass() {
        accountRepository= new AccountRepositoryMySQL( new DataBaseConnectionFactory().getConnectionWrapper(true));
        accountRepository.removeAll();
    }

    @Test
    void findAll() {
        Account account1=new AccountBuilder().setSold(1000).setClientId(21L).setType("Finance").build();
        Account account2=new AccountBuilder().setSold(3000).setClientId(22L).setType("Savings").build();
        accountRepository.save(account1);
         accountRepository.save(account2);

        List<Account> accounts=accountRepository.findAll();
        assertEquals(accounts.size(),2);
    }

    @Test
    void findById() throws EntityNotFoundException {
        Long id=3L;
        Assert.assertTrue(accountRepository.findById(id).getSOLD().equals(3000));
    }

    @Test
    void findByClientId() throws EntityNotFoundException {
        Long id=21L;
        Integer value=1000;
        List<Account> accounts=accountRepository.findByClientId(id);
        assertEquals(accounts.size(),2);
    }

    @Test
    void save() {
    }
    @Test
    void removeAll() {
        accountRepository.removeAll();
        List<Account> accounts=accountRepository.findAll();
        assertEquals(accounts.size(),0);
    }
}