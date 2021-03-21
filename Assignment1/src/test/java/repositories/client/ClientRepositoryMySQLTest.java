package repositories.client;

import database.DataBaseConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;

    @BeforeAll
    public static void setupClass() {
        clientRepository = new ClientRepositoryMySQL( new DataBaseConnectionFactory().getConnectionWrapper(true));
        //clientRepository.removeAll();
    }

    @Test
    void findAll() throws Exception{
        Client client1=new ClientBuilder().setName("Client1").setAddress("Address1").setCNP(1L).build();
        Client client2=new ClientBuilder().setName("Client2").setAddress("Address2").setCNP(2L).build();
       // clientRepository.save(client1);
       // clientRepository.save(client2);
      //  List<Client> clientList=clientRepository.findAll();
       // assertEquals(clientList.size(),2);


    }

    @Test
    void findById() throws EntityNotFoundException {
        Client client1=new ClientBuilder().setId(1L).setName("Client1").setAddress("Address1").setCNP(1L).build();
        Client client2=new ClientBuilder().setId(3L).setName("Client2").setAddress("Address2").setCNP(2L).build();
     //   clientRepository.save(client1);
      //  clientRepository.save(client2);
        Long id=21L;
        Assert.assertTrue(clientRepository.findById(id).getAddress().equals("Address1"));

    }

    @Test
    void save() {
    }

    @Test
    void removeAll() {
        clientRepository.removeAll();
        List<Client> clientList=clientRepository.findAll();
        assertEquals(clientList.size(),0);
    }
}