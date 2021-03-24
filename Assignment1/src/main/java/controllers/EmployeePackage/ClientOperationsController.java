package controllers.EmployeePackage;

import model.Client;
import model.builder.ClientBuilder;
import services.client.ClientService;
import view.Employee.ClientOperationsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientOperationsController {
    private final ClientOperationsView clientOperationsView;
    private final ClientService clientService;

    public ClientOperationsController(ClientOperationsView clientOperationsView,ClientService clientService){
        this.clientOperationsView=clientOperationsView;
        this.clientService=clientService;
        clientOperationsView.setBtnCreateClientListener(new  CreateClientListener());
        clientOperationsView.viewClientsListener(new ViewClientsListener());
    }
    private class ViewClientsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Client> clients=clientService.viewAll();
            clientOperationsView.updateTable(clients);
        }
    }
    private class  CreateClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=clientOperationsView.getName();
            String address=clientOperationsView.getAddress();
            Long CNP= clientOperationsView.getCNP();
            Client client= new ClientBuilder().setName(name).setAddress(address).setCNP(CNP).build();
            clientService.addClient(client);
            clientOperationsView.refresh();
            List<Client> clients=clientService.viewAll();
            clientOperationsView.updateTable(clients);
        }
    }

}
