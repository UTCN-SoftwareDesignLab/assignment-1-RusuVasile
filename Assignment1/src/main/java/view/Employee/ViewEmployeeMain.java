package view.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class ViewEmployeeMain extends  JFrame{
    private JButton client;
    private JButton account;
    private JButton bill;
    private JButton transaction;

    public ViewEmployeeMain()throws HeadlessException{
        setSize(200,150);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(client);
        add(account);
        add(bill);
        add(transaction);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields(){
        client=new JButton("Client OP");
        client.setPreferredSize(new Dimension(100,50));
        account=new JButton("Account OP");
        account.setPreferredSize(new Dimension(100,50));
        bill=new JButton("Bill OP");
        bill.setPreferredSize(new Dimension(100,50));
        transaction=new JButton("Transaction OP");
        transaction.setPreferredSize(new Dimension(100,50));
    }

    public void goToClientOp(ActionListener clientButtonListener){
        client.addActionListener(clientButtonListener);
    }

    public void goToAccountOp(ActionListener accountButtonListener){
        account.addActionListener(accountButtonListener);
    }

    public void goToBillOp(ActionListener billButtonListener){
        bill.addActionListener(billButtonListener);
    }

    public void goToTransactionOp(ActionListener transactionButtonListener){
        transaction.addActionListener(transactionButtonListener);
    }
    public void setVisible() {
        this.setVisible(true);
    }
}
