package ui_qa.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
            //create a new driver and put it in the map with the name key
            WebDriverManager.chromedriver().setup(); //this methods automatically downloads and configures the correct driver binary at runtime
            WebDriver driver = new ChromeDriver();
            drivers.put(name, driver);
        } 

        return drivers.get(name);//return the value which the drivers key name is pointing to it is the driver object
    }

    
    /** Quits **all** managed drivers and clears the registry. */
    public static void quitAll()
    {
        drivers.values().forEach(WebDriver::quit); //SO BASICALLY THE WebDriver::quit is just calling the quit method from the WebDriver interface
        drivers.clear(); //clears the map
    }
    

}
