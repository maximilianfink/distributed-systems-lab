package de.hse.vslab.controller;

import de.hse.vslab.model.Todo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import de.hse.vslab.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestParam String title) {
        return todoService.createTodo(title);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        return todoService.updateTodo(id, updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        boolean deleted = todoService.deleteTodoById(id);
        if (deleted) {
            return ResponseEntity.ok("Todo successfully deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
