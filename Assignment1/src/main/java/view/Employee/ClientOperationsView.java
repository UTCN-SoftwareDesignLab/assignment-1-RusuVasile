package view.Employee;

import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import static javax.swing.BoxLayout.Y_AXIS;

public class ClientOperationsView extends JFrame {
    private JButton btnCreateClient;
    private JButton btnViewClients;
    private JTextField Name;
    private JTextField Address;
    private JTextField CNP;
    private JTable clientsList;
    public ClientOperationsView() throws HeadlessException{
        setSize(500,500);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(btnCreateClient);
        add(btnViewClients);
        add(Name);
        add(Address);
        add(CNP);
        add(clientsList);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public  String getName(){return Name.getText();}
    public  String getAddress(){return Address.getText();}
    public  Long getCNP(){return Long.parseLong(CNP.getText());}
    private void initializeFields(){
        btnCreateClient=new JButton("Create Client");
        btnViewClients=new JButton("View Clients");
        Name=new JTextField();
        CNP=new JTextField();
        Address=new JTextField();

        createTable();
    }
    public void setBtnCreateClientListener(ActionListener createClient){
        btnCreateClient.addActionListener(createClient);
    }
    public void viewClientsListener(ActionListener viewClients){
        btnViewClients.addActionListener(viewClients);
    }
    public void updateTable(List<Client> clients){
        String[] cols={"id","Name","Address","CNP"};
        String [][] matrix = new String[clients.size()+1][4];
        for(Integer i=0;i<clients.size();i++){
            matrix[i][0]=clients.get(i).getId().toString();
            matrix[i][1]=clients.get(i).getName().toString();
            matrix[i][2]=clients.get(i).getAddress().toString();
            matrix[i][3]=clients.get(i).getCnp().toString();
        }
        remove(clientsList);
        clientsList=new JTable(matrix,cols);
        clientsList.setPreferredScrollableViewportSize(new Dimension(400,50));
        add(clientsList);
        Random rn = new Random();
        setSize(500,500+rn.nextInt(10 - 0 + 1) + 0);
    }
    private void createTable(){
        String[] cols={"id","Name","Address","CNP"};
        String[][] clients={
                {"1","name1","address1","CNP1"},
                {"2","name2","address2","CNP2"},
                {"3","name3","address3","CNP3"}
        };

        clientsList=new JTable(clients,cols);
        clientsList.setPreferredScrollableViewportSize(new Dimension(400,50));
    }
    public void refresh(){
        Name.setText("");
        CNP.setText("");
        Address.setText("");
    }
    public void setVisible() {
        this.setVisible(true);
    }

}
