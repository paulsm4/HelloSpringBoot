package com.vogella.springboot2.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.vogella.springboot2.data.TodoRepository;
import com.vogella.springboot2.domain.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) { 
        this.todoRepository = todoRepository;
        createTodoModel();
    }

    private void createTodoModel() {
        Todo todo = new Todo(1);
        todo.setSummary("Learn Spring Boot 2.0");
        todo.setDescription("Easily create modern reactive webapps with Spring Boot 2.0");
        todo.setCategory("spring");

        Todo todo2 = new Todo(2);
        todo2.setSummary("Learn Reactor Framework");
        todo2.setDescription("Use the power of the reactive io api with the Reactor Framework");
        todo2.setCategory("reactive");

        Todo todo3 = new Todo(3);
        todo3.setSummary("Learn @RestController");
        todo3.setDescription("Learn how to create @RestController and use rest endpoints");
        todo3.setCategory("spring");

        todoRepository.saveAll(Arrays.asList(todo, todo2, todo3)).subscribe(); 
    }

    public Flux<Todo> getTodos(long limit) {
        if (-1 == limit) {
            return todoRepository.findAll();
        }
        return todoRepository.findAll().take(limit);
    }

    public Mono<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }

    public Flux<Todo> getBySummary(String summary) {
        return todoRepository.findBySummaryContainingIgnoreCase(summary);
    }

    public Flux<Todo> getByCategory(String categoryName) {
        return todoRepository.findByCategory(categoryName);
    }

    public Mono<Todo> newTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Mono<Void> deleteTodo(long id) {
        return todoRepository.deleteById(id);
    }
}
