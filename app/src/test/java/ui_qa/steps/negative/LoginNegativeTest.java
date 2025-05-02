package ui_qa.steps.negative;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ui_qa.pages.LoginPage;
import ui_qa.Setup;

//this is failed login testing just using testNG


public class LoginNegativeTest extends Setup{
    //declaring the loginpage object
    private LoginPage loginPage;

    
    @BeforeClass
    public void init()
    {
        //initialize the login page object and store it to the loginPage variable
        loginPage = new LoginPage(driver);
    }

    @Test(description = "login with invalid credentials")
    public void testInvalidLogin()
    {
                              //1. open the login screen
        String errormessage = loginPage.open().
        //enter the username and password
        loginExpectingError("standard_user","WrongPass");

        //assert the error message
        Assert.assertEquals(errormessage,"Epic sadface: Username and password do not match any user in this service");

    }

}


