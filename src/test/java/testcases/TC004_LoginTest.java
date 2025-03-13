package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class TC004_LoginTest extends BaseTest {

    @Test(priority = 1, dependsOnMethods = "testcases.TC003_SignUpAccount.testUserRegistration")
    public void testValidUserLogin() {
        if (MyEmailAdd == null || MyPassword == null) {
            throw new RuntimeException("Email or Password is null!");
        }
        Website_Availability();
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(MyEmailAdd, MyPassword);
        loginPage.submitbtn();

        String expectedMessage = "Welcome, Test User!";
        String actualMessage = loginPage.getWelcomeMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Login failed!");
        System.out.println("✅ Able to Login Using Valid Login Credentials : Test Passed");
    }

    @Test(priority = 2)
    public void testInvalidCredentialsLogin() {
        Website_Availability();
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid@test.com", "WrongPass123");
        loginPage.submitbtn();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(errorMessage.contains("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."), "Expected error message not displayed!");
        System.out.println("✅ Unable To Login Using Invalid Credentials Test Passed");
    }

    @Test(priority = 3)
    public void testBlankFieldsLogin() {
        Website_Availability();
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        loginPage.submitbtn();

        String emailError = loginPage.getEmailErrorMessage();
        String passwordError = loginPage.getPasswordErrorMessage();

        Assert.assertTrue(emailError.contains("This is a required field"), "Email validation error not displayed!");
        Assert.assertTrue(passwordError.contains("This is a required field"), "Password validation error not displayed!");
        System.out.println("✅ Blank Fields Test Passed");
    }

    @Test(priority = 4)
    public void testInvalidEmailFormat() {
        Website_Availability();
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidemail", "SomePass123");
        loginPage.submitbtn();

        String emailError = loginPage.getEmailErrorMessage();

        Assert.assertTrue(emailError.contains("Please enter a valid email address"), "Expected error message not displayed!");
        System.out.println("✅ Invalid Email Format Test Passed");
    }
}