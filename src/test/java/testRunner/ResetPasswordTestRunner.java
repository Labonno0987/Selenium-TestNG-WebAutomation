package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PasswordResetPage;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class ResetPasswordTestRunner extends Setup {

    public ResetPasswordTestRunner() throws IOException {
    }

    public void clearFields() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(1000);

    }

    @Test(priority = 1, description = "For Negative Case:Reset Password with Empty Field")
    public void resetPasswordEmptyField() throws InterruptedException {

        driver.findElement(By.partialLinkText("Reset it here")).click();
        PasswordResetPage resetPage = new PasswordResetPage(driver);
        resetPage.doResetPass("");
        Thread.sleep(1000);

        String MsgActual = resetPage.txtEmail.getAttribute("validationMessage");
        String MsgExpected = "Please fill out this field";
        System.out.println(MsgActual);
        Assert.assertTrue(MsgActual.contains(MsgExpected));
    }

    @Test(priority = 2, description = "For Negative Case: Reset Password with Unregister Email")
    public void resetPasswordUnregisteredMail() throws InterruptedException {

        clearFields();
        // driver.findElement(By.partialLinkText("Reset it here")).click();
        PasswordResetPage resetPage = new PasswordResetPage(driver);
        resetPage.doResetPass("asdfg1233@gmail.com");

        Thread.sleep(1000);
        String MsgActual = driver.findElement(By.tagName("p")).getText();
        System.out.println(MsgActual);
        String MsgExpected = "Your email is not registered";
        Assert.assertTrue(MsgActual.contains(MsgExpected));

    }


   @Test(priority = 3, description = "Click Reset Link with Valid Email")
   public void registeredEmail() throws Exception {

       clearFields();
       // Parse JSON to get last registered email
       JSONParser parser = new JSONParser();
       FileReader reader = new FileReader("./src/test/resources/users.json");
       Object obj = parser.parse(reader);
       JSONArray users = (JSONArray) obj;
       JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
       String email = lastUser.get("email").toString();

       PasswordResetPage resetPage = new PasswordResetPage(driver);
       resetPage.doResetPass(email);
       Thread.sleep(1000);
       String msgActual = driver.findElement(By.tagName("p")).getText();
       System.out.println(msgActual);
       String msgExpected = "Password reset link sent to your email";
       Assert.assertTrue(msgActual.contains(msgExpected));
   }

    public String extractResetLinkFromEmail() throws InterruptedException, ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
        Thread.sleep(5000); // wait for email delivery
        String mailBody = Utils.readMail();

        for (String extractedEmail : mailBody.split(" ")) {
            if (extractedEmail.startsWith("https://")) {
                Utils.setEnvVar("reset_link", extractedEmail);  // store the reset link
                return extractedEmail;
            }
        }
        throw new RuntimeException("Reset link not found in email body.");
    }

    @Test(priority = 4, description = "Set Confirm Password by Retriving Password Reset Email")
    public void doReSetPass() throws Exception {

        String resetUrl = extractResetLinkFromEmail();
        System.out.println("Reset URL: " + resetUrl);

        driver.get(resetUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String password = "test123";
        PasswordResetPage resetPage = new PasswordResetPage(driver);
        resetPage.doChangeAndConfirmPass(password,password);

        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
        String msgActual = messageElement.getText();
        String msgExpected = "Password reset successfully";

        Assert.assertTrue(msgActual.contains(msgExpected));
        System.out.println("Actual reset message: " + msgActual);

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("./src/test/resources/users.json");
        JSONArray users = (JSONArray) parser.parse(reader);
        reader.close();

        JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
        String email = lastUser.get("email").toString();

        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            if (user.get("email").equals(email)) {
                user.put("password", password);
                break;
            }
        }

        FileWriter writer = new FileWriter("./src/test/resources/users.json");
        writer.write(users.toJSONString());
        writer.flush();
        writer.close();
    }

}
