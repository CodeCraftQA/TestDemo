package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyAccount;
import pages.SignUpPage;
import utils.ScreenshotUtil;
import utils.UserData;

public class TC002_SignUpAccount extends BaseTest {

    @Test(description = "Verify user registration functionality.")
    public void testUserRegistration() {
        HomePage homePage = new HomePage(driver);
        test.get().log(Status.INFO, "Navigating to the signup page.");
        homePage.clickSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);

        String myEmailAdd = "User" + System.currentTimeMillis() + "@test.com";
        String myPassword = "Aeiou@6401";

        UserData.email = myEmailAdd;
        UserData.password = myPassword;

        test.get().log(Status.INFO, "Registering user with email: " + myEmailAdd);
        signUpPage.registerUser("Test", "User", myEmailAdd, myPassword);

        System.out.println("UserId Generate: "+myEmailAdd);
        System.out.println("Password Generated: "+myPassword);

        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = signUpPage.getSuccessMessage();

        test.get().log(Status.INFO, "Verifying success message: Expected - '" + expectedMessage + "', Actual - '" + actualMessage + "'.");
        Assert.assertEquals(actualMessage, expectedMessage, "Signup failed!");
        test.get().log(Status.PASS, "User registration successful.");

        MyAccount ma = new MyAccount(driver);
        ma.clickheaderOptions();
        ma.clickSignOutbtn();

        ScreenshotUtil.takeScreenshot(driver, "testUserRegistration");

    }
}