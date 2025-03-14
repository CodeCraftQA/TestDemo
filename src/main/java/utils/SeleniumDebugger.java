package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDebugger {

    public static void main(String[] args) {
        // Demo Id = Testing@user.com , Password = Aeiou@6170
        WebDriver driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Testing@user.com");
        driver.findElement(By.xpath("//fieldset[@class='fieldset login']//input[@id='pass']")).sendKeys("Aeiou@6170");
        driver.findElement((By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]"))).click();
        driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")).click();

    }
}
