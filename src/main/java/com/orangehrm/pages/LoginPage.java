package com.orangehrm.pages;

import com.orangehrm.actionDriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    private ActionDriver actionDriver;

    //Define locators
    private By userNameFiled= By.name("username");
    private By passwordFiled =By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMesg = By.xpath("//*[text()='Invalid credentials']");


    LoginPage(WebDriver driver)
    {
        this.actionDriver = new ActionDriver(driver);
    }


    public void login(String userName,String password){
     actionDriver.enterText(userNameFiled,"admin");
     actionDriver.enterText(passwordFiled,"admin123");
     actionDriver.click(loginButton);
    }

    public void isErrorMessageDisplayed(){
        actionDriver.isDisplayed(errorMesg);
    }

    public String getErrorMesgText(){
     return actionDriver.getText(errorMesg);
    }


    public void verifyErrorMeg(String expectedError){
    actionDriver.compareText(errorMesg,expectedError);
    }







}
