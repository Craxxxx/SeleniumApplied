package ui_qa.steps.negative;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import ui_qa.context.TestContext;
import ui_qa.pages.CartPage;
import ui_qa.pages.InventoryPage;
import ui_qa.pages.LoginPage;

public class CartNegativeSteps{
    private final TestContext context; //this is for getting the driver using the context TestContext dependency injection
    private LoginPage loginpage;// this is for navigating the pages
    private InventoryPage inventorypage;
    private CartPage cartpage;
    private String errorMessage;


    //constructor for this class
    public CartNegativeSteps(TestContext context)
    {
        this.context = context; //retrieve the drive to start the operations
    }


    //initiate web driver to open the browser //open the login page and login 
    @Given("I am logged in as {string} with password {string}")
    public void openAndLogin(String user, String pass)
    {
        WebDriver driver = context.getdriver(); //get the driver using picocontainer that reference the driverManager
        loginpage = new LoginPage(driver);//create new login page pom
        
        //1) open the login page and logged in as the user
        inventorypage = loginpage.open().loginAs(user,pass);
    }

    @And("I have navigated to the cart page")
    public void navigateToCart()
    {
        cartpage = inventorypage.navToCart(); //navigate to cart //the cartpage contains Cartpage POM with its driver
    }

    @And("my cart is empty")
    public void cartEmpty()
    {
        //just check if the cart is empty or not
       boolean empty = cartpage.checkCartEmpty(); //will contain true or false
       Assert.assertTrue(empty,"The cart is not empty this test will fails");
    }

    @When("I attempt to checkout")
    public void checkout()
    {
        errorMessage = cartpage.checkoutExpectingError();
    }

    @Then("I should see an error message {string}")
    public void verifyErrorMessage(String expected) {
        Assert.assertEquals(errorMessage, expected); //it is supposed to fail because the website cant handle the value
    }

}
