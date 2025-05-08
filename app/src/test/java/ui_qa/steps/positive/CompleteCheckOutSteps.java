package ui_qa.steps.positive;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui_qa.context.TestContext;
import ui_qa.pages.CartPage;
import ui_qa.pages.CheckOutSummaryPage;
import ui_qa.pages.CheckoutPage;
import ui_qa.pages.InventoryPage;
import ui_qa.pages.LoginPage;
import ui_qa.pages.OrderSummaryPage;

public class CompleteCheckOutSteps {
    private final TestContext context;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderSummaryPage orderSummaryPage;
    private CheckOutSummaryPage checkOutSummaryPage;
    private String ItemCount; //itemCount from adding in the inventory page
    private List<WebElement> Items = new ArrayList<>();

    //inject the picoContainer
    public CompleteCheckOutSteps(TestContext context)
    {
        this.context = context;
    }


    //SETUP
    @Given("I logging in as {string} with a password {string}")
    public void login(String user, String pass)
    {
        WebDriver driver = context.getdriver();
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.open().loginAs(user, pass);
    }

    @And("I have added items to my cart")
    public void addItem()
    {
        ItemCount = inventoryPage.addMultipleCart();
    }
    //SETUP

    //THE TEST
    @When("I proceed to checkout with valid information")
    public void checkoutWithValidInfo()
    {
        //navigate to cart page
        cartPage = inventoryPage.navToCart(); 
        //proceed to checkout
        checkoutPage = cartPage.navtoCheckout();
        //fill all informations on checkout page
        checkoutPage.fillInfo();
       
    }

    @Then("I should see the order summary page")
    public void orderSummary()
    {
        //navigate to the overview page
        orderSummaryPage = checkoutPage.navToOrderSummary();

        //confirm im on the order summary page and confirm that every item i add is inside the order summary page
        Items = orderSummaryPage.allOrderItem();
        int numberOfItems = Items.size();
        String Actual = Integer.toString(numberOfItems);

        Assert.assertEquals(Actual, ItemCount); //assert that the number items in the cart is the same on the number of items in the OrderSummary


    }

    @Then("I should see a confirmation message {string}")
    public void checkOutSucces(String Expected)
    {
        //navigate to CheckOutSummaryPage
        checkOutSummaryPage = orderSummaryPage.navToCheckOutSummary();

        String SuccesBanner = checkOutSummaryPage.verifyCheckoutSucceded();

        Assert.assertEquals(SuccesBanner, Expected);

    }   

    
    //THE TEST

    
}
