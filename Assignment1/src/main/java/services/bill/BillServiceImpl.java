package services.bill;

import model.UtilityBill;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;
import repositories.bill.BillRepository;

import java.util.Date;
import java.util.List;

public class BillServiceImpl implements BillService{
    private final BillRepository billRepository;
    private final AccountRepository accountRepository;

    public BillServiceImpl(BillRepository billRepository,AccountRepository accountRepository){
        this.accountRepository=accountRepository;
        this.billRepository=billRepository;
    }
    @Override
    public List<UtilityBill> viewAll() {
        return billRepository.findAll();
    }

    @Override
    public List<UtilityBill> betweenDatesByUser(Date date1, Date date2, Long id) throws EntityNotFoundException, EntityNotFoundException {
        return billRepository.betweenDates(date1,date2,id);
    }

    @Override
    public boolean addBill(UtilityBill utilityBill) {
        accountRepository.subFromSoldById(utilityBill.getId_account(),utilityBill.getValue());
        return billRepository.save(utilityBill);
    }
}
