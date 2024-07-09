package org.example.framework.pages.authentication;

import org.example.framework.enums.LogType;
import org.example.framework.pages.BasePage;
import org.example.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(css = "#sylius_customer_registration_firstName")
    private WebElement firstName;
    @FindBy(css = "#sylius_customer_registration_lastName")
    private WebElement lastName;
    @FindBy(css = "#sylius_customer_registration_email")
    private WebElement email;
    @FindBy(css = "#sylius_customer_registration_user_plainPassword_first")
    private WebElement password;
    @FindBy(css = "#sylius_customer_registration_user_plainPassword_second")
    private WebElement verification;
    @FindBy(css = "button.ui.large.primary.button")
    private WebElement registerBtn;
    @FindBy(css = "i.circular.smile.icon")
    private WebElement successImageIcon;
    @FindBy(css = "i.circular.smile.icon+div")
    private WebElement successResponseMsg;
    @FindBy(css = "div.sylius-validation-error")
    private WebElement failResponseMsg;

    public  <T>T performRegistration(String firstName, String lastName, String email, String password, String verification, boolean criteria, Class<T>nextPageClass){
        try{
            sendKeys(this.firstName,"firstName",firstName);
            sendKeys(this.lastName,"lastName",lastName);
            sendKeys(this.email,"email",email);
            sendKeys(this.password,"password",password);
            sendKeys(this.verification,"verification",verification);
            click(this.registerBtn,"register button");
            if(criteria){
                waitForElementToBeVisible(this.successImageIcon);
                LoggerUtils.log(getClass(),LogType.INFO,"Registration success");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to complete registration process "+e);
            throw new RuntimeException("Error during registration",e);
        }
    }


    public String getRegisterResponseMsg(String operation){
        return switch (operation){
            case "valid registration"->getText(this.successResponseMsg,"success response message");
            case "invalid email format"->getText(this.failResponseMsg,"invalid email format response message");
            case "invalid email already in use"->getText(this.failResponseMsg,"invalid email already use");
            case "invalid passwords mismatch"->getText(this.failResponseMsg,"invalid mismatch response message");
            case "invalid passwords format"->getText(this.failResponseMsg,"invalid passwords format response message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
