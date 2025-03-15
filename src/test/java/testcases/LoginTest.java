package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccount;
import utils.ScreenshotUtil;
import utils.UserData;

public class LoginTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1, description = "TC003 Verify user login functionality.")
    public void login_Test() {
        HomePage homePage = new HomePage(driver);
        test.get().log(Status.INFO, "Navigating to the login page.");
        logger.info("Navigating to the login page.");
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);

        String email = UserData.email;
        String password = UserData.password;

        test.get().log(Status.INFO, "Logging in with email: " + email);
        logger.info("Logging in with email: " + email);
        loginPage.login(email, password);
        loginPage.submitbtn();

        String expectedMessage = "My Account";
        String actualMessage = loginPage.getWelcomeMessage();

        test.get().log(Status.INFO, "Verifying welcome message: Expected - '" + expectedMessage + "', Actual - '" + actualMessage + "'.");
        logger.info("Verifying welcome message: Expected - '" + expectedMessage + "', Actual - '" + actualMessage + "'.");

        Assert.assertEquals(actualMessage, expectedMessage, "Login failed!");
        test.get().log(Status.PASS, "Login successful.");
        logger.info("Login successful.");

        MyAccount ma = new MyAccount(driver);
        ma.clickheaderOptions();
        ma.clickSignOutbtn();
        logger.info("User signed out successfully.");

        ScreenshotUtil.takeScreenshot(driver, "login_Test");
        logger.info("Screenshot captured: 'login_Test'.");
    }
}
