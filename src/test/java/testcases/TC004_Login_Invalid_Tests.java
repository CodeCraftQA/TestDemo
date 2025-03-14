package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.UserData;

public class TC004_Login_Test_Valid_And_Invalid extends BaseTest {

    @Test(priority = 1, dependsOnMethods = "testUserRegistration")
    public void testValidUserLogin() {

        if (MyEmailAdd == null || MyPassword == null) {
            throw new RuntimeException("Email or Password is null!");
        }
        Website_Availability(); // Ensure this method is defined
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(MyEmailAdd, MyPassword);
        loginPage.submitbtn();

        String expectedMessage = "Welcome, Test User!"; // Make sure this matches the expected message
        String actualMessage = loginPage.getWelcomeMessage();  // Implement this method in LoginPage

        Assert.assertEquals(actualMessage, expectedMessage, "Login failed!");
        System.out.println("✅ Valid Login Test Passed");
    }

    @Test(priority = 2)
    public void testInvalidCredentialsLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid@test.com", "WrongPass123");
        loginPage.submitbtn();

        String errorMessage = loginPage.getErrorMessage();  // Implement this method in LoginPage

        Assert.assertTrue(errorMessage.contains("Invalid login or password"), "Expected error message not displayed!");
        System.out.println("✅ Invalid Credentials Test Passed");
    }

    @Test(priority = 3)
    public void testBlankFieldsLogin() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        loginPage.submitbtn();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(errorMessage.contains("This is a required field"), "Expected error message not displayed!");
        System.out.println("✅ Blank Fields Test Passed");
    }

    @Test(priority = 4)
    public void testInvalidEmailFormat() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidemail", "SomePass123");
        loginPage.submitbtn();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(errorMessage.contains("Please enter a valid email address"), "Expected error message not displayed!");
        System.out.println("✅ Invalid Email Format Test Passed");
    }

    @Test(priority = 5)
    public void testSQLInjectionAttempt() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("' OR 1=1 --", "randompass");
        loginPage.submitbtn();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(errorMessage.contains("Invalid login or password"), "Possible SQL Injection vulnerability!");
        System.out.println("✅ SQL Injection Test Passed");
    }

    @Test(priority = 6)
    public void testLogoutAndRelogin() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(MyEmailAdd, MyPassword);
        loginPage.submitbtn();

        homePage.clickLogout();  // Implement logout functionality in HomePage
        homePage.clickSignIn();

        loginPage.login(MyEmailAdd, MyPassword);
        loginPage.submitbtn();

        String expectedMessage = "Welcome, Test User!"; // Ensure this matches the expected message
        String actualMessage = loginPage.getWelcomeMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Re-login failed!");
        System.out.println("✅ Logout & Re-login Test Passed");

}

}
