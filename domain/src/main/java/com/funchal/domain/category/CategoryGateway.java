package com.funchal.domain.category;

import com.funchal.domain.pagination.Pagination;
import com.funchal.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category category);

    void deleteById(CategoryID categoryID);

    Pagination<Category> findAll(SearchQuery searchQuery);

    Optional<Category> findById(CategoryID categoryID);

    Category update(Category category);
}
