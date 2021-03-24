package controllers;

import controllers.AdminPackage.AdminMain;
import controllers.EmployeePackage.EmployeeMain;
import model.User;
import model.validation.Notification;
import repositories.EntityNotFoundException;
import repositories.user.AuthenticationException;
import services.AdminOperations.AdminService;
import services.account.AccountService;
import services.bill.BillService;
import services.client.ClientService;
import services.transaction.TransactionService;
import services.user.AuthenticationService;
import view.Admin.ViewAdminMain;
import view.Employee.ViewEmployeeMain;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final AdminService adminService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final BillService billService;
    private final TransactionService transactionService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService,AdminService adminService,ClientService clientService,AccountService accountService,TransactionService transactionService,BillService billService) {
        this.loginView = loginView;
        this.adminService=adminService;
        this.clientService=clientService;
        this.accountService=accountService;
        this.billService=billService;
        this.transactionService=transactionService;
        this.authenticationService = authenticationService;
        loginView.setLoginButtonListener(new LoginButtonListener());
        //loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
                if(username.equals("Admin23!@gmail.com")){
                    loginView.setVisible(false);
                    ViewAdminMain viewAdminMain=new ViewAdminMain();
                    AdminMain adminMain=new AdminMain(viewAdminMain,adminService);
                    viewAdminMain.setVisible();
                }
                else{
                    Long employeeId=authenticationService.getIdByUsername(username);

                    loginView.setVisible(false);
                    ViewEmployeeMain viewEmployeeMain=new ViewEmployeeMain();
                    EmployeeMain employeeMain=new EmployeeMain(viewEmployeeMain,clientService,accountService,billService,transactionService,employeeId);
                    viewEmployeeMain.setVisible();
                }
            } catch (AuthenticationException authenticationException) {
                authenticationException.printStackTrace();
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}
