package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients(){
        return clientService.allClients();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientByDni(@PathVariable String clientId){
        return clientService.searchByDni(clientId);
    }

    @PostMapping("/")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        return clientService.deleteClient(clientId);
    }
}
