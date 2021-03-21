package services.bill;

import model.UtilityBill;
import repositories.EntityNotFoundException;

import java.util.List;

public interface BillService {
    List<UtilityBill> viewAll();

    List<UtilityBill> betweenDatesByUser(java.util.Date date1, java.util.Date date2, Long id)  throws EntityNotFoundException, EntityNotFoundException;

    boolean addBill(UtilityBill utilityBill);
}
