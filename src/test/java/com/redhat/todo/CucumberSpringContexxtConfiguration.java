package com.redhat.todo;

import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(classes = OpenAPI2SpringBoot.class, webEnvironment = DEFINED_PORT)
@ContextConfiguration(classes = OpenAPI2SpringBoot.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContexxtConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContexxtConfiguration.class);

    @Before
    public void setUp() {
      LOG.info("================ Spring Context Initialized ================");
    }

}
