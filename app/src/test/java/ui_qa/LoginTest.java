package ui_qa;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ui_qa.pages.InventoryPage;
import ui_qa.pages.LoginPage;

public class LoginTest extends Setup {
    private LoginPage loginPage; //declaring the loginpage instance variable

    @BeforeClass
    public void init()
    {
        //initialize the login page object with the driver instance from the setup class
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Login with valid credentials")
    public void testValidLogin()
    {
        //1. open the login screen
        InventoryPage inventory = loginPage.open()
            //2.perform login
            .loginAs("standard_user", "secret_sauce");

        //3.inventory holds the object ob the InventoryPage class /Assert that the inventory page is loaded
        Assert.assertTrue(inventory.isLoaded(), "Inventory page is not loaded");
        
    }
}
