package com.github.larchaon.loanapp.util.exceptions;

import java.util.List;


public class LoanappValidationException extends RuntimeException {

    private final List<? extends ValidationError> errors;

    public LoanappValidationException(List<? extends ValidationError> errors) {
        this.errors = errors;
    }

    public List<? extends ValidationError> getErrors() {
        return errors;
    }
}