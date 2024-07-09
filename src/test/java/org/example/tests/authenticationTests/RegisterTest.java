package org.example.tests.authenticationTests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.example.framework.data.RegisterData;
import org.example.framework.pages.authentication.RegisterPage;
import org.example.framework.pages.home.HomePage;
import org.example.tests.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.example.framework.utils.AssertionUtils.assertEquality;

@Feature("User Registration")
public class RegisterTest extends BaseTest {

    @Test(dataProvider = "registerData", dataProviderClass = RegisterData.class)
    @Parameters({"firstName", "lastName", "email", "password", "verification","operation", "expectedMessage"})
    @Story("User Registration Scenarios")
    @Description("Test various user registration scenarios with different sets of data.")
    public void testUserRegistrationScenarios(String firstName, String lastName, String email, String password, String verification, String operation, String expectedMessage) {
        String actualMessage = performRegistrationAndGetResponseMsg(firstName, lastName, email, password, verification, operation);
        assertEquality(actualMessage, expectedMessage, "Verify if actualMessage equals to expected message");
    }

    @Step("Perform registration and get response message")
    private String performRegistrationAndGetResponseMsg(String firstName, String lastName, String email, String password, String verification, String operation) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegisterPage()
                .performRegistration(firstName, lastName, email, password, verification, false, RegisterPage.class)
                .getRegisterResponseMsg(operation);
    }
}
