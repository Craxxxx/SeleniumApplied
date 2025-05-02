package ui_qa.steps.negative;
//use cucumber

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import ui_qa.context.TestContext;
import ui_qa.pages.InventoryPage;
import ui_qa.pages.LoginPage;

public class LoginNegativeSteps {

    private final TestContext context; //this is for getting the driver using the context TestContext dependency injection
    private LoginPage loginpage;// this is for navigating the pages
    private String errorMessage;

    //constructor for the context object that belongs to LoginNegativeSteps
    public LoginNegativeSteps(TestContext context)
    {
        this.context = context;  //retrieve the drivers
    }

    //initiate webdriver to open the browser
    @Given("i open the sauce demo login page")
    public void openLoginPage()
    {
        WebDriver driver = context.getdriver();//fetch the driver
        loginpage = new LoginPage(driver); //create the loginpage object for navigation
        loginpage.open(); // open the login page
    }

    @When ("i login as {string} with password {string}")
    public void login(String user, String pass)
    {
        errorMessage = loginpage.loginExpectingError(user, pass);
    }


    @Then("i should see an error message {string}")
    public void verifyErrorMessage(String expected)
    {
        Assert.assertEquals(errorMessage,expected,"Error message did not match as expected");
    }
}
