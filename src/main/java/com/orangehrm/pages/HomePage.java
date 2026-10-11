package com.orangehrm.pages;

import com.orangehrm.actionDriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {


    private ActionDriver actionDriver;

    private By adminTab      =  By.xpath("//*[text()='Admin']");
    private By userIDButton  =  By.xpath("//p[@class='oxd-userdropdown-name']");
    private By logoutButton  =  By.xpath("//a[text()='Logout']");
    private By orangehrmLogo =  By.xpath("//div[@class='oxd-brand-banner']");


    public HomePage(WebDriver driver) {
        this.actionDriver= new ActionDriver(driver);
    }

    public boolean isAdminTabDisplayed(){
        return actionDriver.isDisplayed(adminTab) ;
    }

    public boolean verifyOrgangeHRMLogo(){
        return actionDriver.isDisplayed(orangehrmLogo);
    }

   public void logout(){
        actionDriver.click(userIDButton);
        actionDriver.click(logoutButton);
   }


}
