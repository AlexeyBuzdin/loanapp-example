package com.github.larchaon.loanapp.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public Client getClient(@PathVariable("clientId") String clientId) {
        // TODO: Fetch client
        return new Client();
    }
}
