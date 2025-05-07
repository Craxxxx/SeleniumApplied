package ui_qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //class locator
    private By itemDetails = By.id("inventory_details");
    private By itemTitle = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]");
    private By productDesc = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]");
    private By price = By.className("inventory_details_price");
    private By addtoCartBtn = By.id("add-to-cart");
    private By removeBtn    = By.id("remove");
    
    //class constructor
    public DetailPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //ACTIONS
    public String checkTitle()
    {
        //return the value of the title
        return driver.findElement(itemTitle).getText();
    }

    public String checkProductDesc()
    {
        WebElement Desc = driver.findElement(productDesc);
        String text = Desc.getText();
        return text;
    }

    public String checkPrice()
    {
        WebElement Price = driver.findElement(price);
        String text = Price.getText();
        return text;
    }

    public boolean clickTocartAndVerify()
    {
        //locate the button
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addtoCartBtn));
        //click the button
        btn.click();
        WebElement rmv = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn));

        return rmv.isDisplayed();
    }
    //ACTIONS
}
