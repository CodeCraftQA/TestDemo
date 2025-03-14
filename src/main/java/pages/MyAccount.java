package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {

    public MyAccount(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath ="//div[@class='panel header']//button[@type='button']")
    WebElement headerOptionsToogle;

    @FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='My Wish List']")
    WebElement wishlit;

    @FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")
    WebElement SignOutbtn;

    public void clickheaderOptions(){
        click(headerOptionsToogle);
    }
    public void Clickwishlit(){
        click(wishlit);
    }
    public void clickSignOutbtn(){
        click(SignOutbtn);
    }


}
