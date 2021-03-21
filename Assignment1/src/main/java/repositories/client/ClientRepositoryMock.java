package repositories.client;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMock implements  ClientRepository{
    private final List<Client> clients;

    public ClientRepositoryMock() {
        this.clients = new ArrayList<>();
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public Client findById(Long id) {
        return clients.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public boolean save(Client client) {
        return clients.add(client);
    }

    @Override
    public void removeAll() {
        clients.clear();
    }
}
