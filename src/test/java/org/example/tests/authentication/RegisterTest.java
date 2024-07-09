package org.example.tests.authentication;

import org.example.framework.pages.home.HomePage;
import org.example.tests.BaseTest;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {


    @Test
    public void testUserRegistrationScenarios(){
        performRegistrationAndGetResponseMsg();
    }




    private void performRegistrationAndGetResponseMsg(){
        HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegisterPage();
    }
}
