package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // A post-login indicator
    private By inventoryContainerBy = By.id("inventory_container");

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
}
