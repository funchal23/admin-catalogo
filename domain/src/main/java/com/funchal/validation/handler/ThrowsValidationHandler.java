package com.funchal.validation.handler;

import com.funchal.exceptions.DomainException;
import com.funchal.validation.Error;
import com.funchal.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with(List.of(error));
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

}
