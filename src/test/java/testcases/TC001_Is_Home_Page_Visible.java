package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

public class TC001_IsHomePageVisible extends BaseTest {

    @Test(description = "Verify that the Home Page is displayed with the correct title.")
    public void isHomePageDisplayed() {
        test.get().log(Status.INFO, "Navigating to the Magento homepage.");
        driver.get("https://magento.softwaretestingboard.com/");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";

        test.get().log(Status.INFO, "Verifying page title: Expected - '" + expectedTitle + "', Actual - '" + actualTitle + "'.");

        Assert.assertEquals(actualTitle, expectedTitle, "Home Page title mismatch!");
        test.get().log(Status.PASS, "Home Page is displayed successfully.");

        ScreenshotUtil.takeScreenshot(driver, "isHomePageDisplayed");
    }
}