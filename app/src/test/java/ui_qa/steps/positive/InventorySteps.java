package ui_qa.steps.positive;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui_qa.pages.LoginPage;
import ui_qa.context.TestContext;
import ui_qa.helper.ProductSummary;
import ui_qa.pages.DetailPage;
import ui_qa.pages.InventoryPage;
import ui_qa.helper.ProductSummary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class InventorySteps {
    private final TestContext context;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private DetailPage detailPage;
    private ProductSummary productSummary;

    List<Object> Products = new ArrayList<>();

    //inject the testcontext here pico continer
    public InventorySteps(TestContext context){
        this.context = context; //inject context class
    }

    
    @Given("I log in as {string} with password {string}")
    public void login(String user, String pass)
    {
        //store the driver from the context
        WebDriver driver = context.getdriver();
        loginPage = new LoginPage(driver);

        //open the login page
        loginPage.open();

        //login
        inventoryPage = loginPage.loginAs(user, pass);
    }

    @When("I click the {string} item link")
    public void clickItemName(String itemName)
    {
        detailPage = inventoryPage.navToItemdetails(itemName);
    }


    @Then("I should see the {string} in the product title detail page")
    public void voidCheckContent(String expectedTitle)
    {
        //doing assertions of content of the detailPage//

        //check the product title
        String title = detailPage.checkTitle();
        Assert.assertEquals(title, expectedTitle);


        //CHECK PRODUCT DETAIL
        //check product description (string)
        String desc = detailPage.checkProductDesc();
        assertThat("Product title should not be empty",desc, not( is(emptyOrNullString())));

        //check the price
        String price = detailPage.checkPrice();
        assertThat("Price title should not be empty",price, not( is(emptyOrNullString())));
        //CHECK PRODUCT DETAIL


        //add to cart button functionality
        boolean rmv_btn = detailPage.clickTocartAndVerify();
        Assert.assertTrue(rmv_btn,"Remove button should be present" );
        
        
        //back to product link
    }

    @When("I am on the inventory page")
    public void navToInventory()
    {
        Assert.assertTrue(inventoryPage.isLoaded()); //true if in the inventory page 
    }


    @Then("I should see exactly 6 products displayed")
    public void productCount()
    {
        //assertions of 6 products displayed
        Products = inventoryPage.verifyInventoryItem();
        Assert.assertEquals(Products.size(), 6);
    }


    @Then("each product should show:")
    public void eachProduct(List<String> datatable) //accept the datatable in convert it into a list
    {
        //assertion of the value of each products
        for(Object product : Products)
        {
            ProductSummary summary = (ProductSummary) product; //typecast it back to its original class
            
            for(String field : datatable)
            {
                switch (field) {
                    case "name":
                        //name assertions
                        Assert.assertNotNull(summary.getName()); //make sure it is not null
                        Assert.assertFalse(summary.getName().isEmpty()); //make sure it is not empty

                    case "price":
                        //price assertions
                        Assert.assertNotNull(summary.getPrice());
                        Assert.assertFalse(summary.getPrice().isEmpty());

                    case "image":
                        //image assertions
                        Assert.assertTrue(summary.getImg().isDisplayed()); 

                }
            }            
        

            
        }
    }

}
