package view.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class ViewAdminMain extends JFrame {

    private JButton btnCreateEmployee;
    private JTextField username;
    private JTextField password;
    private JButton btnGenerateReport;
    private JTextField startingDate;
    private JTextField endingDate;
    private JTextField employeeId;

    public ViewAdminMain()throws HeadlessException{
        setSize(500,500);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(btnCreateEmployee);
        add(username);
        add(password);
        add(btnGenerateReport);
        add(startingDate);
        add(endingDate);
        add(employeeId);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public String getUsername(){return username.getText();}
    public String getPassword(){return password.getText();}
    public String getStartingDate(){return startingDate.getText();}
    public String getEndingDate(){return endingDate.getText();}
    public String getEmployeeId(){return employeeId.getText();}

    private void initializeFields() {

        btnCreateEmployee = new JButton("Create Employee");
        username=new JTextField();
        password=new JTextField();
        btnGenerateReport=new JButton("Generate Report ");
        startingDate=new JTextField();
        endingDate=new JTextField();
        employeeId=new JTextField();
    }
    public void setRegisterButtonListener(ActionListener registerButtonListener) {
        btnCreateEmployee.addActionListener(registerButtonListener);
    }
    public void setBtnGenerateReport(ActionListener generateReport){
        btnGenerateReport.addActionListener(generateReport);
    }
    public void setVisible() {
        this.setVisible(true);
    }


}
