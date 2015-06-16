package com.github.larchaon.loanapp.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/loans")
public class LoanRestController {

    @Autowired
    LoanService loanService;

    @RequestMapping(value = "/{loanId}", method = GET)
    public ResponseEntity<?> findLoanById(@PathVariable("loanId") long loanId) {
        Optional<Loan> loan$ = loanService.findLoanById(loanId);

        if (loan$.isPresent()) {
            return new ResponseEntity<>(loan$.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
