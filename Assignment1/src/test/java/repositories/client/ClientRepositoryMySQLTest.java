package repositories.client;

import database.DataBaseConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import static org.junit.Assert.assertEquals;

class ClientRepositoryMySQLTest {
    private static ClientRepository clientRepository;
    @BeforeAll
    public static void setup(){
        clientRepository=new ClientRepositoryMySQL(new DataBaseConnectionFactory().getConnectionWrapper(true));
        //clientRepository.removeAll();
    }
    @Test
    void findAll() {
        /*Client client1=new ClientBuilder().setName("Client1").setCNP(1L).setAddress("Address1").build();
        Client client2=new ClientBuilder().setName("Client2").setCNP(2L).setAddress("Address2").build();
        clientRepository.save(client1);
        clientRepository.save(client2);

         */
        assertEquals(2,clientRepository.findAll().size());
    }

    @Test
    void findById() throws EntityNotFoundException {
        String name="Client1";
        Assert.assertTrue(clientRepository.findById(5L).getName().equals(name));
    }

    @Test
    void removeById() {
        Client clientToRemove=new ClientBuilder().setName("ClientToRemove").setCNP(3L).setAddress("Address3").build();
        clientRepository.save(clientToRemove);
        //actually, it removes by CNP :)
        clientRepository.removeById(3L);
        assertEquals(2,clientRepository.findAll().size());
    }

    @Test
    void updateAddress() throws EntityNotFoundException {
        clientRepository.updateAddress("newAddress",5L);
        Assert.assertTrue(clientRepository.findById(5L).getAddress().equals("newAddress"));
    }

    @Test
    void save() {
    }

    @Test
    void removeAll() {
    }
}