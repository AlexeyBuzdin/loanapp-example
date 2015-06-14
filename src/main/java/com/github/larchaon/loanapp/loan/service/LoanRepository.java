package com.github.larchaon.loanapp.loan.service;

import com.github.larchaon.loanapp.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByPk(long pk);
}
