package org.example.framework.pages.authentication;

import org.example.framework.enums.LogType;
import org.example.framework.pages.BasePage;
import org.example.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "#_username")
    private WebElement email;
    @FindBy(css = "#_password")
    private WebElement password;
    @FindBy(css = "button.ui.blue.submit.button")
    private WebElement loginBtn;
    @FindBy(css = "div.top-bar div.item")
    private WebElement successLoginMsg;
    @FindBy(css = "div.negative.message p")
    private WebElement failLoginMsg;


    public <T>T performLogin(String email,String password,boolean criteria,Class<T>nextPageClass){
        try{
            sendKeys(this.email,"email",email);
            sendKeys(this.password,"password",password);
            click(this.loginBtn,"login button");
            if(criteria){
                waitForPageTitleToContain("");
                LoggerUtils.log(getClass(), LogType.INFO,"Login Success ,Moving to HomePage");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to complete login process "+e);
            throw new RuntimeException("Fail to operate login operation",e);
        }

    }

    public String getLoginResponseMessage(String operation){
        return switch (operation){
            case "valid login"->getText(this.successLoginMsg,"success login message");
            case "invalid email"->getText(this.failLoginMsg,"invalid email message");
            case "invalid password"->getText(this.failLoginMsg,"invalid password message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
