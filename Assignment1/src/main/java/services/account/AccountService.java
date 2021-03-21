package services.account;

import model.Account;
import repositories.EntityNotFoundException;

import java.util.List;

public interface AccountService {

    List<Account> viewAll();

    List<Account> viewByClientId(Long client_id) throws EntityNotFoundException;

    Account viewById(Long id) throws EntityNotFoundException;

    boolean addAccount(Account account);

    void addSold(Long id,Integer add);

    void subSold(Long id,Integer add);

}
