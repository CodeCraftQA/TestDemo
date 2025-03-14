package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_IsHomePageVisible extends BaseTest {

    @Test
    public void isHomePageDisplayed() {
        test.get().log(Status.INFO, "Opening Magento homepage");
        driver.get("https://magento.softwaretestingboard.com/");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";

        if (actualTitle.equals(expectedTitle)) {
            test.get().log(Status.PASS, "Home Page is displayed successfully.");
        } else {
            test.get().log(Status.FAIL, "Home Page title mismatch! Expected: " + expectedTitle + ", Actual: " + actualTitle);
        }

        Assert.assertEquals(actualTitle, expectedTitle, "Home Page title mismatch!");
    }
}
