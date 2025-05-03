package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // A post-login indicator locator
    private By inventoryContainerBy = By.id("inventory_container");
    //locating the cartButton
    private By cartButtonBy = By.className("shopping_cart_link");
    //locating addToCart button
    private By addItemByBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addItemByBikelight = By.id("add-to-cart-sauce-labs-bike-light");
    private By addItemByTshirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");

    //constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait for key element on inventory page
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainerBy));
    }

    // A simple check method
    public boolean isLoaded() {
        return driver.findElement(inventoryContainerBy).isDisplayed();
    }

    //ACTION METHODS

    //1) accessing cart
    public CartPage navToCart()
    {
        //wait for the cartButton to be clickable 
        WebElement c = wait.until(ExpectedConditions.elementToBeClickable(cartButtonBy));
        c.click();//click the cart button

        return new CartPage(driver); //this is used for chaining commands and navigate the cart page
        //return new CartPage object
    }

    //2)adding item to carts
    public void addItemCarts()
    {
        WebElement item1 = wait.until(ExpectedConditions.elementToBeClickable(addItemByBackpack));
        WebElement item2 = wait.until(ExpectedConditions.elementToBeClickable(addItemByBikelight));
        WebElement item3 = wait.until(ExpectedConditions.elementToBeClickable(addItemByTshirt));

        item1.click();
        item2.click();
        item3.click();
    }
    //ACTION METHODS
}
