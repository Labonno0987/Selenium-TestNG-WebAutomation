package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileReader;

public class UserLoginNewPasswordTestRunner extends Setup {

    @Test(priority = 1, description = "Login with New Password")
    public void doLoginWithNewPassword() throws Exception {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("./src/test/resources/users.json");
        JSONArray users = (JSONArray) parser.parse(reader);
        reader.close();
        JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
        String email = lastUser.get("email").toString();
        String password = lastUser.get("password").toString();
        System.out.println(email);
        System.out.println(password);
        Thread.sleep(3000);

        LoginPage login = new LoginPage(driver);
        login.doLogin(email, password);
        Thread.sleep(3000);

    }
}
