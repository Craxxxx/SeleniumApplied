package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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
    //individual item locator

    
    //item locator using dynamic map
    private final Map<String,By> itemLocator = new HashMap<>();

    //constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait for key element on inventory page
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainerBy));

        //since i cant put the item id in the gherkin format this will do
        itemLocator.put("Sauce Labs Backpack", By.id("item_4_title_link"));
        itemLocator.put("Sauce Labs Bike Light", By.id("item_0_title_link"));
        itemLocator.put("Sauce Labs Bolt T-Shirt", By.id("item_1_title_link"));
        itemLocator.put("Sauce Labs Fleece Jacket", By.id("item_5_title_link"));
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

    public DetailPage navToItemdetails(String item)
    {
        By currentItem = null; //first initiation
        //check if the item and By couple is already created inside the map
        if(itemLocator.containsKey(item)) //if the key value is found with its related value
        {
            currentItem = itemLocator.get(item); //get the by value from the key value
        }
        WebElement itemLink = wait.until(ExpectedConditions.elementToBeClickable(currentItem));//wait for the link to be clickable

        itemLink.click();
        return new DetailPage(driver);
    }

 
    //ACTION METHODS
}
