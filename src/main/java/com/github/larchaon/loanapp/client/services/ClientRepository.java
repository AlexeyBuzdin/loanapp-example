package com.github.larchaon.loanapp.client.services;

import com.github.larchaon.loanapp.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(long id);
}
