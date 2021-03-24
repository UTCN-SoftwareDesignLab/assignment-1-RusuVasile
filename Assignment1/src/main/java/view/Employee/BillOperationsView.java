package view.Employee;

import javax.swing.*;

public class BillOperationsView extends  JFrame{
    private JTextField value = new JTextField();
    private JTextField description = new JTextField();

    private JComboBox<String> Accounts;
    public BillOperationsView(String[] repAccounts){
        Accounts= new JComboBox<String>(repAccounts);
        Object[] BillBox = {
                "Payer:", Accounts,
                "Value:",value,
                "Description",description,
        };

        int option = JOptionPane.showConfirmDialog(this,BillBox, "Bill", JOptionPane.OK_CANCEL_OPTION);

    }


    public String getAccounts() {
        return Accounts.getSelectedItem().toString();
    }

    public String getValue() {
        return value.getText();

    }
    public String getDescription() {
        return description.getText();

    }
    public void setVisible() {
        this.setVisible(true);
    }

}

