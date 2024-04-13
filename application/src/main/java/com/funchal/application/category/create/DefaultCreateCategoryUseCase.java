package com.funchal.application.category.create;

import com.funchal.domain.category.CategoryGateway;
import com.funchal.domain.category.Category;
import com.funchal.validation.handler.ThrowsValidationHandler;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway gateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand categoryCommand) {
        final var name = categoryCommand.name();
        final var description = categoryCommand.description();
        final var isActive = categoryCommand.isActive();

        final var category = Category.newCategory(name, description, isActive);
        category.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.from(gateway.create(category));
    }
}
