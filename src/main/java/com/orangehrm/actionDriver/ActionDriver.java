package com.orangehrm.actionDriver;

import com.orangehrm.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class ActionDriver
{

  private WebDriver driver;
  private WebDriverWait wait;


  public ActionDriver(WebDriver  driver)
  {
      this.driver=driver;
      int explicitWait = Integer.parseInt(Base.getProp().getProperty("explicitWait"));
      this.wait = new WebDriverWait(driver,Duration.ofSeconds(explicitWait));
  }

  public void waitForElementToBeClickable(By by) {
      try {
          wait.until(ExpectedConditions.elementToBeClickable(by));
      }
      catch (Exception e) {
          System.out.println("element is not clickable: "+e.getMessage());
      }
  }

  public void waitForElementToBeVisible(By by) {
      try {
          wait.until(ExpectedConditions.visibilityOfElementLocated(by));
      }
      catch (Exception e) {
          System.out.println("element is not visible: "+e.getMessage());
      }
  }

  public void click(By by)
  {
      try {
          waitForElementToBeClickable(by);
          driver.findElement(by).click();
      }
      catch (Exception e){
          System.out.println("element is not Clickable: "+e.getMessage());
      }
  }

  public void enterText(By by,String value) {
      try {
          waitForElementToBeVisible(by);
         WebElement element = driver.findElement(by);
         element.clear();
         element.sendKeys(value);
      }
      catch (Exception e){
          System.out.println("Unable to enter the value: "+e.getMessage());
      }
  }

  public String getText(By by) {
      try {
          waitForElementToBeVisible(by);
          return  driver.findElement(by).getText();
      }
      catch (Exception e) {
          System.out.println("Unable to get the text from element "+e.getMessage());
          return "";
      }
  }

  public boolean compareText(By by,String expectedText) {
      String actualText ="";
      try {
          waitForElementToBeVisible(by);
           actualText = driver.findElement(by).getText();
         if(expectedText.equals(actualText)) {
             System.out.println("Text are Matching: "+actualText+" equals "+expectedText);
           return true;
         }
         else {
             System.out.println("Text are not Matching: "+actualText+" equals "+expectedText);
           return false;
         }
      }
      catch (Exception e){
          System.out.println("Unable to compare Texts: "+e.getMessage());
      }
      return false;
  }

  public boolean isDisplayed(By by) {
      try{
          waitForElementToBeVisible(by);
          return driver.findElement(by).isDisplayed();
      }
      catch (Exception e){
          System.out.println("Element is not displayed: "+e.getMessage());
          return  false;
      }
  }

  public void scrollToElement(By by) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          WebElement element = driver.findElement(by);
          js.executeScript("arguments[0],scrollIntoView(true)",element);
      }
      catch (Exception e){
          System.out.println("Unable to locate element: "+e.getMessage());
      }
  }



}
