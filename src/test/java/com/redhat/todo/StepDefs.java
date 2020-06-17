package com.redhat.todo;

import com.redhat.todo.api.TodosApiController;
import com.redhat.todo.config.OpenAPIDocumentationConfig;
import com.redhat.todo.model.Todo;
import com.redhat.todo.repository.TodoRepository;
import com.redhat.todo.service.TodoService;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@ContextConfiguration(classes = { OpenAPI2SpringBoot.class, OpenAPIDocumentationConfig.class }, loader = SpringBootContextLoader.class )
public class StepDefs {

    static Todo todo;
    static int response;
    private static final Logger LOG = Logger.getLogger( StepDefs.class.getName() );

    @Autowired
    private TodoService todoService;

    @Given("a todo object with the following data:")
    public void a_todo_object_with_the_following_data(List<Map<String, String>> todoMap) throws Exception {
        todo = new Todo();

        todo.setCompleted(Boolean.valueOf(todoMap.get(0).get("completed")));
        todo.setName(todoMap.get(0).get("name"));
        todo.setDescription(todoMap.get(0).get("description"));
    }


    @When("^I create a todo$")
    public void i_create_a_todo() throws Exception {
        LOG.info("entering create");
        ResponseEntity responseEntity = todoService.createTodo(todo);
        response = responseEntity.getStatusCodeValue();
    }

    @Then("^I should get a response of (\\d+)$")
    public void i_should_get_a_response_of(int expectedResponse) throws Exception {
        Assert.assertTrue(expectedResponse == response);
    }


}