package view.Employee;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import static javax.swing.BoxLayout.Y_AXIS;

public class AccountOperationsView extends JFrame {
    private JButton btnCreateAccount;
    private JButton btnViewAccounts;
    private JTextField kind;
    private JTextField sold;
    private JTextField client_id;
    private JTable accountsList;

    public AccountOperationsView() throws HeadlessException{
        setSize(500,500);
        setLocationRelativeTo(null);
        init();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(btnCreateAccount);
        add(btnViewAccounts);
        add(kind);
        add(sold);
        add(client_id);
        add(accountsList);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void init(){
        btnCreateAccount=new JButton("Create Account");
        btnViewAccounts=new JButton("View Accounts");
        kind=new JTextField();
        sold=new JTextField();
        client_id=new JTextField();


        createTable();
    }
    public  String getKind(){return kind.getText();}
    public  Integer getSold(){return Integer.parseInt(sold.getText());}
    public  Long getClient_id(){return Long.parseLong(client_id.getText());}

    public void updateTable(List<Account> accounts){
        String[] cols={"id","kind","SOLD","client_ID","Creation_Date"};
        String [][] matrix = new String[accounts.size()+1][5];
        for(Integer i=0;i<accounts.size();i++){
            matrix[i][0]=accounts.get(i).getId().toString();
            matrix[i][1]=accounts.get(i).getType();
            matrix[i][2]=accounts.get(i).getSOLD().toString();
            matrix[i][3]=accounts.get(i).getId_client().toString();
            matrix[i][4]=accounts.get(i).getCreationDate().toString();
        }
        remove(accountsList);
        accountsList=new JTable(matrix,cols);
        accountsList.setPreferredScrollableViewportSize(new Dimension(400,50));
        accountsList.setVisible(true);
        add(accountsList);
        Random rn = new Random();
        setSize(500,500+rn.nextInt(10 - 0 + 1) + 0);

    }
    public void setBtnCreateAccountListener(ActionListener createAccount){
        btnCreateAccount.addActionListener(createAccount);
    }
    public void viewAccountsListener(ActionListener viewAccounts){
        btnViewAccounts.addActionListener(viewAccounts);
    }

    private void createTable(){
        String[] cols={"id","kind","SOLD","client_ID","Creation_Date"};
        String[][] accounts={
                {"1","kind1","SOLD1","clientid1","date1"},
                {"1","kind1","SOLD1","clientid1","date1"}
        };
        accountsList=new JTable(accounts,cols);
        accountsList.setPreferredScrollableViewportSize(new Dimension(400,50));

    }

    public void refresh(){
        kind.setText("");
        sold.setText("");
        client_id.setText("");

    }
    public void setVisible() {
        this.setVisible(true);
    }

}
