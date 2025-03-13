package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SignUpPage;

public class TC003_SignUpAccount extends BaseTest {

    @Test
    public void testUserRegistration() {

        Website_Availability();

        HomePage homePage = new HomePage(driver);
        homePage.clickSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);

        // Generate unique email for each test run
        MyEmailAdd = "User" + System.currentTimeMillis() + "@test.com";
        MyPassword = "Aeiou@6401";

        signUpPage.registerUser("Test", "User", MyEmailAdd, MyPassword);

        System.out.println("Registered Email: " + MyEmailAdd);
        System.out.println("Registered Password: " + MyPassword);

        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = signUpPage.getSuccessMessage();
        System.out.println("Expected : "+expectedMessage);
        System.out.println("Actual : "+actualMessage);


        if (actualMessage.equals(expectedMessage)) {
            System.out.println("✅ Assertion Passed: Signup success message matches.");
        } else {
            System.out.println("❌ Assertion Failed: Expected - " + expectedMessage + ", but got - " + actualMessage);
        }
        Assert.assertEquals(actualMessage, expectedMessage, "Signup failed!");

    }
}
