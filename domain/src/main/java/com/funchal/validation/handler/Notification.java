package com.funchal.validation.handler;

import com.funchal.validation.Error;
import com.funchal.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<Error> errors;

    private Notification(final List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create(){
        return new Notification(new ArrayList<>());
    }

    public static Notification create(Throwable error){
        return create(new Error(error.getMessage()));
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
    public List<Error> getErrors() {
        return this.errors;
    }

}
