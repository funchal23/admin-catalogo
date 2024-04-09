package com.funchal.domain.category;

import com.funchal.domain.AggregateRoot;
import com.funchal.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Category(final CategoryID id, final String name, final String description, final boolean isActive, final Instant createdAt,
        final Instant updatedAt, final Instant deletedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(final String name, final String description, final boolean isActive) {
        final var id = CategoryID.unique();
        final var time = Instant.now();
        final Instant deletedAt = isActive ? null : time;
        return new Category(id, name, description, isActive, time, time, deletedAt);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public void validate(final ValidationHandler validationHandler) {
        new CategoryValidator(this, validationHandler).validate();
    }

    public Category disable(){
        isActive = false;
        final Instant now = Instant.now();
        deletedAt = now;
        updatedAt = now;
        return this;
    }

    public Category active(){
        isActive = true;
        final Instant now = Instant.now();
        deletedAt = null;
        updatedAt = now;
        return this;
    }
}