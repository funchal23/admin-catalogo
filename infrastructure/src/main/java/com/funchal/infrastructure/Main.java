package com.funchal.infrastructure;

import com.funchal.application.UseCase;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!");
        System.out.println(new UseCase().execute());
    }

}