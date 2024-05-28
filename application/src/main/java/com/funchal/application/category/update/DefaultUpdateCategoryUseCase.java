package com.funchal.application.category.update;

import com.funchal.domain.category.Category;
import com.funchal.domain.category.CategoryGateway;
import com.funchal.domain.category.CategoryID;
import com.funchal.exceptions.NotFoundException;
import com.funchal.validation.Error;
import com.funchal.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Try;

import java.util.List;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{

    private final CategoryGateway gateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand updateCategoryCommand) {
        final var id = updateCategoryCommand.id();
        final var name = updateCategoryCommand.name();
        final var description = updateCategoryCommand.description();
        final var notification = Notification.create();
        CategoryID from = CategoryID.from(id);
        final var aCategory = gateway.findById(from).orElseThrow(notFound());
        Category aCategoryUpdated = aCategory.update(name, description);
        aCategoryUpdated.validate(notification);
        return notification.hasError() ? API.Left(notification) : updateCategory(aCategoryUpdated);
    }

    private Either<Notification, UpdateCategoryOutput> updateCategory(final Category category) {
        return Try.of(() -> this.gateway.update(category))
            .toEither()
            .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private Supplier<NotFoundException> notFound() {
        return () -> NotFoundException.with(List.of(new Error("Category not found")));
    }
}
