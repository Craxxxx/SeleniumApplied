package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

//import helper
import ui_qa.helper.ProductSummary;

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

    //item detail locator
    private By inventoryItems = By.className("inventory_item");
    private By itemName = By.className("inventory_item_name");
    private By itemPrice = By.className("inventory_item_price");
    private By itemImg = By.className("inventory_item_img");

    
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

    //navToItemDetails
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

  

    public List<Object> verifyInventoryItem()
    {
            //create array list to contain all the inventory_item class
            List<WebElement> e = new ArrayList<>();

            //create array list to contain all attributes inside the parent class
            List<Object> allproduct = new ArrayList<>();

            e = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inventoryItems)); //containts all the WebElements
            for(WebElement Item : e)
            {
                //foreach item in the list
                String name = Item.findElement(itemName).getText(); //get name
                String price = Item.findElement(itemPrice).getText(); //get name
                WebElement img = Item.findElement(itemImg); //get name

                //make an object to contain all this
                allproduct.add(new ProductSummary(name, price, img)); // and store it inside a list
            }
            
            return allproduct;
    } 


    //ACTION METHODS
}
