package services.transaction;

import model.Transaction;
import repositories.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    List<Transaction> viewAll();

    List <Transaction> betweenDatesByUser(Date date1, Date date2, Long employee_id)  throws EntityNotFoundException, EntityNotFoundException;

    boolean addTransaction(Transaction transaction);
}
