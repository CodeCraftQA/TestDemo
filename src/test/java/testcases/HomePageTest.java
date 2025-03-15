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


public class HomePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);

    @Test(description = "TC001 Verify that the Home Page is displayed with the correct title.")
    public void is_Home_Page_Displayed() {
        test.get().log(Status.INFO, "Navigating to the Magento homepage.");
        driver.get("https://magento.softwaretestingboard.com/");
        logger.info("Home Page is loaded Successfully");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";

        test.get().log(Status.INFO, "Verifying page title: Expected - '" + expectedTitle + "', Actual - '" + actualTitle + "'.");

        Assert.assertEquals(actualTitle, expectedTitle, "Home Page title mismatch!");
        test.get().log(Status.PASS, "Home Page is displayed successfully.");

        logger.info("Successfully Verified HomePage");
        ScreenshotUtil.takeScreenshot(driver, "is_Home_Page_Displayed");
        logger.info("Screenshot captured: 'is_Home_Page_Displayed'.");
    }
}