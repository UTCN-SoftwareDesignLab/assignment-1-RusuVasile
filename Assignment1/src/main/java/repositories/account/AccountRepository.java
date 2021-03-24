package repositories.account;

import model.Account;
import repositories.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    List <String> findAllIds();
    Account findById(Long id) throws EntityNotFoundException, EntityNotFoundException;

    List<Account> findByClientId(Long id) throws EntityNotFoundException, EntityNotFoundException;

    boolean save(Account account);

    void removeById(Long id);

    void addToSoldById(Long id,Integer toAdd);

    void subFromSoldById(Long id,Integer toAdd);



    void removeAll();
}
