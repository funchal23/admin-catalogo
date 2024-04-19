package com.funchal.application.category.create;

import com.funchal.domain.category.Category;

public record CreateCategoryOutput(
    String id
) {

    public static CreateCategoryOutput from(Category category){
        return new CreateCategoryOutput(category.getId().getValue());
    }

}
