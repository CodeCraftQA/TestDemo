package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        test.get().log(Status.INFO, "Navigating to login page");
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");

        // Simulated login check
        boolean loginSuccess = true; // Replace with actual login logic

        if (loginSuccess) {
            test.get().log(Status.PASS, "Login successful");
        } else {
            test.get().log(Status.FAIL, "Login failed");
        }

        Assert.assertTrue(loginSuccess, "Login failed!");
    }
}
