package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstname")
    private WebElement firstName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "email_address")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")
    private WebElement createAccountButton;

    @FindBy(xpath = "//div[contains(@data-bind,'html: $parent.prepareMessageForHtml')]")
    private WebElement successMessage;

    public void registerUser(String fName, String lName, String emailId, String pwd) {
        sendKeys(firstName, fName);
        sendKeys(lastName, lName);
        sendKeys(email, emailId);
        sendKeys(password, pwd);
        sendKeys(confirmPassword, pwd);
        click(createAccountButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }


}
