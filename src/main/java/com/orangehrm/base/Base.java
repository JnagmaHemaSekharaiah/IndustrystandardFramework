package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.testng.annotations.*;;

public class Base {

    protected static Properties prop;
    protected static WebDriver driver;

    @BeforeSuite
    public void loadConfig() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void launchBroswer() {
        String browser = prop.getProperty("browser").trim();
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported.." + browser);
        }
    }

    private void configBroswer() {
        //Implicit Wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitwait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        //maximize window
        driver.manage().window().maximize();

        //Navigate to URL
        try {
            driver.get(prop.getProperty("url"));
        } catch (Exception e) {
            System.out.println("Failed to navigate the URL " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setUp() throws IOException {
        System.out.println("Setting up WebDriver for:" + this.getClass().getSimpleName());
        launchBroswer();
        configBroswer();
        staticWait(2);
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("unable to quit the driver" + e.getMessage());
            }
        }
    }

    public void staticWait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }

    public void setDriver(WebDriver driver) {
        this.driver =driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

   public static Properties getProp() {
       return prop;
   }



}
