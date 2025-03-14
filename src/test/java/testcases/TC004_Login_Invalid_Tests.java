package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccount;
import utils.ScreenshotUtil;
import utils.UserData;

public class TC004_Login_Invalid_Tests extends BaseTest {

    @Test(priority = 1, dependsOnMethods = "testcases.TC002_SignUp_Account.test_User_Registration")
    public void test_Invalid_Credentials_Login() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid@test.com", "WrongPass123");
        loginPage.submitbtn();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(errorMessage.contains("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."), "Expected error message not displayed!");
        System.out.println("✅ Not Able To Login Using Invalid Credentials : Test Passed");

        ScreenshotUtil.takeScreenshot(driver, "test_Invalid_Credentials_Login");
    }

    @Test(priority = 2)
    public void test_Blank_Fields_Login() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        loginPage.submitbtn();

        String errorMessage = loginPage.getEmailPwdBlankMessage();
        Assert.assertTrue(errorMessage.contains("This is a required field."), "Expected error message not displayed!");
        System.out.println("✅ Not Able To Login Without Entering EmailId, Password and Clicking Submit Button : Test Passed");
        ScreenshotUtil.takeScreenshot(driver, "testValidLogin");
    }
    @Test(priority = 3)
    public void testInvalidEmailidField(){
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("te", "Test@1234");
        loginPage.submitbtn();

        String errorMessage = loginPage.incompleteEmailAdd();
        System.out.println(errorMessage);
        Assert.assertTrue(errorMessage.contains("Please enter a valid email address (Ex: johndoe@domain.com)."), "Expected error message not displayed!");
        System.out.println("✅ Not Able To Login Without Entering Full EmailId Clicking Submit Button : Test Passed");
        ScreenshotUtil.takeScreenshot(driver, "test_Blank_Fields_Login");
    }

    @Test(priority = 4)
    public void test_Invalid_Email_Format() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidemail", "SomePass123");
        loginPage.submitbtn();

        String errorMessage = loginPage.incompleteEmailAdd();

        Assert.assertTrue(errorMessage.contains("Please enter a valid email address (Ex: johndoe@domain.com)."), "Expected error message not displayed!");
        System.out.println("✅ Not Able To Login Using Invalid Email Format : Test Passed");
        ScreenshotUtil.takeScreenshot(driver, "test_Invalid_Email_Format");
    }

    @Test(priority = 5)
    public void test_SQLInjection_Attempt() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("' OR 1=1 --", "randompass");
        loginPage.submitbtn();

        String errorMessage = loginPage.incompleteEmailAdd();

        Assert.assertTrue(errorMessage.contains("Please enter a valid email address (Ex: johndoe@domain.com)."), "Possible SQL Injection vulnerability!");
        System.out.println("✅ Not Able To Login Using SQL Injection : Test Passed");
        ScreenshotUtil.takeScreenshot(driver, "test_SQLInjection_Attempt");
    }

    @Test(priority = 6)
    public void test_Logout_And_Relogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.email, UserData.password);
        loginPage.submitbtn();

        MyAccount ma = new MyAccount(driver);
        ma.clickheaderOptions();
        ma.clickSignOutbtn();

        homePage.clickSignIn();

        loginPage.login(UserData.email, UserData.password);
        loginPage.submitbtn();

        String expectedMessage = "My Account";
        String actualMessage = loginPage.getWelcomeMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Expected error message not displayed!");
        System.out.println("✅Able To Logout & Re-login : Test Passed");
        ScreenshotUtil.takeScreenshot(driver, "test_Logout_And_Relogin");
    }
}
