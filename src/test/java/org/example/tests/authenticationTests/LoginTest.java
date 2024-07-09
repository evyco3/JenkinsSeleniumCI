package org.example.tests.authenticationTests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.framework.data.LoginData;
import org.example.framework.pages.authentication.LoginPage;
import org.example.framework.pages.home.HomePage;
import org.example.framework.utils.AssertionUtils;
import org.example.tests.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Feature("User Login")
public class LoginTest extends BaseTest {


    @Test(dataProvider = "loginData",dataProviderClass = LoginData.class)
    @Parameters({"email", "password", "operation", "expectedResult"})
    @Story("Login scenarios")
    @Description("Test different login scenarios with different sets of data")
    public void testUserLoginScenarios(String email,String password,String operation,String expectedResult){
        String actualMessage=performLoginAndGetResponseMessage(email,password,operation);
        AssertionUtils.assertEquality(actualMessage,expectedResult,"verify if actualLoginMessage is Equals To expectedLoginMessage");
    }






    private String performLoginAndGetResponseMessage(String email,String password,String operation){
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToLoginPage()
                .performLogin(email,password,false, LoginPage.class)
                .getLoginResponseMessage(operation);

    }
}
