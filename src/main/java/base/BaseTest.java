package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setupReport() {
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Parameters("browser")
    @BeforeMethod
    public void setupDriver(@Optional("chrome") String browser, Method method) {
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
        logger.info("Initializing WebDriver for browser: {}", browser);

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--private");
                firefoxOptions.addArguments("--ignore-certificate-errors");
                driver = new FirefoxDriver(firefoxOptions);
                logger.info("Firefox initialized successfully.");
                break;

            case "edge":
                try {
                    Path tempDir = Files.createTempDirectory("edgeUserDataDir");
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--ignore-certificate-errors");
                    edgeOptions.addArguments("--remote-debugging-port=9222");
                    edgeOptions.addArguments("--user-data-dir=" + tempDir.toString());

                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(edgeOptions);
                    logger.info("Edge initialized successfully.");
                } catch (IOException e) {
                    logger.error("Error creating unique user data directory for Edge", e);
                    throw new RuntimeException(e);
                }
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://magento.softwaretestingboard.com/");
        logger.info("Navigated to Magento website");
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        ExtentTest extentTest = test.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test Passed Successfully.");
        } else {
            extentTest.skip("Test Skipped.");
        }

        if (driver != null) {
            driver.quit();
            logger.info("Browser Closed");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        logger.info("Extent Report Generated.");
    }
}
