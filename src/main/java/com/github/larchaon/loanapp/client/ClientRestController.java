package com.github.larchaon.loanapp.client;

import com.github.larchaon.loanapp.client.register.RegisterClientModel;
import com.github.larchaon.loanapp.util.ToDoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<RegisterClientModel> registerAClient(@Valid @RequestBody RegisterClientModel model) {
        throw new ToDoException("Validate and Save client");
    }
}
