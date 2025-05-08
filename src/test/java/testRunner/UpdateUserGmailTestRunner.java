package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UpdateUserGmail;
import utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;

public class UpdateUserGmailTestRunner extends Setup {
    @BeforeTest
    public void doLogin2() throws Exception {

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
    @Test(priority = 1, description = "Update User Gmail with New gmail")
    public void updateGmail() throws Exception {
        // Load user data
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("./src/test/resources/users.json");
        JSONArray users = (JSONArray) parser.parse(reader);
        reader.close();

        // Get the last user and store old Gmail
        JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
        String oldEmail = lastUser.get("email").toString();

        // Save old Gmail to storeOldGmail.json
        JSONObject storeOldGmail = new JSONObject();
        storeOldGmail.put("oldEmail", oldEmail);
        System.out.println("Old Email : "+oldEmail);

        FileWriter localWriter = new FileWriter("./src/test/resources/storeOldGmail.json");
        localWriter.write(storeOldGmail.toJSONString());
        localWriter.flush();
        localWriter.close();

        // Update on UI
        UpdateUserGmail updateGmailUser = new UpdateUserGmail(driver);
        String newGmail = "anon123" + Utils.generateRandomNumber(100, 999) + "@gmail.com";
        updateGmailUser.updateUser("", "", newGmail, "", "", "");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        // Update user.json with new Gmail
        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            if (user.get("email").equals(oldEmail)) {
                user.put("email", newGmail);
                System.out.println("New Email : "+newGmail);
                break;
            }
        }

        FileWriter writer = new FileWriter("./src/test/resources/users.json");
        writer.write(users.toJSONString());
        writer.flush();
        writer.close();
        Thread.sleep(2000);

        LoginPage logOut= new LoginPage(driver);
        logOut.doLogout();
    }


}
