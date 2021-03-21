package services.transaction;

import model.Transaction;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;
import repositories.transaction.TransactionRepository;

import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    public TransactionServiceImpl(TransactionRepository repository,AccountRepository accountRepository){
        this.transactionRepository=repository;
        this.accountRepository=accountRepository;
    }

    @Override
    public List<Transaction> viewAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> betweenDatesByUser(Date date1, Date date2, Long employee_id) throws EntityNotFoundException, EntityNotFoundException {

        return transactionRepository.betweenDates(date1,date2,employee_id);
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        accountRepository.subFromSoldById(transaction.getId_from(),transaction.getValue());
        accountRepository.addToSoldById(transaction.getId_to(),transaction.getValue());

        return transactionRepository.save(transaction);
    }
}
