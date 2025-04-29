package ui_qa.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui_qa.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import io.cucumber.picocontainer.PicoFactory;

//import the context
import ui_qa.context.TestContext;


public class CucumberHook {

    private final TestContext context;

     // PicoContainer will inject TestContext here //basically a constructor for
    public CucumberHook(TestContext context) {
        this.context = context;
    }


    @Before
    public void beforeScenario()
    {
        System.out.println("Setting up driver for cucumbers BDD test");
        context.getdriver();
    }

    @After
    public void afterScenario()
    {
        System.out.println("Tearing down resources and driver for BDD Cucumber Test");
        context.getdriver().quit();
    }
}
