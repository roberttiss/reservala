package br.com.ada.reservala.controller.mapper;

import br.com.ada.reservala.controller.dto.ClientDTORequest;
import br.com.ada.reservala.controller.dto.ClientDTOResponse;
import br.com.ada.reservala.domain.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public Client toEntity(ClientDTORequest clientDTORequest){
        return Client
                .builder()
                .age(clientDTORequest.getAge())
                .name(clientDTORequest.getName())
                .build();
    }

    public List<ClientDTOResponse> toDto(List<Client> clients){
        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ClientDTOResponse toDto(Client client){
        return ClientDTOResponse
                .builder()
                .idClient(client.getIdClient())
                .age(client.getAge())
                .name(client.getName())
                .build();
    }
}
