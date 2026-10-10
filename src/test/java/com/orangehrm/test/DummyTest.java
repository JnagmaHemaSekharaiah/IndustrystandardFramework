package com.orangehrm.test;

import com.orangehrm.base.Base;
import org.testng.annotations.Test;

public class DummyTest extends Base
{

    @Test
    public void dummyTest()
    {
        String title = driver.getTitle();
        assert title.equals("OrangeHRM") : "Test Failed - Title is not matching";

        System.out.println("Test Passed - Title matching");
    }



}
