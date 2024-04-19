package com.funchal.validation.handler;

import com.funchal.exceptions.DomainException;
import com.funchal.validation.Error;
import com.funchal.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<Error> errors;

    public Notification(final List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create(){
        return new Notification(new ArrayList<>());
    }

    public static Notification create(Throwable error){
        return new Notification(new ArrayList<>()).append(new Error(error.getMessage()));
    }

    public static Notification create(Error error){
        return new Notification(new ArrayList<>()).append(error);
    }

    @Override
    public Notification append(final Error error) {
        this.errors.add(error);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler validationHandler) {
        this.errors.addAll(validationHandler.getErrors());
        return this;
    }

    @Override
    public Notification validate(final Validation validation) {
        try {
            validation.validate();
        } catch (DomainException e) {
            this.errors.addAll(e.getErrors());
        } catch (Throwable e){
            this.errors.add(new Error(e.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }

}
