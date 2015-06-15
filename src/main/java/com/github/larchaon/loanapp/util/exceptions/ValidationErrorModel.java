package com.github.larchaon.loanapp.util.exceptions;

import java.util.List;

public class ValidationErrorModel {
    private List<ValidationError> errors;

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
