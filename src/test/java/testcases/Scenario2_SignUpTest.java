package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyAccount;
import pages.SignUpPage;
import utils.ScreenshotUtil;
import utils.UserData;

public class Scenario2_SignUpTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Scenario2_SignUpTest.class);

    @Test(priority = 2, description = "TC002 Verify user registration functionality.")
    public void test_User_Registration() {
        HomePage homePage = new HomePage(driver);
        test.get().log(Status.INFO, "Navigating to the signup page.");
        logger.info("Navigating to the signup page.");
        homePage.clickSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        String myEmailAdd = "User" + System.currentTimeMillis() + "@test.com";
        String myPassword = "Aeiou@6401";

        UserData.email = myEmailAdd;
        UserData.password = myPassword;

        test.get().log(Status.INFO, "Registering user with email: " + myEmailAdd);
        logger.info("Registering user with email: " + myEmailAdd);
        signUpPage.registerUser("Test", "User", myEmailAdd, myPassword);

        logger.info("User ID Generated: " + myEmailAdd);
        logger.info("Password Generated: " + myPassword);

        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = signUpPage.getSuccessMessage();

        test.get().log(Status.INFO, "Verifying success message: Expected - '" + expectedMessage + "', Actual - '" + actualMessage + "'.");
        logger.info("Verifying success message: Expected - '" + expectedMessage + "', Actual - '" + actualMessage + "'.");

        Assert.assertEquals(actualMessage, expectedMessage, "Signup failed!");
        test.get().log(Status.PASS, "User registration successful.");
        logger.info("User registration successful.");

        MyAccount ma = new MyAccount(driver);
        ma.clickheaderOptions();
        ma.clickSignOutbtn();
        logger.info("User signed out successfully.");

        ScreenshotUtil.takeScreenshot(driver, "test_User_Registration");
        logger.info("Screenshot captured: 'test_User_Registration'.");
    }
}
