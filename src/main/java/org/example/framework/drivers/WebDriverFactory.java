package org.example.framework.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.framework.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.EnumMap;
import java.util.Map;

/**
 * WebDriverFactory is a utility class that provides WebDriver instances based on the specified browser type.
 * It uses WebDriverManager for automatic driver setup and supports multiple browser types.
 */
public final class WebDriverFactory {

    // EnumMap that holds all the browser types and their corresponding WebDriver suppliers
    private static final Map<BrowserType, WebDriverSupplier> DRIVER_SUPPLIERS = new EnumMap<>(BrowserType.class);

    // Static block to initialize the DRIVER_SUPPLIERS map with browser types and their corresponding supplier classes
    static {
        DRIVER_SUPPLIERS.put(BrowserType.CHROME, new ChromeDriverSupplier());
        DRIVER_SUPPLIERS.put(BrowserType.FIREFOX, new FirefoxDriverSupplier());
        DRIVER_SUPPLIERS.put(BrowserType.EDGE, new EdgeDriverSupplier());
        DRIVER_SUPPLIERS.put(BrowserType.EXPLORER, new InternetExplorerDriverSupplier());
        DRIVER_SUPPLIERS.put(BrowserType.SAFARI, new SafariDriverSupplier());
    }

    // Private constructor to prevent instantiation
    private WebDriverFactory() {}

    /**
     * Returns a WebDriver instance based on the specified browser type.
     *
     * @param browserType the type of browser for which to get the WebDriver
     * @return WebDriver instance for the specified browser type
     * @throws UnsupportedOperationException if the specified browser type is not supported
     */
    public static WebDriver getBrowserDriver(BrowserType browserType) {
        WebDriverSupplier supplier = DRIVER_SUPPLIERS.get(browserType);
        if (supplier == null) {
            throw new UnsupportedOperationException("Unsupported browser type: " + browserType);
        }
        return supplier.get();
    }

    // WebDriverSupplier interface to define a method for getting WebDriver instances
    private interface WebDriverSupplier {
        WebDriver get();
    }

    // ChromeDriverSupplier class to provide ChromeDriver instances
    private static class ChromeDriverSupplier implements WebDriverSupplier {
        @Override
        public WebDriver get() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

    // FirefoxDriverSupplier class to provide FirefoxDriver instances
    private static class FirefoxDriverSupplier implements WebDriverSupplier {
        @Override
        public WebDriver get() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }

    // EdgeDriverSupplier class to provide EdgeDriver instances
    private static class EdgeDriverSupplier implements WebDriverSupplier {
        @Override
        public WebDriver get() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
    }

    // InternetExplorerDriverSupplier class to provide InternetExplorerDriver instances
    private static class InternetExplorerDriverSupplier implements WebDriverSupplier {
        @Override
        public WebDriver get() {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();
        }
    }

    // SafariDriverSupplier class to provide SafariDriver instances
    private static class SafariDriverSupplier implements WebDriverSupplier {
        @Override
        public WebDriver get() {
            WebDriverManager.safaridriver().setup();
            return new SafariDriver();
        }
    }
}
