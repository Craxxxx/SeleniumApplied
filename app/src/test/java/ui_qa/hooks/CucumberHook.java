package ui_qa.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui_qa.utils.DriverManager;
import org.openqa.selenium.WebDriver;
//import io.cucumber.picocontainer.PicoFactory;  no need to use this because you already added it the build.gradle

//import the context
import ui_qa.context.TestContext;

//THIS CLASS IS BASICALLY USED TO REPLICATE THE SETUP.JAVA BECAUSE IT THE @BeforeSuites and @AfterSuites only works for on a single test suites
//In my Test design i make so that the testing using and testNG and testing using cucumber/gherkin format into a different test suites
public class CucumberHook {

    private final TestContext context;

     // PicoContainer will inject TestContext here //basically a constructor for this class
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
        //context.getdriver().quit();
        DriverManager.quitAndRemove("SiteA");
        
    }
}
