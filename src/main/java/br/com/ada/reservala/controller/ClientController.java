package br.com.ada.reservala.controller;

import br.com.ada.reservala.controller.dto.ClientDTORequest;
import br.com.ada.reservala.controller.dto.ClientDTOResponse;
import br.com.ada.reservala.controller.mapper.ClientMapper;
import br.com.ada.reservala.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientDTOResponse> createClient(@Valid @RequestBody ClientDTORequest newClient){
        var client = clientMapper.toEntity(newClient);
        var response = clientMapper.toDto(clientService.createClient(client));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTOResponse>> readClient(){
        var response = clientMapper.toDto(clientService.readClient());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<ClientDTOResponse> updateClient(@RequestBody ClientDTORequest newClient, @PathVariable("idClient") Integer idClient){
        var clientOptional = clientService.updateClient(clientMapper.toEntity(newClient), idClient);
        return clientOptional
                .map(client -> {
                    var dto = clientMapper.toDto(client);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @RequestMapping("/{idClient}")
    public ResponseEntity<Void> deleteClient(@PathVariable("idClient") Integer idClient){
        clientService.deleteClient(idClient);
        return ResponseEntity.noContent().build();
    }
}
