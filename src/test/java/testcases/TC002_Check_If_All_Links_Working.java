package testcases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TC002_Check_If_All_Links_Working extends BaseTest {

    @Test
    public void checkBrokenLinks() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Get all links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + links.size());

        List<String> brokenLinks = new ArrayList<>();

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url != null && !url.isEmpty()) {
                if (!verifyLink(url)) {
                    brokenLinks.add(url);
                }
            } else {
                System.out.println("⚠ Empty or missing href attribute found.");
            }
        }

        // Assert if there are any broken links
        Assert.assertTrue(brokenLinks.isEmpty(), "Broken links found: " + brokenLinks);
    }

    public static boolean verifyLink(String url) {
        try {
            // Create HTTP connection
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode >= 400) {
                System.out.println("❌ Broken Link: " + url + " | Status Code: " + responseCode);
                return false;
            } else {
                System.out.println("✅ Valid Link: " + url + " | Status Code: " + responseCode);
                return true;
            }
        } catch (IOException e) {
            System.out.println("⚠ Error checking URL: " + url);
            return false;
        }

    }

}
