package services.account;

import model.Account;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){this.accountRepository=accountRepository;}
    @Override
    public List<Account> viewAll() {

        return accountRepository.findAll();
    }

    @Override
    public List<String> findIds() {
        return accountRepository.findAllIds();
    }

    @Override
    public List<Account> viewByClientId(Long client_id) throws EntityNotFoundException {
        return accountRepository.findByClientId(client_id);
    }

    @Override
    public Account viewById(Long id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public boolean addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void addSold(Long id, Integer add) {
        accountRepository.addToSoldById(id,add);
    }

    @Override
    public void subSold(Long id, Integer add) {
        accountRepository.subFromSoldById(id,add);
    }
}
