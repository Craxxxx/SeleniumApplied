package ui_qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import testNG annotations
import org.testng.annotations.*;
import ui_qa.utils.*;

//import driverManager
import io.github.bonigarcia.wdm.WebDriverManager;


public class Setup {

    protected static WebDriver driver;

    
    @BeforeSuite
    public void setupDriver()
    {
        System.out.println("Setting up driver");
        driver = DriverManager.getDriver("siteA");//get or set the driver from the driver manager class
    }



    @AfterSuite
    public void teardownTest()
    {
        System.out.println("Tearing down env");
        DriverManager.quitAll();
    }
}
