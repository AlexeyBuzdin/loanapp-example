package com.github.larchaon.loanapp.client.services;

import com.github.larchaon.loanapp.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public long saveClient(Client newClient) {
        Client savedClient = clientRepository.save(newClient);
        return savedClient.getPk();
    }

    public Optional<Client> findClientById(long clientId) {
        return clientRepository.findByPk(clientId);
    }
}
