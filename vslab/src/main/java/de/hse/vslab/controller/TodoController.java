package de.hse.vslab.controller;

import de.hse.vslab.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private List<Todo> todos = new ArrayList<>();

    @GetMapping
    public List<Todo> getAllTodos() {
        return todos;
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        todos.add(todo);
        return todo;
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                todo.setTitle(updatedTodo.getTitle());
                todo.setDone(updatedTodo.isDone());
                return todo;
            }
        }
        return null;
    }
}
