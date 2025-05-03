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

    @Test(description = "missing username on the username filed in the login page")
    public void testEmptyUser()
    {
        //1. open the login screen using function below will automatically clear the previous session
        String errormesage = loginPage.open().
        //2.enter the username and password
        loginExpectingError("", "secret_sauce");
        //3. assert the error message
        Assert.assertEquals(errormesage, "Epic sadface: Username is required");
    }

    @Test(description = "Missing password on the password field in the login page")
    public void testEmptyPasword()
    {
        String errormessage = loginPage.open().
        loginExpectingError("standard_user", "");

        Assert.assertEquals(errormessage,"Epic sadface: Password is required");
    }

}


