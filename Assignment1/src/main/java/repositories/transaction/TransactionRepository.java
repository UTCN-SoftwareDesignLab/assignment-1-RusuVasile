package repositories.transaction;

import model.Transaction;
import repositories.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();

    Transaction findById(Long id) throws EntityNotFoundException, EntityNotFoundException;

    List<Transaction> findByUserId(Long id) throws EntityNotFoundException, EntityNotFoundException;

   List <Transaction> betweenDates(Date date1, Date date2,Long employee_id)  throws EntityNotFoundException, EntityNotFoundException;

    boolean save(Transaction transaction);

    void removeAll();
}
