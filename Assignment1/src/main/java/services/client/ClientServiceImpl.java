package services.client;

import model.Client;
import repositories.EntityNotFoundException;
import repositories.client.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository){this.clientRepository=clientRepository;}

    @Override
    public boolean addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> viewAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client viewById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }
}
