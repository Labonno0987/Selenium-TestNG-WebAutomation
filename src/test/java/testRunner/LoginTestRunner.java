package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {

    @Test(priority = 1,description = "Admin Login")
    public void adminLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "admin123");
        Thread.sleep(5000);
    }
    @Test(priority = 2,description = "Admin Logout")
    public void adminLogout() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
    }
    @Test (priority = 3, description = "User Login")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=userObj.get("email").toString();
        String password=userObj.get("password").toString();

        Thread.sleep(5000);
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin(email,password);
    }
}
