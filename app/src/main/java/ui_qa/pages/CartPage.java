package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    //create the driverContainer
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Locators for the cart page
    private By checkoutBy = By.id("checkout"); //checkout button

    //Locator for the cart-Item
    private By cartItemBy = By.className("cart_item");

    //Locator for the error message
    private By errorBannerBy   = By.cssSelector(".error-message-container");


    //CONSTRUCTOR FOR THIS CLASS
    public CartPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //ACTION METHODS
    public boolean checkCartEmpty()
    {
        //check if the CART ITEM is available

        //return driver.findElement(cartItemBy).isDisplayed(); //so basically u cant use this function because if there's no such elements not even one match,
        //it will throw an exeption
        //thats why i'd have to catch and swallow that exception to return false which make the code not clean
        
        //SOLUTION
        //USE findElements which always returns a possibly empty. i can simply check isEmpty(); because it is a list just checkif the list is empty
        return driver.findElements(cartItemBy).isEmpty(); //returns true if the cart is really empty
        
    }

    //performing supposed failed checkout
    public String checkoutExpectingError()
    {
        WebElement E = wait.until(ExpectedConditions.elementToBeClickable(checkoutBy));
        E.click(); //click the button   

        //wait for the error banner to appear
        //WebElement bannerError = wait.until(ExpectedConditions.visibilityOfElementLocated(errorBannerBy)); //cant use this because it will wait, and if there is no such conditions appeared it will just throw an error
     
        //instead use this
        if(driver.findElements(errorBannerBy).isEmpty()) //if the elements is not present at all
        {
            return "";
        }

        return driver.findElement(errorBannerBy).getText();
    }

    //performing supposed succesfull checkout
    public CheckoutPage navtoCheckout()
    {
        WebElement E = wait.until(ExpectedConditions.elementToBeClickable(checkoutBy));
        E.click(); //click the button CHECKOUT BUTTON to move to checkout 
        
        return new CheckoutPage(driver);//return the driver to create the checkoutPage POM
    }

    //ACTION METHODS

}
