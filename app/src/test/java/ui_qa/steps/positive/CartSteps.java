package ui_qa.steps.positive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.*;
import ui_qa.context.TestContext;
import ui_qa.pages.CartPage;
import ui_qa.pages.InventoryPage;
import ui_qa.pages.LoginPage;

public class CartSteps {
    private final TestContext context;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartpage;
    private String valueofBadge;

    //list to verify all three items
    private List<String> multItemsCart = new ArrayList<>();

    //picoContainer Injector
    public CartSteps(TestContext context)
    {
        this.context = context;
    } 

    @Given("I log in as {string} with a password {string}")
    public void login(String user, String pass)
    {
        WebDriver driver = context.getdriver();
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.open().loginAs(user,pass);

        // Assert.assertTrue(inventoryPage.isLoaded());
    }

    @When("I add {string} to the cart")
    public void addSingleItem(String ItemName)
    {
        //i need to verify that the specific item is added
        valueofBadge = inventoryPage.addSingleCart();
    }

    @Then("the cart badge shows {string} item")
    public void verifySingleItemBadge(String expected)
    {
        Assert.assertEquals(valueofBadge, expected); //assert the the badge value is the same as the number of product added which is equal to 1
    }

    @Then("the cart page lists {string}")
    public void verifySingleItemContent(String expected)
    {
        //navtocart and verify its content
        cartpage = inventoryPage.navToCart();

        //verify its content (Sauce Labs Backpack)
        Assert.assertEquals(cartpage.checkSingleValue(), expected); 
        
    }


    //MULTIPLE
    @When("I add {string}, {string}, and {string} to the cart")
    public void addMultipleItem(String item1, String item2, String item3)
    {
        valueofBadge = inventoryPage.addMultipleCart();
        multItemsCart.add(item1);
        multItemsCart.add(item2);
        multItemsCart.add(item3);

    }

    @Then("the cart badge shows {string} items")
    public void verifyMultipleItemBadge(String expected)
    {
        Assert.assertEquals(valueofBadge, expected);
    }

    @Then("the cart page lists all three items")
    public void verifyMultipleItemContent()
    {
        //nav to cart first
        cartpage = inventoryPage.navToCart();
        
        //contain all items in the web
        // List<WebElement> Items = new ArrayList<>();
        // Items = cartpage.checkMultipleValue();

        List<String> Items = cartpage.checkMultipleValue().stream().map(WebElement::getText).collect(Collectors.toList());

        //create the hashset
        Set<String> Actual = new HashSet<>(Items);
        //int iterator = 0; 
        for(String expected: multItemsCart)
        {
            //assert that every value that needs to be inserted is the same on the displayed value (Cart Case)
            Assert.assertTrue(Actual.contains(expected), "Expected item not found in cart: " + expected);

        }   
    }
    //MULTIPLE


    //REMOVE
    @Given("I have added {string}, {string} to the cart")
    public void addRemovedItem(String item1, String item2)
    {
        inventoryPage.addItem2();

        //navigate to the cart page
        cartpage = inventoryPage.navToCart();     
    }

    @When("I remove {string} from the cart")
    public void removeItem(String removedItem)
    {
        valueofBadge = cartpage.removeItem();//removing the item from the cart
    }

    @Then("the cart badge shows {string}")
    public void verifyRemoveItemBadge(String expected)
    {
        //verify that the correspoding badge is the same as the number of items left
        Assert.assertEquals(valueofBadge, expected);
    }

    @Then("the cart page lists only the {string}")
    public void verifyRemoveItemContent(String expected)
    {
        //verify that the only item left inside the cart is "Sauce Labs Bike Light"
        String itemsOnCart = cartpage.checkSingleValue();
        Assert.assertEquals(itemsOnCart, expected);
    }
    //REMOVE
}
