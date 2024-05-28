package com.funchal.application.category.update;


import com.funchal.domain.category.Category;
import com.funchal.domain.category.CategoryGateway;
import com.funchal.domain.category.CategoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUseCaseTest {

    // 1. Teste do caminho feliz
    // 2. Teste passando uma propriedade inválida (name)
    // 3. Teste atualizando uma categoria inativa
    // 4. Teste simulando um erro generico vindo do gateway
    // 5. Teste passando id invalido

    @Mock
    private CategoryGateway gateway;

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Test
    void shouldSuccessUpdateCategory() {
        final var aCategory = Category.newCategory("Action", "Best category", true);

        final var expectedName = "Terror";
        final var expectedDescription = "Best";
        final var expectedIsActive = true;
        final var id = aCategory.getId();

        final var categoryCommand =
                UpdateCategoryCommand.with(id.getValue(), expectedName, expectedDescription);

        when(gateway.findById(eq(id))).thenReturn(Optional.of(aCategory));
        when(gateway.update(any())).thenAnswer(returnsFirstArg());

        final var categoryOutput = useCase.execute(categoryCommand).get();

        Assertions.assertEquals(expectedName, categoryOutput.name());
        Assertions.assertEquals(expectedDescription, categoryOutput.description());

        verify(gateway, times(1)).findById(eq(id));
    }
}
