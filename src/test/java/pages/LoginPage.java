package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(id="email")
    public WebElement txtEmail;
    @FindBy(id="password")
    public WebElement txtPassword;
    @FindBy(tagName = "button")
    WebElement btnLogin;

    @FindBy(css="[type=button]")
    List<WebElement> btnProfileIcon;
    @FindBy(css = "[role=menuitem]")
    List<WebElement> btnLogout;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this); //webelement gula inilitize kore(PageFactory=testNG class and initElements=method)
    }
    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
    public void doLogout(){
        btnProfileIcon.get(0).click();
        btnLogout.get(1).click();
    }
    public void doLoginNewGmail(String email, String password){
        txtEmail.sendKeys(Keys.CONTROL+"a", Keys.BACK_SPACE);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(Keys.CONTROL+"a", Keys.BACK_SPACE);
        txtPassword.sendKeys(password);
        btnLogin.click();

    }

}
