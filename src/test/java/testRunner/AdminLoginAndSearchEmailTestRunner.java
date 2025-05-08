package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminSearchEmailPage;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AdminLoginAndSearchEmailTestRunner extends Setup {

    @Test(priority =1,description = "Admin Login by CMD")

    public void adminLogin() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        if (System.getProperty("email") != null && System.getProperty("password") != null) {
            loginPage.doLogin(System.getProperty("email"), System.getProperty("password"));
        } else {
            loginPage.doLogin("admin@test.com", "admin123");
            Thread.sleep(2000);
        }
    }
        @Test(priority = 2, description = "Search By Updated Gmail on Admin Dashboard")
        public void searchUpdatedGmail() throws IOException, ParseException, InterruptedException {

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
            JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
            String email =(String) userObj.get("email");

            AdminSearchEmailPage adminPage = new AdminSearchEmailPage(driver);
            adminPage.searchEmailInpt.sendKeys(email);
            List<WebElement> allData = driver.findElements(By.xpath("//tbody/tr[1]/td"));
            String actualEmail = allData.get(2).getText();
            Assert.assertTrue(actualEmail.contains(email));

            Thread.sleep(2000);
            LoginPage logOut = new LoginPage(driver);
            logOut.doLogout();


        }
    }
