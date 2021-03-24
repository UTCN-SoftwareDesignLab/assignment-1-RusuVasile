package controllers.EmployeePackage;

import model.builder.TransactionBuilder;
import model.builder.UtilityBillBuilder;
import services.account.AccountService;
import services.bill.BillService;
import services.client.ClientService;
import services.transaction.TransactionService;
import view.Employee.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeMain {
    private final ViewEmployeeMain viewEmployeeMain;
    private final ClientService clientService;
    private final AccountService accountService;
    private final BillService billService;
    private final Long employeeId;
    private final TransactionService transactionService;

    public EmployeeMain(ViewEmployeeMain viewEmployeeMain,ClientService clientService,AccountService accountService,BillService billService,TransactionService transactionService,Long employeeId) {
        this.viewEmployeeMain = viewEmployeeMain;
        this.clientService=clientService;
        this.accountService=accountService;
        this.billService=billService;
        this.transactionService=transactionService;
        this.employeeId=employeeId;
        viewEmployeeMain.goToClientOp(new ClientOp());
        viewEmployeeMain.goToAccountOp(new AccountOp());
        viewEmployeeMain.goToBillOp(new BillOp());
        viewEmployeeMain.goToTransactionOp(new TransactionOp());
    }

    private class ClientOp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientOperationsView clientOperationsView=new ClientOperationsView();
           new ClientOperationsController(clientOperationsView,clientService);
           clientOperationsView.setVisible();
        }
    }
    private class AccountOp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AccountOperationsView accountOperationsView=new AccountOperationsView();
            new AccountOperationsController(accountOperationsView,accountService);
            accountOperationsView.setVisible();
        }
    }
    private class BillOp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> accounts=accountService.findIds();
            BillOperationsView billOperationsView=new BillOperationsView(accounts.toArray(new String[0]));
            billOperationsView.setVisible();
            String payer=billOperationsView.getAccounts();
            Long idPayer=Long.parseLong(payer.substring(11,payer.length()));
            Integer value=Integer.parseInt(billOperationsView.getValue());
            String description=billOperationsView.getDescription();
            billService.addBill(new UtilityBillBuilder().setUserId(employeeId).setAccountId(idPayer).setValue(value).setDescription(description).build());
        }
    }
    private class TransactionOp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> accounts=accountService.findIds();
            TrasactionOperationsView trasactionOperationsView=new TrasactionOperationsView(accounts.toArray(new String[0]));
            trasactionOperationsView.setVisible();
            String fromAccount=trasactionOperationsView.getAccounts1();
            Long idFrom=Long.parseLong(fromAccount.substring(11,fromAccount.length()));
            String toAccount=trasactionOperationsView.getAccounts2();
            Long idTo=Long.parseLong(toAccount.substring(11,toAccount.length()));

            Integer value=Integer.parseInt(trasactionOperationsView.getValue());
            transactionService.addTransaction(new TransactionBuilder().setUserId(employeeId).setToId(idTo).setFromId(idFrom).setValue(value).build());
        }
    }
}
