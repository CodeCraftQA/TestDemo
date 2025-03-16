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

    @FindBy(xpath = "//div[@id='pass-error']")
    private WebElement emailPwdBlankMessage;

    @FindBy(xpath = "//div[@id='email-error']")
    private WebElement incompleteEmail;

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

    public String getEmailPwdBlankMessage() {
        return emailPwdBlankMessage.getText();
    }
    public String getWelcomeMessage() {
        return myAccountHeader.getText().trim();
    }
    public String incompleteEmailAdd(){
        return incompleteEmail.getText();
    }
}
