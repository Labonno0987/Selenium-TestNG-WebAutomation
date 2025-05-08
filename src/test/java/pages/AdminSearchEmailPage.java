package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminSearchEmailPage {
    @FindBy(className = "search-box")
    public WebElement searchEmailInpt;

    public AdminSearchEmailPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}
