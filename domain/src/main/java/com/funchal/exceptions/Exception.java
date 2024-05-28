package com.funchal.exceptions;

import com.funchal.validation.Error;

import java.util.List;

public class Exception extends RuntimeException {

    private List<Error> errors;

    protected Exception(final List<Error> errors){
        super("message error", null, true, false);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
