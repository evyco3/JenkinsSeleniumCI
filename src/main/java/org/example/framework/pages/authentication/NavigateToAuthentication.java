package org.example.framework.pages.authentication;

import org.example.framework.enums.LogType;
import org.example.framework.pages.BasePage;
import org.example.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigateToAuthentication extends BasePage {

    @FindBy(css = "a[href*='login']")
    private WebElement loginLink;
    @FindBy(css = "a[href*='register']")
    private WebElement registerLink;


    public RegisterPage navigateToRegisterPage(){
        clickNavigateAndLog(registerLink,"register link","Register","RegisterPage");
        return new RegisterPage();
    }

    public LoginPage navigateToLoginPage(){
        clickNavigateAndLog(loginLink,"login link","login","LoginPage");
        return new LoginPage();
    }


}
