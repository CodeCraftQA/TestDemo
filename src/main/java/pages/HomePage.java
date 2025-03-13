package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Create an Account')]")
    private WebElement signUpLink;

    @FindBy(xpath = "//a[contains(text(),'Sign In')]")
    private WebElement signInLink;

    @FindBy(xpath = "//a[contains(text(),'Sign Out')]")
    private WebElement logoutButton;

    public void clickSignUp() {
        click(signUpLink);
    }

    public void clickSignIn() {
        click(signInLink);
    }

    public void clickLogout() {
        click(logoutButton);
    }
}
