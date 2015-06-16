package com.github.larchaon.loanapp.transaction;

import com.github.larchaon.loanapp.util.RequestHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    TransactionRepository repository;

    @Autowired
    RequestHelperService requestHelperService;

    public void saveTransaction(Transaction application) {
        repository.save(application);
    }

    public Transaction loadTransactionForDate(Date createdOn) {
        String remoteAddress = requestHelperService.getRequestRemoteAddress();
        Optional<Transaction> trx = repository.findByCreatedOnAndRemoteAddress(createdOn, remoteAddress);
        return trx.orElseGet(Transaction::new);
    }
}
