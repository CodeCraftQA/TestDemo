package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

public class TC001_Is_Home_Page_Visible extends BaseTest {


    @Test(description = "Verify that the Home Page is displayed with the correct title.")
    public void is_Home_Page_Displayed() {
        test.get().log(Status.INFO, "Navigating to the Magento homepage.");
        driver.get("https://magento.softwaretestingboard.com/");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";

        test.get().log(Status.INFO, "Verifying page title: Expected - '" + expectedTitle + "', Actual - '" + actualTitle + "'.");

        Assert.assertEquals(actualTitle, expectedTitle, "Home Page title mismatch!");
        test.get().log(Status.PASS, "Home Page is displayed successfully.");

        ScreenshotUtil.takeScreenshot(driver, "is_Home_Page_Displayed");
    }
}