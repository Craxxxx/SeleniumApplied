package ui_qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSummaryPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    //list of items inside cart
    List<WebElement> items = new ArrayList<>();

    //locator
    private By itemsinCart = By.className("cart_item");

    private By finishButton = By.id("finish");

    public OrderSummaryPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //ACTIONS
    public List<WebElement> allOrderItem()
    {
        items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemsinCart));
        return items;
    }

    public CheckOutSummaryPage navToCheckOutSummary()
    {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
        btn.click();

        return new CheckOutSummaryPage(driver);
    }
    //ACTIONS
}
