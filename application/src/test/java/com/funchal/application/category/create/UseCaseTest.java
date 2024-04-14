package com.funchal.application.category.create;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class UseCaseTest<USE_CASE> {

    @InjectMocks
    protected USE_CASE useCase;

}
