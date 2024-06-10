package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Client;
import br.com.ada.reservala.exception.ClientNotFoundException;
import br.com.ada.reservala.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client){
        return clientRepository.createClient(client);
    }

    public List<Client> readClient(){
        return clientRepository.readClient();
    }

    public Optional<Client> updateClient(Client client, Integer idClient){
        if (!clientRepository.existsClient(idClient)){
            throw new ClientNotFoundException("Client with id " + idClient + " not found.");
        }
        return Optional.of(clientRepository.udpateClient(client,idClient));
    }

    public void deleteClient(Integer idClient){
        if (!clientRepository.existsClient(idClient)){
            throw new ClientNotFoundException("Client with id " + idClient + " not found.");
        }
        clientRepository.deleteClient(idClient);
    }

}
