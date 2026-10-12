package com.orangehrm.pages;

import com.orangehrm.actionDriver.ActionDriver;
import com.orangehrm.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    private ActionDriver actionDriver;

    //Define locators
    private By userNameFiled = By.name("username");
    private By passwordFiled = By.name("password");
    private By loginButton   = By.xpath("//button[@type='submit']");
    private By errorMesg     = By.xpath("//*[text()='Invalid credentials']");


    public LoginPage() {
        this.actionDriver = Base.getActionDriver();
    }


    public void login(String userName,String password){
     actionDriver.enterText(userNameFiled,userName);
     actionDriver.enterText(passwordFiled,password);
     actionDriver.click(loginButton);
    }

    public void isErrorMessageDisplayed(){
        actionDriver.isDisplayed(errorMesg);
    }

    public String getErrorMessageText(){
     return actionDriver.getText(errorMesg);
    }


    public boolean verifyErrorMessage(String expectedError){
    return actionDriver.compareText(errorMesg,expectedError);
    }






}
