package com.orangehrm.test;

import com.orangehrm.base.Base;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends Base {

   private HomePage homePage;
   private LoginPage loginPage;


   @BeforeMethod
   public void setPages() {
   loginPage = new LoginPage(driver);
   homePage = new HomePage(driver);
   }

   @Test
   public void verifyValidLoginTest(){
       loginPage.login("admin","admin123");
       Assert.assertTrue(homePage.isAdminTabDisplayed(),"Admin Tab should be viible after sucessfull login ");
       homePage.logout();
       staticWait(2);
   }

   @Test
   public void inValidLoginTest(){
       loginPage.login("admin","admin");
       String expectedErrorText ="Invalid credentials";
       Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorText),"Test Failed: Invalid Error message");
   }



}
