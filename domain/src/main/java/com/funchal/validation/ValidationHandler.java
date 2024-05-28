package com.funchal.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);

    List<Error> getErrors();

    default boolean hasError(){
        return getErrors() != null && !getErrors().isEmpty();
    }

}
