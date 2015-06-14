package com.github.larchaon.loanapp.helpers;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.client.Client;

public class ClientTestData {

    public static Client validClient() {
        return TestBuilder.forBean(Client::new).
                with(Client::setPhoneNumber, "+371 2232323").
                with(Client::setEmail, "me@mail.com").
                with(Client::setPassword, "pass").
                with(Client::setPersonalCode, 121312313L).
                build();
    }
}
