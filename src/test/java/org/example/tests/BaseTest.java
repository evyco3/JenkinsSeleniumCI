package org.example.tests;

import org.example.framework.drivers.Driver;
import org.example.framework.enums.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup(){
        Driver.getInstance().initDriver(BrowserType.CHROME);
    }

    @AfterMethod
    public void tearDown(){
        Driver.getInstance().quitDriver();
    }
}
