package com.funchal.application.category.create;

import com.funchal.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class CreateCategoryUseCaseTest {

    @Test
    void shouldSuccessCreateCategory() {
        final var expectedName = "Action";
        final var expectedDescription = "Best category";
        final var expectedIsActive = true;

        final var categoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final CategoryGateway gateway = mock(CategoryGateway.class);
        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase(gateway);

        final var categoryCreated = useCase.execute(categoryCommand);

        Assertions.assertNotNull(categoryCreated.id());

        Mockito.verify(gateway, times(1)).create(argThat(
            aCategory -> Objects.equals(expectedName, aCategory.getName()) && Objects.equals(expectedDescription,
                aCategory.getDescription()) && Objects.equals(expectedIsActive, aCategory.isActive()) && Objects.nonNull(
                aCategory.getId()) && Objects.nonNull(aCategory.getCreatedAt()) && Objects.nonNull(aCategory.getUpdatedAt()) && Objects.isNull(
                aCategory.getDeletedAt())));
    }

}
