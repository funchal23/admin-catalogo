package com.funchal.application.category.update;

public record UpdateCategoryCommand (
    String id,
    String name,
    String description
){

    public static UpdateCategoryCommand with(String id, String name, String description) {
        return new UpdateCategoryCommand(id, name, description);
    }
}
