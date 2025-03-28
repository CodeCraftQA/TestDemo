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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Scenario1_HomePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Scenario1_HomePageTest.class);

    @Test(priority = 1, description = "TC001 Verify that the Home Page is displayed with the correct title.")
    public void is_Home_Page_Displayed() {
        test.get().log(Status.INFO, "Navigating to the Magento homepage.");
        driver.get("https://magento.softwaretestingboard.com/");

        logger.info("Home Page is loaded Successfully");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";

        test.get().log(Status.INFO, "Verifying page title: Expected - '" + expectedTitle + "', Actual - '" + actualTitle + "'.");

        Assert.assertEquals(actualTitle, expectedTitle, "Home Page title mismatch!");

        // Attach a screenshot for validation
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "is_Home_Page_Displayed");
        test.get().addScreenCaptureFromPath(screenshotPath);

        test.get().log(Status.PASS, "Home Page is displayed successfully.");
        logger.info("Successfully Verified HomePage");
    }

}