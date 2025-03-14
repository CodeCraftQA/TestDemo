package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        driver = (WebDriver) context.getAttribute("WebDriver");
    }
}
