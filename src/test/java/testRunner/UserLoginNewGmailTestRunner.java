package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileReader;

public class UserLoginNewGmailTestRunner extends Setup {

    @Test(priority = 1, description = "Fail to Login with Old Gmail")
    public void oldGmailLogin() throws InterruptedException {
        try {
            JSONParser parser = new JSONParser();

            // Read old email from storeOldGmail.json
            FileReader localReader = new FileReader("./src/test/resources/storeOldGmail.json");
            JSONObject storeOldGmail= (JSONObject) parser.parse(localReader);
            localReader.close();
            String oldEmail = storeOldGmail.get("oldEmail").toString();

            // Read last user password from user.json
            FileReader userReader = new FileReader("./src/test/resources/users.json");
            JSONArray users = (JSONArray) parser.parse(userReader);
            userReader.close();
            JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
            String password = lastUser.get("password").toString();

            // Perform login with old email and last password
            LoginPage oldGmail = new LoginPage(driver);
            oldGmail.doLogin(oldEmail, password);

            String msgActual = driver.findElement(By.tagName("p")).getText();
            String msgExpected = "Invalid email or password";
            Assert.assertTrue(msgActual.contains(msgExpected));
            System.out.println(msgActual);


        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(3000);
    }

    @Test(priority=2,description = "Successfully Login with New Gmail")
    public void newGmailLogin() {
        try {
            // Parse the user.json file
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("./src/test/resources/users.json");
            JSONArray users = (JSONArray) parser.parse(reader);
            reader.close();

            // Get last user
            JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
            String email = lastUser.get("email").toString();
            String password = lastUser.get("password").toString();

            // Perform login with last user credentials
            LoginPage newGmail = new LoginPage(driver);
            newGmail.doLoginNewGmail(email, password);
            Thread.sleep(2000);
            newGmail.doLogout();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
