package repositories.account;

import model.Account;
import repositories.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException, EntityNotFoundException;

    List<Account> findByClientId(Long id) throws EntityNotFoundException, EntityNotFoundException;

    boolean save(Account account);

    void addToSoldById(Long id,Integer toAdd);

    void subFromSoldById(Long id,Integer toAdd);

    Integer getSoldById(Long id) throws SQLException, EntityNotFoundException;

    void removeAll();
}
