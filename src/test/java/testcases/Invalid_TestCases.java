package testcases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccount;
import utils.ScreenshotUtil;
import utils.UserData;

public class Invalid_TestCases extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Invalid_TestCases.class);

    @Test(priority = 1, dependsOnMethods = "testcases.SignUpTest.test_User_Registration")
    public void test_Invalid_Credentials_Login() {
        logger.info("Starting test: test_Invalid_Credentials_Login");

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        logger.info("Navigated to Login Page.");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid@test.com", "WrongPass123");
        loginPage.submitbtn();
        logger.info("Entered invalid credentials and attempted login.");

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("The account sign-in was incorrect or your account is disabled temporarily."),
                "Expected error message not displayed!");
        logger.info("Invalid login attempt failed as expected.");

        ScreenshotUtil.takeScreenshot(driver, "test_Invalid_Credentials_Login");
        logger.info("Screenshot captured: test_Invalid_Credentials_Login.");
    }

    @Test(priority = 2)
    public void test_Blank_Fields_Login() {
        logger.info("Starting test: test_Blank_Fields_Login");

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        logger.info("Navigated to Login Page.");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        loginPage.submitbtn();
        logger.info("Attempted login with blank credentials.");

        String errorMessage = loginPage.getEmailPwdBlankMessage();
        Assert.assertTrue(errorMessage.contains("This is a required field."), "Expected error message not displayed!");
        logger.info("Blank login attempt failed as expected.");

        ScreenshotUtil.takeScreenshot(driver, "test_Blank_Fields_Login");
        logger.info("Screenshot captured: test_Blank_Fields_Login.");
    }

    @Test(priority = 3)
    public void testInvalidEmailidField() {
        logger.info("Starting test: testInvalidEmailidField");

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        logger.info("Navigated to Login Page.");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("te", "Test@1234");
        loginPage.submitbtn();
        logger.info("Attempted login with an incomplete email.");

        String errorMessage = loginPage.incompleteEmailAdd();
        Assert.assertTrue(errorMessage.contains("Please enter a valid email address (Ex: johndoe@domain.com)."),
                "Expected error message not displayed!");
        logger.info("Invalid email format login attempt failed as expected.");

        ScreenshotUtil.takeScreenshot(driver, "testInvalidEmailidField");
        logger.info("Screenshot captured: testInvalidEmailidField.");
    }

    @Test(priority = 4)
    public void test_Invalid_Email_Format() {
        logger.info("Starting test: test_Invalid_Email_Format");

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        logger.info("Navigated to Login Page.");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidemail", "SomePass123");
        loginPage.submitbtn();
        logger.info("Attempted login with an incorrect email format.");

        String errorMessage = loginPage.incompleteEmailAdd();
        Assert.assertTrue(errorMessage.contains("Please enter a valid email address (Ex: johndoe@domain.com)."),
                "Expected error message not displayed!");
        logger.info("Invalid email format login attempt failed as expected.");

        ScreenshotUtil.takeScreenshot(driver, "test_Invalid_Email_Format");
        logger.info("Screenshot captured: test_Invalid_Email_Format.");
    }

    @Test(priority = 5)
    public void test_SQLInjection_Attempt() {
        logger.info("Starting test: test_SQLInjection_Attempt");

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        logger.info("Navigated to Login Page.");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("' OR 1=1 --", "randompass");
        loginPage.submitbtn();
        logger.info("Attempted SQL Injection in login.");

        String errorMessage = loginPage.incompleteEmailAdd();
        Assert.assertTrue(errorMessage.contains("Please enter a valid email address (Ex: johndoe@domain.com)."),
                "Possible SQL Injection vulnerability!");
        logger.info("SQL Injection attempt blocked as expected.");

        ScreenshotUtil.takeScreenshot(driver, "test_SQLInjection_Attempt");
        logger.info("Screenshot captured: test_SQLInjection_Attempt.");
    }

    @Test(priority = 6)
    public void test_Logout_And_Relogin() {
        logger.info("Starting test: test_Logout_And_Relogin");

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        logger.info("Navigated to Login Page.");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.email, UserData.password);
        loginPage.submitbtn();
        logger.info("Logged in with valid credentials.");

        MyAccount ma = new MyAccount(driver);
        ma.clickheaderOptions();
        ma.clickSignOutbtn();
        logger.info("User logged out successfully.");

        homePage.clickSignIn();
        logger.info("Navigating back to Login Page for re-login.");

        loginPage.login(UserData.email, UserData.password);
        loginPage.submitbtn();
        logger.info("Attempted re-login.");

        String expectedMessage = "My Account";
        String actualMessage = loginPage.getWelcomeMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Expected error message not displayed!");
        logger.info("User successfully logged in again.");

        ScreenshotUtil.takeScreenshot(driver, "test_Logout_And_Relogin");
        logger.info("Screenshot captured: test_Logout_And_Relogin.");
    }
}
