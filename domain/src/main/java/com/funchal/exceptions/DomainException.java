package com.funchal.exceptions;

import com.funchal.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException{

    private final List<Error> errors;

    protected DomainException(final List<Error> errors){
        super("message error", null, true, false);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public static DomainException with(final List<Error> errors){
        return new DomainException(errors);
    }

}

