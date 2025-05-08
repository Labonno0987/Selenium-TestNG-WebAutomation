package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Setup {
    public Properties prop;
    public WebDriver driver;
    @BeforeTest
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://dailyfinance.roadtocareer.net/");
    }
   @AfterTest
    public void tearDown(){
        driver.quit();
    }
    @AfterMethod
    public void reload() throws IOException {
        prop=new Properties();
        FileInputStream fs= new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
        FileInputStream fs1= new FileInputStream("./src/test/resources/storeOldGmail.json");
        prop.load(fs1);
    }
}
