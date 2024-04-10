package com.funchal.domain;

import com.funchal.domain.category.Category;
import com.funchal.exceptions.DomainException;
import com.funchal.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class CategoryTest {

    @Test
    void shouldSuccessNewCategory(){

        final var expectedName = "Terror";
        final var expectedDescription = "best category";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertNotNull(aCategory);
        Assertions.assertEquals(aCategory.getName(), expectedName);
        Assertions.assertEquals(aCategory.getDescription(), expectedDescription);
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getCreatedAt());
        Assertions.assertNotNull(aCategory.getUpdatedAt());
        Assertions.assertNull(aCategory.getDeletedAt());

    }

    @Test
    void shouldFailNewCategoryWithNameNull(){

        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedMessageError = "message error";
        final var expectedDescription = "best category";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final DomainException aException = Assertions.assertThrows(DomainException.class,
            () -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, aException.getErrors().size());
        Assertions.assertEquals(expectedMessageError, aException.getErrors().get(0).message());
    }

    @Test
    void shouldFailNewCategoryWithNameEmpty(){

        final String expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedMessageError = "message error";
        final var expectedDescription = "best category";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final DomainException aException = Assertions.assertThrows(DomainException.class,
            () -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, aException.getErrors().size());
        Assertions.assertEquals(expectedMessageError, aException.getErrors().get(0).message());
    }

    @Test
    void shouldFailNewCategoryWithNameLengthLessThan3(){

        final String expectedName = "Lu ";
        final var expectedErrorCount = 1;
        final var expectedMessageError = "message error";
        final var expectedDescription = "best category";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final DomainException aException = Assertions.assertThrows(DomainException.class,
            () -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, aException.getErrors().size());
        Assertions.assertEquals(expectedMessageError, aException.getErrors().get(0).message());
    }

    @Test
    void shouldFailNewCategoryWithNameLengthMoreThan255(){

        final String expectedName =
            "Ainda assim, existem dúvidas a respeito de como a crescente influência da mídia prepara-nos para enfrentar situações atípicas decorrentes das condições inegavelmente apropriadas. A nível organizacional, o comprometimento entre as equipes agrega valor ao estabelecimento do levantamento das variáveis envolvidas. Por conseguinte, a constante divulgação das informações afeta positivamente a correta previsão de alternativas às soluções ortodoxas. Percebemos, cada vez mais, que o surgimento do comércio virtual promove a alavancagem das diretrizes de desenvolvimento para o futuro.";
        final var expectedErrorCount = 1;
        final var expectedMessageError = "message error";
        final var expectedDescription = "best category";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final DomainException aException = Assertions.assertThrows(DomainException.class,
            () -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, aException.getErrors().size());
        Assertions.assertEquals(expectedMessageError, aException.getErrors().get(0).message());
    }

    @Test
    void shouldSuccessNewCategoryWithDescriptionEmpty(){

        final String expectedName = "Lucas";
        final var expectedDescription = " ";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(aCategory);
        Assertions.assertEquals(aCategory.getName(), expectedName);
        Assertions.assertEquals(aCategory.getDescription(), expectedDescription);
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getCreatedAt());
        Assertions.assertNotNull(aCategory.getUpdatedAt());
        Assertions.assertNull(aCategory.getDeletedAt());
    }


    @Test
    void shouldSuccessNewCategoryWithIsActiveFalse(){

        final String expectedName = "Lucas";
        final var expectedDescription = "Description";
        final var expectedActive = false;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(aCategory);
        Assertions.assertEquals(aCategory.getName(), expectedName);
        Assertions.assertEquals(aCategory.getDescription(), expectedDescription);
        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getCreatedAt());
        Assertions.assertNotNull(aCategory.getUpdatedAt());
        Assertions.assertNotNull(aCategory.getDeletedAt());
    }

    @Test
    void shouldSuccessDisableCategory(){

        final var expectedName = "Terror";
        final var expectedDescription = "best category";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final Instant updatedAt = aCategory.getUpdatedAt();

        final var aNewCategory = aCategory.disable();

        Assertions.assertNotNull(aNewCategory.getDeletedAt());
        Assertions.assertFalse(aNewCategory.isActive());
        Assertions.assertNotNull(aNewCategory);
        Assertions.assertEquals(aNewCategory.getName(), expectedName);
        Assertions.assertEquals(aNewCategory.getDescription(), expectedDescription);
        Assertions.assertNotNull(aNewCategory.getCreatedAt());
        Assertions.assertNotNull(aNewCategory.getUpdatedAt());
        Assertions.assertTrue(aNewCategory.getUpdatedAt().isAfter(updatedAt));
    }

    @Test
    void shouldSuccessActiveCategory(){

        final var expectedName = "Terror";
        final var expectedDescription = "best category";
        final var expectedActive = false;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var aNewCategory = aCategory.active();

        Assertions.assertNull(aNewCategory.getDeletedAt());
        Assertions.assertTrue(aNewCategory.isActive());
        Assertions.assertNotNull(aNewCategory);
        Assertions.assertEquals(aNewCategory.getName(), expectedName);
        Assertions.assertEquals(aNewCategory.getDescription(), expectedDescription);
        Assertions.assertNotNull(aNewCategory.getCreatedAt());
        Assertions.assertNotNull(aNewCategory.getUpdatedAt());
    }

    @Test
    void shouldSuccessUpdatedCategory(){
        final var expectedName = "Terror";
        final var expectedDescription = "best category";

        final var aCategory = Category.newCategory("Action", "Must be available", true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var aNewCategory = aCategory.update(expectedName, expectedDescription);

        Assertions.assertNull(aNewCategory.getDeletedAt());
        Assertions.assertTrue(aNewCategory.isActive());
        Assertions.assertNotNull(aNewCategory);
        Assertions.assertEquals(aNewCategory.getName(), expectedName);
        Assertions.assertEquals(aNewCategory.getDescription(), expectedDescription);
        Assertions.assertNotNull(aNewCategory.getCreatedAt());
        Assertions.assertNotNull(aNewCategory.getUpdatedAt());
    }
}
