package com.redhat.todo;

import com.redhat.todo.model.Todo;
import com.redhat.todo.service.TodoService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefs {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions res;

    static String todo;
    static int response;
    private static final Logger LOG = Logger.getLogger( StepDefs.class.getName() );

    @Given("a todo object with the following data:")
    public void a_todo_object_with_the_following_data(List<Map<String, String>> todoMap) throws Exception {
        todo = String.format(
            "{" +
                "\"name\": \"%s\"," +
                "\"description\": \"%s\"," +
                "\"completed\": %s," +
                "\"date\": \"%s\"" +
            "}",
            todoMap.get(0).get("name"),
            todoMap.get(0).get("description"),
            todoMap.get(0).get("completed"),
            todoMap.get(0).get("date")
        );
    }


    @When("^I create a todo$")
    public void i_create_a_todo() throws Exception {
        res = mockMvc.perform(post("/v1/todos").content(todo).contentType("application/json")).andDo(print());
    }

    @Then("^I should get a response of (\\d+)$")
    public void i_should_get_a_response_of(int expectedResponse) throws Exception {
        res.andExpect(status().isCreated());
    }


}