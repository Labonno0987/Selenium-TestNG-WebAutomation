package testRunner;

import config.Setup;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.AllRegDataSet;
import pages.UserRegistrationPage;


public class CSVuserRegTestRunner extends Setup {

        @Test(dataProvider = "RegCSVData", dataProviderClass = AllRegDataSet.class)
        public void doCSVregistration(String firstName, String lastname, String email, String password,String phoneNumber, String address) {
            driver.findElement(By.linkText("Register")).click();
            UserRegistrationPage doReg = new UserRegistrationPage(driver);
            doReg.doRegistercsv(firstName,lastname,email,password,phoneNumber,address);

        }

    }
