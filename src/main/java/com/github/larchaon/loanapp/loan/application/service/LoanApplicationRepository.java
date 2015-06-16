package com.github.larchaon.loanapp.loan.application.service;

import com.github.larchaon.loanapp.loan.application.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    Optional<LoanApplication> findByPk(long pk);
}
