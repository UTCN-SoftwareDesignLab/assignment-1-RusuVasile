package services.AdminOperations;

import com.itextpdf.text.DocumentException;
import model.validation.Notification;
import repositories.EntityNotFoundException;

import java.io.FileNotFoundException;
import java.util.Date;

public interface AdminService {
    Notification<Boolean>createEmployee(String username, String password);

    void generateReport(Long userId, Date date1,Date date2) throws EntityNotFoundException, DocumentException, FileNotFoundException;
}
