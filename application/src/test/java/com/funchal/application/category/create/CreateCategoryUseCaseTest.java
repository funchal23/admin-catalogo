package com.funchal.application.category.create;

import com.funchal.domain.category.CategoryGateway;
import com.funchal.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {


    @Mock
    private CategoryGateway gateway;

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;


    // 1. Teste do caminho feliz
    // 2. Teste passando uma propriedade inválida (name)
    // 3. Teste criando uma categoria inativa
    // 4. Teste simulando um erro generico vindo do gateway

    @Test
    void shouldSuccessCreateCategory() {
        final var expectedName = "Action";
        final var expectedDescription = "Best category";
        final var expectedIsActive = true;

        final var categoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var categoryCreated = useCase.execute(categoryCommand);

        Assertions.assertNotNull(categoryCreated.id());

        Mockito.verify(gateway, times(1)).create(argThat(
            aCategory -> Objects.equals(expectedName, aCategory.getName()) && Objects.equals(expectedDescription,
                aCategory.getDescription()) && Objects.equals(expectedIsActive, aCategory.isActive()) && Objects.nonNull(
                aCategory.getId()) && Objects.nonNull(aCategory.getCreatedAt()) && Objects.nonNull(aCategory.getUpdatedAt()) && Objects.isNull(
                aCategory.getDeletedAt())));
    }

    @Test
    void shouldFailCreateCategoryWithNameInvalid(){
        final String expectedName = null;
        final var expectedDescription = "Best category";
        final var expectedIsActive = true;
        final var expectedErrorCount = 1;

        final CreateCategoryCommand category = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final DomainException domainException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(category));

        Assertions.assertEquals(expectedErrorCount, domainException.getErrors().size());
    }

    @Test
    void shouldSuccessCreateCategoryDisable() {
        final var expectedName = "Action";
        final var expectedDescription = "Best category";
        final var expectedIsActive = false;

        final var categoryCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var categoryCreated = useCase.execute(categoryCommand);

        Assertions.assertNotNull(categoryCreated.id());

        Mockito.verify(gateway, times(1)).create(argThat(
            aCategory -> Objects.equals(expectedName, aCategory.getName()) && Objects.equals(expectedDescription,
                aCategory.getDescription()) && Objects.equals(expectedIsActive, aCategory.isActive()) && Objects.nonNull(
                aCategory.getId()) && Objects.nonNull(aCategory.getCreatedAt()) && Objects.nonNull(aCategory.getUpdatedAt()) && Objects.nonNull(
                aCategory.getDeletedAt())));
    }

    @Test
    void shouldFailCreateCategory(){
        final var expectedName = "Terror";
        final var expectedDescription = "Best category";
        final var expectedIsActive = true;
        final var expectedMessageError = "Error for create";

        final CreateCategoryCommand category = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(gateway.create(any())).thenThrow(new IllegalAccessError(expectedMessageError));

        final var error = Assertions.assertThrows(IllegalAccessError.class, () -> useCase.execute(category));

        Assertions.assertEquals(error.getMessage(), expectedMessageError);

        Mockito.verify(gateway, times(1)).create(argThat(
            aCategory -> Objects.equals(expectedName, aCategory.getName()) && Objects.equals(expectedDescription,
                aCategory.getDescription()) && Objects.equals(expectedIsActive, aCategory.isActive()) && Objects.nonNull(
                aCategory.getId()) && Objects.nonNull(aCategory.getCreatedAt()) && Objects.nonNull(aCategory.getUpdatedAt()) && Objects.isNull(
                aCategory.getDeletedAt())));

    }
}
