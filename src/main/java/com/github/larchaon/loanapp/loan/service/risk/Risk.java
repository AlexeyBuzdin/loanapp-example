package com.github.larchaon.loanapp.loan.service.risk;

import com.github.larchaon.loanapp.util.exceptions.ValidationError;

public class Risk implements ValidationError {

    private final String message;

    public Risk(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
