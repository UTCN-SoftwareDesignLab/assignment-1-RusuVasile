package controllers.AdminPackage;

import com.itextpdf.text.DocumentException;
import model.validation.Notification;
import repositories.EntityNotFoundException;
import services.AdminOperations.AdminService;
import view.Admin.ViewAdminMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Date;

public class AdminMain {
   private final ViewAdminMain viewAdminMain;
   private final AdminService adminService;

    public AdminMain(ViewAdminMain viewAdminMain, AdminService adminService) {
        this.viewAdminMain = viewAdminMain;
        this.adminService = adminService;
        viewAdminMain.setRegisterButtonListener(new CreateEmployeeListener());
        viewAdminMain.setBtnGenerateReport(new createReport());
    }

    private class CreateEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = viewAdminMain.getUsername();
            String password = viewAdminMain.getPassword();

            Notification<Boolean> registerNotification = adminService.createEmployee(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(viewAdminMain.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(viewAdminMain.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(viewAdminMain.getContentPane(), "Registration successful!");
                }
            }
        }
    }

    private class createReport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id=Long.parseLong(viewAdminMain.getEmployeeId());
            Date startingDate=new Date(110,8,21);
            Date endingDate=new Date(121,3,25);
            try {
                adminService.generateReport(id,startingDate,endingDate);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            } catch (DocumentException documentException) {
                documentException.printStackTrace();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }
}
