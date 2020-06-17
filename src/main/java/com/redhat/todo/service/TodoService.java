package com.redhat.todo.service;

import com.redhat.todo.model.Todo;
import com.redhat.todo.repository.TodoRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.List;

@Component
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public ResponseEntity<List<Todo>> getTodos(Boolean completed){
        return ResponseEntity.ok(todoRepository.getByCompleted(completed));
    }

    public ResponseEntity<Todo> getTodo(Integer todoId)  {
        return ResponseEntity.ok(todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public ResponseEntity<Void> createTodo(Todo todo) {
        todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> updateTodo( Integer todoId, Todo todo) {
        todo.setId(todoId);
        todoRepository.save(todo);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity<Void> deleteTodo( Integer todoId)  {
        todoRepository.deleteById(todoId);
        return ResponseEntity.noContent().build();
    }
}
