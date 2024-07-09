package org.example.framework.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public final class RegisterData {

    private static final Faker faker =new Faker();


    private RegisterData(){}


    @DataProvider(name = "registerData")
    public static Object[][]getData(){
        String password=getPassword();
        return new Object[][]{
                {getFirstName(),getLastName(),getEmail(),password,password,"valid registration","Thank you for your registration"},
                {getFirstName(),getLastName(),"email.com",password,password,"invalid email format","This email is invalid."},
                {getFirstName(),getLastName(),"fashion@example.com",password,password,"invalid email already in use","This email is already used."},
                {getFirstName(),getLastName(),getEmail(),"1234567","a1b1c1","invalid passwords mismatch","The entered passwords don't match"},
                {getFirstName(),getLastName(),getEmail(),"123","123","invalid passwords format","Password must be at least 4 characters long."}

        };
    }








    private static String getFirstName(){
        return faker.name().firstName();
    }

    private static String getLastName(){
        return faker.name().lastName();
    }

    private static String getEmail(){
        return faker.internet().emailAddress();
    }
    private static String getPassword(){
        return faker.internet().password(6,10,true,true,true);
    }
}
