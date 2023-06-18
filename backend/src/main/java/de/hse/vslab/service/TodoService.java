package de.hse.vslab.service;

import de.hse.vslab.model.Todo;
import de.hse.vslab.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDone(false);
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setTitle(updatedTodo.getTitle());
            todo.setDone(updatedTodo.isDone());
            return todoRepository.save(todo);
        }
        return null;
    }

    public boolean deleteTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
