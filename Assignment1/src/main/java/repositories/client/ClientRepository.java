package repositories.client;

import model.Client;
import repositories.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException, EntityNotFoundException;

    boolean save(Client client);

    void removeAll();

}
