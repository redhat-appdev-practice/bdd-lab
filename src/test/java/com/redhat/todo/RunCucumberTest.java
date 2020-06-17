package com.redhat.todo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@RunWith(Cucumber.class)
@CucumberOptions( plugin = {"pretty"}, features = "src/test/resources" )
public class RunCucumberTest {
}