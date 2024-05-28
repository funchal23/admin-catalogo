package com.funchal.exceptions;

import com.funchal.validation.Error;

import java.util.List;

public class DomainException extends Exception {

    protected DomainException(final List<Error> errors){
        super(errors);
    }

    public static DomainException with(final List<Error> errors){
        return new DomainException(errors);
    }
}

