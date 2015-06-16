package com.github.larchaon.loanapp.client.loan;

import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.client.ClientService;
import com.github.larchaon.loanapp.client.loan.issue.IssueLoanModel;
import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.LoanService;
import com.github.larchaon.loanapp.util.exceptions.NotImplementedException;
import com.github.larchaon.loanapp.util.orika.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "clients/{clientId}/loans")
public class ClientLoanRestController {

    @Autowired
    ClientService clientService;

    @Autowired
    LoanService loanService;

    @Autowired
    DataMapper mapper;

    @RequestMapping(value = "", method = GET)
    public ResponseEntity<?> clientLoans(@PathVariable("clientId") long clientId, HttpServletRequest request) {
        Optional<Client> client$ = clientService.findClientById(clientId);
        if (!client$.isPresent()) return new ResponseEntity<>(NOT_FOUND);

        throw new NotImplementedException("Fetch client loans");
    }

    @RequestMapping(value = "", method = POST)
    public ResponseEntity<?> takeALoan(@PathVariable("clientId") long clientId, @Valid @RequestBody IssueLoanModel model) {
        Optional<Client> client$ = clientService.findClientById(clientId);
        if (!client$.isPresent()) return new ResponseEntity<>(NOT_FOUND);
        Loan loan = mapper.map(model, Loan.class);
        long loanId = loanService.requestALoan(loan, client$.get());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/loans/"+loanId);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
