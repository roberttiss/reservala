package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Client;
import br.com.ada.reservala.exception.ExceptionThrower;
import br.com.ada.reservala.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ReservationService reservationService;

    public ClientService(ClientRepository clientRepository, ReservationService reservationService){
        this.clientRepository = clientRepository;
        this.reservationService = reservationService;
    }

    public Client createClient(Client client){
//        checkIfClientExist(client.getIdClient());
        int id = clientRepository.getLastInsertedId().orElse(0) + 1;
        client.setIdClient(id);
        return clientRepository.createClient(client);
    }

    public List<Client> readClient(){
        return clientRepository.readClient();
    }

    public Optional<Client> redClientById(Integer idClient){
        checkIfClientDoesNotExist(idClient);
        return Optional.of(clientRepository.readClientById(idClient));
    }

    public Optional<Client> updateClient(Client client, Integer idClient){
        checkIfClientDoesNotExist(idClient);
        return Optional.of(clientRepository.udpateClient(client,idClient));
    }

    public void deleteClient(Integer idClient){
        checkIfClientDoesNotExist(idClient);
        reservationService.checkIfClientHasReservation(idClient);
        clientRepository.deleteClient(idClient);
    }

    public void checkIfClientDoesNotExist(int idClient)  {
        if (!clientRepository.existsClient(idClient)){
            ExceptionThrower.throwClientNotFoundException("Client with id " + idClient + " not exist.");
        }
    }

}
