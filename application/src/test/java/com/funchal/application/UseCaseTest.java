package com.funchal.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseCaseTest {

    @Test
    void testReturnCategory(){
        Assertions.assertNotNull(new UseCase().execute());
    }


}
