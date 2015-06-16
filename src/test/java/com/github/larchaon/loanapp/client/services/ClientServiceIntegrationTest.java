package com.github.larchaon.loanapp.client.services;

import com.github.larchaon.loanapp.BaseIntegrationTest;
import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.client.ClientRepository;
import com.github.larchaon.loanapp.client.ClientService;
import com.github.larchaon.loanapp.helpers.ClientTestData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.github.larchaon.loanapp.matchers.BeanMatchers.hasProperty;
import static com.github.larchaon.loanapp.matchers.OptionalMatchers.existsAnd;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClientServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    ClientService service;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void saveClient_should_save_new_Client() throws Exception {
        Client client = ClientTestData.validClient();
        long clientId = service.saveClient(client);

        Optional<Client> client$ = clientRepository.findByPk(clientId);
        assertThat(client$, existsAnd(hasProperty(Client::getPk, equalTo(clientId))));
    }

    @Test
    public void findClientById_should_load_Client_from_DB() throws Exception {
        Client client = ClientTestData.validClient();
        Client savedClient = clientRepository.save(client);

        Optional<Client> client$ = service.findClientById(savedClient.getPk());
        assertThat(client$, existsAnd(hasProperty(Client::getPk, equalTo(savedClient.getPk()))));
    }
}