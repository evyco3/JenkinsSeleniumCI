package org.example.framework.drivers;

import org.example.framework.enums.BrowserType;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * Driver is a singleton class that manages WebDriver instances using ThreadLocal to ensure thread safety.
 */
public final class Driver {

    // ThreadLocal variable to store WebDriver instances for each thread
    private static final ThreadLocal<WebDriver> THREAD_LOCAL_DRIVER = new ThreadLocal<>();

    // Singleton instance
    private static final Driver INSTANCE = new Driver();

    // Private constructor to prevent instantiation
    private Driver() {}

    /**
     * Returns the singleton instance of the Driver class.
     *
     * @return the singleton instance of the Driver class
     */
    public static Driver getInstance() {
        return INSTANCE;
    }

    /**
     * Initializes the WebDriver for the current thread based on the specified browser type.
     *
     * @param browserType the type of browser to initialize the WebDriver for
     */
    public void initDriver(BrowserType browserType) {
        if (THREAD_LOCAL_DRIVER.get() == null) {
            WebDriver driver = WebDriverFactory.getBrowserDriver(browserType);
            THREAD_LOCAL_DRIVER.set(driver);
            configureDriver(driver);
        }
    }

    /**
     * Quits the WebDriver instance for the current thread and removes it from ThreadLocal storage.
     */
    public void quitDriver() {
        if (THREAD_LOCAL_DRIVER.get() != null) {
            THREAD_LOCAL_DRIVER.get().quit();
            THREAD_LOCAL_DRIVER.remove();
        }
    }

    /**
     * Configures the WebDriver instance with common settings.
     *
     * @param driver the WebDriver instance to configure
     */
    private void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        driver.get("https://demo.sylius.com/en_US/");
    }

    /**
     * Retrieves the WebDriver instance for the current thread.
     *
     * @return the WebDriver instance for the current thread
     */
    public WebDriver getDriver() {
        return THREAD_LOCAL_DRIVER.get();
    }
}
