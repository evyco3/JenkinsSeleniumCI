package org.example.framework.pages;

import org.example.framework.drivers.Driver;
import org.example.framework.enums.LogType;
import org.example.framework.utils.ActionUtils;
import org.example.framework.utils.LoggerUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver;

    public BasePage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver,this);
    }


    protected WebElement waitForElementToBeVisible(WebElement element) {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(8))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class)
                    .ignoring(ElementNotInteractableException.class);
            return wait.until(ExpectedConditions.visibilityOf(element));

    }

    protected void waitForPageTitleToContain(String pageTitle){
        ActionUtils.execAction(getClass(),()->
                        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.titleContains(pageTitle)),
                "Waited for pageTitle to be "+pageTitle+" Success",
                "PageTitle is not matched to "+pageTitle
        );
    }


    protected void sendKeys(WebElement element,String elementName,String value){
        ActionUtils.execAction(getClass(),()->
                waitForElementToBeVisible(element).sendKeys(value),
                "Sent keys to "+elementName,
                "Failed to sent keys to "+elementName
                );
    }

    protected void click(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", waitForElementToBeVisible(element));
        }, "Clicked on element: " + elementName, "Failed to click on element: " + elementName);

    }

    protected String getText(WebElement element,String elementName){
       return ActionUtils.execFunction(getClass(),()->
                waitForElementToBeVisible(element).getText().trim(),
                "Retrieve Text from "+elementName+":"+element.getText(),
                "Failed to Retrieve Text from "+elementName
                );
    }
    protected void clickNavigateAndLog(WebElement element,String elementName,String pageTitle,String className){
        try{
            click(element,elementName);
            waitForPageTitleToContain(pageTitle);
            LoggerUtils.log(getClass(), LogType.INFO,"Navigate to "+className);
        }catch (Exception e){
            LoggerUtils.log(getClass(),LogType.ERROR,"Failed to Navigate to "+className+e);
            throw new RuntimeException("Failed to navigate to "+className,e);
        }
    }



}
