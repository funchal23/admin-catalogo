package com.funchal.exceptions;

import com.funchal.validation.Error;

import java.util.List;

public class NotFoundException extends Exception {

    protected NotFoundException(List<Error> errors) {
        super(errors);
    }

    public static NotFoundException with(final List<Error> errors){
        return new NotFoundException(errors);
    }
}
