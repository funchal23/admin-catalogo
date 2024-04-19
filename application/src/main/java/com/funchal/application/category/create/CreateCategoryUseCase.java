package com.funchal.application.category.create;

import com.funchal.application.UseCase;
import com.funchal.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
