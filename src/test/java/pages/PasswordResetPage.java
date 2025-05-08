package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PasswordResetPage {
    @FindBy(tagName = "input")
    public WebElement txtEmail;

    @FindBy(css = "[type=submit]")
    WebElement submitBtn;

    @FindBy(tagName = "input")
    List<WebElement> txtInput;

    @FindBy(tagName = "button")
    WebElement btnReset;

    public PasswordResetPage(WebDriver driver){
        PageFactory.initElements(driver,this); //webElement gula inilitize kore(PageFactory=testNG class and initElements=method)
    }
    public void doResetPass(String email){
        txtEmail.sendKeys(email);
        submitBtn.click();
    }
    public void doChangeAndConfirmPass(String newPass,String confirmPass) throws InterruptedException {
        txtInput.get(0).sendKeys(newPass);
        txtInput.get(1).sendKeys(confirmPass);
        Thread.sleep(1500);
        btnReset.click();
    }
}
