package ui_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CheckoutPage {
    //create the driverContainer
    private final WebDriver driver;
    private final WebDriverWait wait;

    //locator
    private By inputFirstN = By.id("first-name");
    private By inputLastN  = By.id("last-name");
    private By inputZipCde = By.id("postal-code");

    //continue button locator
    private By ContButton = By.id("continue");

    //error banner locator
    private By errorBanner = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]");


    //constructor for this class
    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //actions
    public String fillInfoExpectingError()
    {
        WebElement f = wait.until(ExpectedConditions.elementToBeClickable(inputFirstN));
        f.clear();f.sendKeys("");
        WebElement l = wait.until(ExpectedConditions.elementToBeClickable(inputLastN));
        l.clear();l.sendKeys("Tanjung");
        WebElement z = wait.until(ExpectedConditions.elementToBeClickable(inputZipCde));
        z.clear();z.sendKeys("17432");

        wait.until(ExpectedConditions.elementToBeClickable(ContButton)).click(); //click the continue button


        WebElement bannerError = wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner));
        return bannerError.getText();
    }
    //actions

}
