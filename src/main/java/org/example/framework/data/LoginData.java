package org.example.framework.data;

import org.testng.annotations.DataProvider;

public final class LoginData {

    private LoginData(){}



    @DataProvider(name = "loginData")
    public static Object[][]getData(){
        return new Object[][]{
                {"fashion@example.com","sylius","valid login","Hello Lyla Herman!"},
                {"unknownEmail@walla.com","sylius","Invalid credentials."},
                {"fashion@example.com","wrongPassword","invalid password","Invalid credentials."},
        };
    }
}
