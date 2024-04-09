package com.funchal.domain.category;

import com.funchal.validation.Error;
import com.funchal.validation.ValidationHandler;
import com.funchal.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler validationHandler) {
        super(validationHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final String name = this.category.getName();
        if (name == null){
            this.getValidationHandler().append(new Error("message error"));
            return;
        }

        if(name.isBlank()){
            this.getValidationHandler().append(new Error("message error"));
            return;
        }

        final int length = name.trim().length();
        if (length > 255 || length < 3){
            this.getValidationHandler().append(new Error("message error"));
        }
    }

}
