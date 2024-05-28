package com.funchal.application.category.update;

import com.funchal.domain.category.Category;

public record UpdateCategoryOutput(
    String id,
    String name,
    String description
) {

    public static UpdateCategoryOutput from(Category category){
        return new UpdateCategoryOutput(category.getId().getValue(), category.getName(), category.getDescription());
    }
}
