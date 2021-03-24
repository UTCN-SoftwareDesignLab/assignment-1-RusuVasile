package controllers.EmployeePackage;

import model.Account;
import model.builder.AccountBuilder;
import services.account.AccountService;
import view.Employee.AccountOperationsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountOperationsController {
    private final AccountOperationsView accountOperationsView;
    private final AccountService accountService;

    public AccountOperationsController(AccountOperationsView accountOperationsView, AccountService accountService) {
        this.accountOperationsView = accountOperationsView;
        this.accountService = accountService;
        accountOperationsView.setBtnCreateAccountListener(new CreateAccountListener());
        accountOperationsView.viewAccountsListener(new ViewAccountsListener());
    }
    private class ViewAccountsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Account> accountList=accountService.viewAll();
            accountOperationsView.updateTable(accountList);
        }
    }
    private class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String kind=accountOperationsView.getKind();
            Integer sold=accountOperationsView.getSold();
            Long client_id=accountOperationsView.getClient_id();

            Account account=new AccountBuilder().setType(kind).setSold(sold).setClientId(client_id).build();
            accountService.addAccount(account);
            List<Account> accountList=accountService.viewAll();
            accountOperationsView.updateTable(accountList);
        }
    }
}
