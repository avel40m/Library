package com.example.demo.service.implementation;

import com.example.demo.exception.ClientException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class IClientService implements ClientService {

    private final ClientRepository clientRepository;

    private final Logger logger = LoggerFactory.getLogger(IClientService.class);

    @Override
    public ResponseEntity<List<Client>> allClients() {
        try{
            logger.info("Getting all clients");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clientRepository.findAll());
        } catch (ClientException e) {
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Client> searchByDni(String clientDni) {
        try{
            var client = clientRepository.findByDni(clientDni);
            if (client.isEmpty()){
                logger.warn("The DNI not found");
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(client.get());
        } catch (ClientException e) {
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Client> createClient(Client client) {
        try{
            var searchClient = clientRepository.findByDni(client.getDni());
            if(searchClient.isEmpty()){
                logger.warn("The DNI not found");
                return ResponseEntity.notFound().build();
            }
            var newClient = new Client(null, client.getFullName(), client.getEmail(), client.getDni(), client.getPhone(), null);
            logger.info("Creating new client");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(clientRepository.save(newClient));
        } catch (ClientException e) {
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateClient(Client client) {
        try{
            var updatedClient = clientRepository.findById(client.getId());
            if (updatedClient.isEmpty()){
                logger.warn("The id of client not found");
                return ResponseEntity.notFound().build();
            }
            logger.info("Updating client with ID: " + updatedClient.get().getId());
            clientRepository.save(updatedClient.get());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (ClientException e) {
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteClient(Long clientId) {
        try{
            var updatedClient = clientRepository.findById(clientId);
            if (updatedClient.isEmpty()){
                logger.warn("The id of client not found");
                return ResponseEntity.notFound().build();
            }
            logger.info("deleted client with ID: " + updatedClient.get().getId());
            clientRepository.deleteById(clientId);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (ClientException e) {
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
