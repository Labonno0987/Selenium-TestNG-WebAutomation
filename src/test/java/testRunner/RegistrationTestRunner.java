package testRunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserRegistrationPage;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.IOException;

public class RegistrationTestRunner extends Setup {

    @Test(description = "New User can register and Receives Congratulations email.")
    public void userRegistration() throws InterruptedException, IOException, ParseException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        driver.findElement(By.partialLinkText("Register")).click();
        UserRegistrationPage user = new UserRegistrationPage(driver);

        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email="ayatahmed012345+"+Utils.generateRandomNumber(1000000,9999999)+"@gmail.com";
        String password="123";
        String phnNo="017"+ Utils.generateRandomNumber(10000000,99999999);
        String address=faker.address().fullAddress();


        UserModel userModel=new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phnNo);
        userModel.setAddress(address);
        user.userRegistration(userModel);

        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstName);
        userObj.put("lastName",lastName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phnNo);
        userObj.put("address",address);

        Utils.saveUserData("./src/test/resources/users.json",userObj);
        Thread.sleep(5000);
        String mailBody = Utils.readMail();
        Assert.assertTrue(mailBody.contains("Welcome to our platform"));
    }
}
