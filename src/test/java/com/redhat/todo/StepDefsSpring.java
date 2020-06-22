package com.redhat.todo;

import com.redhat.todo.model.Todo;
import com.redhat.todo.service.TodoService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class StepDefsSpring {

    static Todo todo;
    static int response;
    private static final Logger LOG = Logger.getLogger( StepDefsSpring.class.getName() );

    @Autowired
    public TodoService todoService;

    @Given("a todo object with the following data:")
    public void a_todo_object_with_the_following_data(List<Map<String, String>> todoMap) throws Exception {
        todo = new Todo();

        todo.setCompleted(Boolean.valueOf(todoMap.get(0).get("completed")));
        todo.setName(todoMap.get(0).get("name"));
        todo.setDescription(todoMap.get(0).get("description"));
        todo.setDate(OffsetDateTime.now());

    }


    @When("^I create a todo$")
    public void i_create_a_todo() throws Exception {
        Gson gson = new Gson();
        LOG.info("entering create\n\n" +  gson.toJson(todo));
        ResponseEntity responseEntity = todoService.createTodo(todo);
        response = responseEntity.getStatusCodeValue();
    }

    @Then("^I should get a response of (\\d+)$")
    public void i_should_get_a_response_of(int expectedResponse) throws Exception {
        Assert.assertTrue(expectedResponse == response);
    }


}