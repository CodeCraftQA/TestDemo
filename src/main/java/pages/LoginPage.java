package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "pass")
    private WebElement password;

    @FindBy(id = "send2")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElement errorMessage;

    @FindBy(xpath = "//h1[@class='page-title']/span[@class='base']")
    private WebElement myAccountHeader;

    @FindBy(xpath = "//div[@id='email-error' and contains(@class, 'mage-error')]")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[@id='pass-error' and contains(@class, 'mage-error')]")
    private WebElement passwordErrorMessage;

    public void login(String emailId, String pwd) {
        sendKeys(email, emailId);
        sendKeys(password, pwd);
    }

    public void submitbtn() {
        click(signInButton);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }

    public String getWelcomeMessage() {
        return myAccountHeader.getText().trim(); // Ensure extra spaces are removed
    }

}
