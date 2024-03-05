package com.example.demo.service;

import com.example.demo.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    ResponseEntity<Client> createClient(Client client);
    ResponseEntity<Void> updateClient(Client client);
    ResponseEntity<Void> deleteClient(Long clientId);
}
