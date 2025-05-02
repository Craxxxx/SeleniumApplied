package ui_qa.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//import driverManager
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    // Holds all your named driver sessions
    private static final Map<String, WebDriver> drivers = new HashMap<>();  //hasmaps is basically same as associative array in php
    //using final keyword to make sure that the map is not reassigned to another object
    //and we can only add or remove elements from the map


    //function to get di driver
    public static WebDriver getDriver(String name)
    {
        //code below will check if the map already contain the driver with the name key passe in the function
        if(!drivers.containsKey(name)) //if it doesnt contain the driver with the name key
        {

            //i have a case where a popup from Chrome’s built‑in password manager warning you that “secret_sauce” 
            //was breached—and it’s blocking your test’s DOM interactions and causing the failure.

            //below is the solution to prevent that from happening
            //1)disable chrome password manager and save password prompts
            Map<String,Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            


            //2)create whatever this is
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs" , prefs);

            //3)run with guest mode
            options.addArguments("--guest");
        
            //create a new driver and put it in the map with the name key
            WebDriverManager.chromedriver().setup(); //this methods automatically downloads and configures the correct driver binary at runtime
            WebDriver driver = new ChromeDriver(options);
            drivers.put(name, driver);
        } 

        return drivers.get(name);//return the value which the drivers key name is pointing to it is the driver object
    }


    /** Quits **specific** managed drivers and clears the registry. */
    public static void quitAndRemove(String name)
    {
        WebDriver driver = drivers.remove(name);
        if (driver != null) {
            driver.quit();                              // then quit browser
        }
    }

    
    /** Quits **all** managed drivers and clears the registry. */
    public static void quitAll()
    {
        drivers.values().forEach(WebDriver::quit); //SO BASICALLY THE WebDriver::quit is just calling the quit method from the WebDriver interface
        drivers.clear(); //clears the map
    }
    

}
