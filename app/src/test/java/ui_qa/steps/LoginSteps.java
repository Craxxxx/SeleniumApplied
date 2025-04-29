package ui_qa.steps;

import io.cucumber.java.en.*;

import java.sql.DriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui_qa.pages.LoginPage;
import ui_qa.context.TestContext;
import ui_qa.pages.InventoryPage;


//so for running the test with cucumber u dont run the LoginTest class
public class LoginSteps{
    private final TestContext context;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

      // PicoContainer will inject TestContext here
    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("I open the SauceDemo login page")
    public void openLoginPage()
    {
        WebDriver driver = context.getdriver();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @When("I login as {string} with password {string}")
    public void login(String user, String pass)
    {
        inventoryPage = loginPage.loginAs(user, pass);
    }

    @Then("i should see the inventory page")
    public void verifyInventoryPage()
    {
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page is not loaded");
    }

}
