package com.funchal.application.category.create;

import com.funchal.domain.category.Category;
import com.funchal.domain.category.CategoryGateway;
import com.funchal.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway gateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand categoryCommand) {
        final var name = categoryCommand.name();
        final var description = categoryCommand.description();
        final var isActive = categoryCommand.isActive();
        final var category = Category.newCategory(name, description, isActive);
        final var notification = Notification.create();
        category.validate(notification);
        return notification.hasError() ? API.Left(notification) : create(category);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category category) {
        return API.Try(() -> this.gateway.create(category))
            .toEither()
            .bimap(Notification::create,CreateCategoryOutput::from);
    }
}