package com.funchal.application;

import com.funchal.domain.category.Category;

public class UseCase {

    public Category execute(){
        return Category.newCategory("name", "description", false);
    }
}