package ui_qa.steps.negative;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.cucumber.java.Before;
import ui_qa.pages.CartPage;
import ui_qa.pages.InventoryPage;
import ui_qa.pages.LoginPage;
import ui_qa.Setup;



public class CartNegativeTest extends Setup{
    //declaring some object to navigate through
    private LoginPage loginPage;
    private CartPage cartpage;
    private String value;
    
    @BeforeClass
    public void init()
    {
        loginPage = new LoginPage(driver);
    }

    @Test(description = "do a checkout when the cart is empty")
    public void testEmptyCartCheckout()
    {
        //1) open the login screen
        InventoryPage inventory = loginPage.open().
        //2) do a login
        loginAs("standard_user","secret_sauce");

        //3)navigate to the cart
        CartPage cart = inventory.navToCart();

        //4) check if the cart is empty or not
        boolean cartEmpty = cart.checkCartEmpty();

        //5) try to checkout with empty cart
        if(cartEmpty == true)
        {
            value = cart.checkoutExpectingError();
        }
        else
        {
            value = "Cart is not empty";
        }

        Assert.assertEquals(value, ""); //the website cant handle the scenario. in order for the test to run continualy this test must pass
    }


}
