package com.orangehrm.test;

import com.orangehrm.base.Base;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends Base {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupPages(){
        this.loginPage = new LoginPage();
        this.homePage = new HomePage();
    }

    @Test
    public void verifyOrangeHRMLogo(){
        loginPage.login("admin","admin123");
        staticWait(2);
        Assert.assertTrue(homePage.verifyOrgangeHRMLogo(),"Logo is not visible");
    }

}
