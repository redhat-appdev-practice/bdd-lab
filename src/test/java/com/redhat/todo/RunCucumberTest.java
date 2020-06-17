package com.redhat.todo;

import com.redhat.todo.config.OpenAPIDocumentationConfig;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources")
@SpringBootTest(classes = { OpenAPI2SpringBoot.class, OpenAPIDocumentationConfig.class }, webEnvironment = DEFINED_PORT)
public class RunCucumberTest {
}