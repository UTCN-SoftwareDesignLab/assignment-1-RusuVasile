package repositories.client;

import model.Client;
import repositories.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException, EntityNotFoundException;

    void removeById(Long id);

    void updateAddress(String newAddress, Long id);

    boolean save(Client client);

    void removeAll();

}
