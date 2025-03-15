package testcases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomePageLinksTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomePageLinksTest.class);

    @Test
    public void checkBrokenLinks() {
        String baseUrl = "https://magento.softwaretestingboard.com/";
        driver.get(baseUrl);
        test.get().log(Status.INFO, "Navigated to URL: " + baseUrl);
        logger.info("Navigated to URL: {}", baseUrl);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        test.get().log(Status.INFO, "Total links found: " + links.size());
        logger.info("Total links found: {}", links.size());

        List<String> brokenLinks = new ArrayList<>();

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url != null && !url.isEmpty()) {
                if (!verifyLink(url)) {
                    brokenLinks.add(url);
                }
            } else {
                test.get().log(Status.WARNING, "⚠ Empty or missing href attribute found.");
                logger.warn("⚠ Empty or missing href attribute found.");
            }
        }

        if (!brokenLinks.isEmpty()) {
            test.get().log(Status.FAIL, "❌ Broken links found: " + brokenLinks);
            logger.error("Broken links found: {}", brokenLinks);
        } else {
            test.get().log(Status.PASS, "✅ No broken links found.");
            logger.info("✅ No broken links found.");
        }

        // Assertion with proper error logging
        Assert.assertTrue(brokenLinks.isEmpty(), "Broken links found: " + brokenLinks);
    }

    public static boolean verifyLink(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode >= 400) {
                logger.error("❌ Broken Link: {} | Status Code: {}", url, responseCode);
                return false;
            } else {
                logger.info("✅ Valid Link: {} | Status Code: {}", url, responseCode);
                return true;
            }
        } catch (IOException e) {
            logger.error("⚠ Error checking URL: {}", url, e);
            return false;
        }
    }
}
