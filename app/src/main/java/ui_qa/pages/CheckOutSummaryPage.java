package ui_qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutSummaryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //locator
    private By CheckOutComplete = By.className("complete-header");

    //CONSTRUCTOR
    public CheckOutSummaryPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String verifyCheckoutSucceded()
    {
        WebElement succesBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(CheckOutComplete));

        //get the success text
        String BannerSuccess = succesBanner.getText();

        return BannerSuccess;
    }

}
