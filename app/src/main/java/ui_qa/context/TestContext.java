package ui_qa.context;

import org.openqa.selenium.WebDriver;
import ui_qa.utils.DriverManager;

public class TestContext{

    //this is a dependency injection to provide context to the LoginSteps
    
    private WebDriver driver;

    public WebDriver getdriver()
    {
        if(driver == null)
        {
            driver = DriverManager.getDriver("SiteA");
        }

        return this.driver;
    }

}
