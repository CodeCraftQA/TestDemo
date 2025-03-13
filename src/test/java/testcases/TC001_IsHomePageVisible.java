package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_IsHomePageVisible extends BaseTest {

    @Test
    public void isHomePageDisplayed() {
        driver.get("https://magento.softwaretestingboard.com/");


        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";

        Assert.assertEquals(actualTitle, expectedTitle, "Home page title mismatch!");
        System.out.println("Home Page is visible. Title: " + actualTitle);
    }
}
