package com.github.larchaon.loanapp.client;

import com.github.larchaon.loanapp.client.register.RegisterClientModel;
import com.github.larchaon.loanapp.client.services.ClientService;
import com.github.larchaon.loanapp.util.orika.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    @Autowired
    DataMapper mapper;

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "", method = POST)
    public ResponseEntity<?> registerAClient(@Valid @RequestBody RegisterClientModel model) {
        Client newClient = mapper.map(model, Client.class);
        long clientId = clientService.saveClient(newClient);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/clients/" + clientId);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{clientId}", method = GET)
    public ResponseEntity<?> findClientById(@PathVariable("clientId") long clientId) {
        Optional<Client> client$ = clientService.findClientById(clientId);

        if (client$.isPresent()) {
            return new ResponseEntity<>(client$.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
