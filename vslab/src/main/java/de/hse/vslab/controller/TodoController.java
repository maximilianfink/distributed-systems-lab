package de.hse.vslab.controller;

import de.hse.vslab.model.Todo;
import de.hse.vslab.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDone(false);
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setTitle(updatedTodo.getTitle());
            todo.setDone(updatedTodo.isDone());
            return todoRepository.save(todo);
        }
        return null;
    }
}