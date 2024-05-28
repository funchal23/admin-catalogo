package com.funchal.application.category.update;

import com.funchal.application.UseCase;
import com.funchal.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>>{
}
