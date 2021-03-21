package services.client;

import model.Client;
import repositories.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    boolean addClient(Client client);

    List<Client> viewAll();

    Client viewById(Long id) throws EntityNotFoundException;
}
