package com.github.larchaon.loanapp.client.services;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.BaseIntegrationTest;
import com.github.larchaon.loanapp.client.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.github.larchaon.matchers.BeanMatchers.hasProperty;
import static com.github.larchaon.matchers.OptionalMatchers.existsAnd;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ClientServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    ClientService service;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void saveClient_should_save_new_Client() throws Exception {
        Client client = TestBuilder.forBean(Client::new).
                build();
        long clientId = service.saveClient(client);

        Optional<Client> client$ = clientRepository.findById(clientId);
        assertThat(client$, existsAnd(hasProperty(Client::getId, equalTo(clientId))));
    }

    @Test
    public void findClientById_should_load_Client_from_DB() throws Exception {
        Client client = TestBuilder.forBean(Client::new).
                build();
        Client savedClient = clientRepository.save(client);

        Optional<Client> client$ = service.findClientById(savedClient.getId());
        assertThat(client$, existsAnd(hasProperty(Client::getId, equalTo(savedClient.getId()))));
    }
}