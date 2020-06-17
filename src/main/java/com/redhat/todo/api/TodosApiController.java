package com.redhat.todo.api;

import com.redhat.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiParam;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.redhat.todo.model.Todo;
import com.redhat.todo.repository.TodoRepository;

@Controller
@RequestMapping("${openapi.todo.base-path:/v1}")
public class TodosApiController implements TodosApi {
    @Autowired
    TodoService todoService;

    @Override
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(value = "completed", required = false, defaultValue = "false") @Valid Boolean completed) {
        //TODO: Check if completed is null and return all values instead of defaulting it to false
        return todoService.getTodos(completed);
    }

    @Override
    public ResponseEntity<Todo> getTodo(@ApiParam(value = "A unique identifier for a `todo`.",required=true) @PathVariable("todoId") Integer todoId)  {
        return todoService.getTodo(todoId);
    }

    @Override
    public ResponseEntity<Void> createTodo(@Valid  @RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @Override
    public ResponseEntity<Void> updateTodo(@ApiParam(value = "A unique identifier for a `todo`.",required=true) @PathVariable("todoId") Integer todoId,@ApiParam(value = "Updated `todo` information." ,required=true )  @Valid @RequestBody Todo todo) {
        return todoService.updateTodo(todoId, todo);
    }

    @Override
    public ResponseEntity<Void> deleteTodo(@ApiParam(value = "A unique identifier for a `todo`.",required=true) @PathVariable("todoId") Integer todoId)  {
        return todoService.deleteTodo(todoId);
    }
}
