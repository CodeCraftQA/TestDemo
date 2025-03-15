package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setupReport() {
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Selenium Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Your Name");
        logger.info("Extent Report setup completed.");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setupDriver(@Optional("chrome") String browser, Method method) {
        ExtentTest extentTest = extent.createTest(method.getName() + " - [" + browser.toUpperCase() + "]");
        extentTest.assignCategory(browser.toUpperCase());
        test.set(extentTest);
        logger.info("Initializing WebDriver for browser: {}", browser);

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--private");
                firefoxOptions.addArguments("--ignore-certificate-errors");
                firefoxOptions.addArguments("--headless=new");
                driver = new FirefoxDriver(firefoxOptions);
                logger.info("Firefox initialized successfully.");
                break;

            case "edge":
                try {

                    Path parentDir = Path.of(System.getProperty("user.dir")).getParent();
                    Path miscDir = parentDir.resolve("misc");
                    Path edgeUserDataDir = miscDir.resolve("EdgeUserData");

                    // Ensure the misc directory exists
                    Files.createDirectories(miscDir);

                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized", "--disable-popup-blocking");
                    edgeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                    edgeOptions.addArguments("--disable-gpu", "--ignore-certificate-errors");
                    edgeOptions.addArguments("--remote-debugging-port=9222");
                    edgeOptions.addArguments("--user-data-dir=" + edgeUserDataDir);
                    edgeOptions.addArguments("--headless=new");

                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(edgeOptions);
                    logger.info("Edge initialized successfully with user data directory at: {}", edgeUserDataDir.toString());
                } catch (IOException e) {
                    logger.error("Error creating 'EdgeUserData' folder inside 'misc' directory", e);
                    throw new RuntimeException(e);
                }
                break;


            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--ignore-certificate-errors");
                //chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                logger.info("Chrome initialized successfully.");
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://magento.softwaretestingboard.com/");
        logger.info("Navigated to Magento website.");
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getName());
            extentTest.fail("Test Failed: " + result.getThrowable());
            extentTest.addScreenCaptureFromPath(screenshotPath);
            logger.error("Test Failed: {}", result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test Passed Successfully.");
            logger.info("Test Passed Successfully.");
        } else {
            extentTest.skip("Test Skipped.");
            logger.warn("Test Skipped.");
        }

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        logger.info("Extent Report generated.");
    }

    public String takeScreenshot(String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + "/reports/screenshots/" + testName + "_" + new Date().getTime() + ".png";
            Files.createDirectories(Path.of(System.getProperty("user.dir") + "/reports/screenshots/"));
            Files.copy(srcFile.toPath(), Path.of(screenshotPath), StandardCopyOption.REPLACE_EXISTING);
            return screenshotPath;
        } catch (Exception e) {
            logger.error("Error capturing screenshot: ", e);
            return null;
        }
    }

}
