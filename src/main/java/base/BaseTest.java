package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger;
    protected static String MyEmailAdd;
    protected static String MyPassword;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public void Website_Availability() {
        if (driver.getPageSource().contains("404 Not Found")) {
            System.out.println("❌ Website is not available - 404 Not Found");
            throw new SkipException("Skipping test as the website is not available.");
        } else {
            System.out.println("✅ Website loaded successfully");
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
