package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddItem;
import pages.LoginPage;

import java.io.FileReader;



public class AddItemTestRunner extends Setup {
    @BeforeTest
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
    @Test(priority = 1, description = "Add First item with all the fields")
    public void addFirstItem() throws InterruptedException {
        AddItem addNewItem=new AddItem(driver);
        String itemName="Apple";
        String amount="5";
        String purchaseDate="07/05/2025";
        String month="May";
        String remarks="Fresh";
        addNewItem.addingItem(itemName,amount,purchaseDate,month,remarks);

        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        String headerActual= driver.findElement(By.tagName("h2")).getText();
        String headerExpected="User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected),"Item Added");
    }
    @Test(priority = 2, description = "Add Second item with only mandatory fields")
    public void addSecondItem() throws InterruptedException {
        AddItem addNewItem=new AddItem(driver);
        String itemName="Banana";
        String amount="48";
        String purchaseDate="07/05/2025";
        String month="";
        String remarks="";
        addNewItem.addingItem(itemName,amount,purchaseDate,month,remarks);

        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        String headerActual= driver.findElement(By.tagName("h2")).getText();
        String headerExpected="User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected),"Item Added");
    }

    @Test(priority = 3, description = "Assert 2 items are showing on the item list")
    public void showItem(){
        AddItem addNewItem=new AddItem(driver);
        addNewItem.showItem();
    }


}
