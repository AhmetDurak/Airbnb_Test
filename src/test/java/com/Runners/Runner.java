package com.Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target//cucumber.json"},
        features = "src/test/resources",
        glue = "src/test/java/com/StepDefinitions",
        dryRun = false,
        tags = "@airbnb"
)

public class Runner {
}
