package view.Employee;

import javax.swing.*;

public class TrasactionOperationsView extends JFrame {


    private JTextField value = new JTextField();

    private JComboBox<String> Accounts1;
    private JComboBox<String> Accounts2;

    public TrasactionOperationsView(String[] repAccounts){
        Accounts1 = new JComboBox<String>(repAccounts);
        Accounts2 = new JComboBox<String>(repAccounts);
        Object[] TransactionBox = {
                "From:", Accounts1,
                "Value:",value,
                "To:", Accounts2
        };

        int option = JOptionPane.showConfirmDialog(this,TransactionBox, "Transaction", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            System.out.println(getAccounts1());
            System.out.println(getValue());
            System.out.println(getAccounts2());
        }
    }


    public String getAccounts1() {
        return Accounts1.getSelectedItem().toString();
    }

    public String getAccounts2() {
        return Accounts2.getSelectedItem().toString();
    }

    public String getValue() {
        return value.getText();

    }
    public void setVisible() {
        this.setVisible(true);
    }

}
