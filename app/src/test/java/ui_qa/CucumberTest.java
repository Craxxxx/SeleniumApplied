package ui_qa;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"ui_qa.steps","ui_qa.hooks","ui_qa.context"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class CucumberTest extends AbstractTestNGCucumberTests{
    // no methods needed
}
