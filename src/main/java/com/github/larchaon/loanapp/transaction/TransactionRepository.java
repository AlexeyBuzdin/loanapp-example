package com.github.larchaon.loanapp.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByCreatedOnAndRemoteAddress(Date createdOn, String remoteAddress);
}
