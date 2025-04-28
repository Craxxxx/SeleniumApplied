package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    //LOCATORS for the login page elements
    private By usernameBy = By.id("user-name"); //id of the username input field
    private By passwordBy = By.id("password");
    private By loginBtnBy = By.id("login-button");
    //only the page itself knows how to find the elements on the page

    //login page CONSTRUCTOR
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //pass the webdriver instance from your test or driver manager to the page constructor


    //Action methods
    public void login(String user, String pass)
    {
        driver.findElement(usernameBy).sendKeys(user);
        driver.findElement(passwordBy).sendKeys(pass);
        driver.findElement(loginBtnBy).click();
    }

    //open the login page
    public LoginPage open()
    {
        //open the website using the driver instance from the setup class
        driver.get("https://www.saucedemo.com/");
        //wait for the username input field t be visible before return the login page object
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameBy));

        //return the login page object itself so you can chain the methods
        return this;
    }


    /* this function loginAs declared as an instance of the InventoryPage class because after a succesful login the app navigates away
     * from the login page and lands on the inventory(post-login) screen.
     * 
     * IN POM design, each page class represents a distint UI context - so you return the page object that matches the next screen you are on
    */
    //perform login and return next page object
     public InventoryPage loginAs(String user, String pass) {
        //this code uses explicit waits to wait for the elements to be clickable before interacting -- to ensure stability by not interacting to early
        //clear the input fields before sending keys to them


        WebElement u = wait.until(ExpectedConditions.elementToBeClickable(usernameBy));
        u.clear(); u.sendKeys(user);

        WebElement p = wait.until(ExpectedConditions.elementToBeClickable(passwordBy));
        p.clear(); p.sendKeys(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginBtnBy)).click();
        return new InventoryPage(driver); // return the new inventory page object after login
    }




}
