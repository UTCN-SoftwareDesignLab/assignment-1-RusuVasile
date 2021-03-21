package repositories.bill;

import model.UtilityBill;
import repositories.EntityNotFoundException;

import java.util.List;

public interface BillRepository {
    List<UtilityBill> findAll();

    UtilityBill findById(Long id)throws EntityNotFoundException, EntityNotFoundException;

    List<UtilityBill> findByUserId(Long id)throws EntityNotFoundException, EntityNotFoundException;

    List <UtilityBill> betweenDates(java.util.Date date1, java.util.Date date2,Long id)  throws EntityNotFoundException, EntityNotFoundException;

    boolean save(UtilityBill utilityBill);

    void removeAll();
}
