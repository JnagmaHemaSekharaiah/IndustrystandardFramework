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

import org.testng.annotations.*;;

public class Base
{
   protected Properties prop;
   protected WebDriver  driver;


   @BeforeMethod
   public void setUp() throws IOException
   {
     // Load Configuration
     prop = new Properties();
     try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
         prop.load(fis);
     }

     //Initialize the browser
     String browser = prop.getProperty("browser").trim();
      if (browser.equalsIgnoreCase("chrome")) {
          driver= new ChromeDriver();
      }
      else if (browser.equalsIgnoreCase("firefox")) {
          driver= new FirefoxDriver();
      }
      else {
          throw new IllegalArgumentException("Browser not supported.."+browser);
      }

      //Implicit Wait
      int implicitWait = Integer.parseInt(prop.getProperty("implicitwait"));
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

      //maximize window
      driver.manage().window().maximize();

      //Navigate to URL
       driver.get(prop.getProperty("url"));

   }


   @AfterMethod
   public void tearDown()
   {
       if (driver!=null)
       {
           driver.quit();
       }
   }



}
