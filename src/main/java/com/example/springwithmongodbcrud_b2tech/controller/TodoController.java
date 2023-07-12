package com.example.springwithmongodbcrud_b2tech.controller;

import com.example.springwithmongodbcrud_b2tech.exception.toDoCollectionException;
import com.example.springwithmongodbcrud_b2tech.model.ToDoDTO;
import com.example.springwithmongodbcrud_b2tech.repository.TodoRepository;
import com.example.springwithmongodbcrud_b2tech.service.toDoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.springwithmongodbcrud_b2tech.model.ToDoDTO;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Controller
public class TodoController {
    private final TodoRepository repository;
    @Autowired
    private toDoService toDoService;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<ToDoDTO> toDoDTOList = toDoService.getAllTodos();

        return new ResponseEntity(toDoDTOList, toDoDTOList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody ToDoDTO todo){
        try{
            toDoService.createTodo(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (toDoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable String id){
       try {
           return new ResponseEntity<>(toDoService.getSingleTodo(id), HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable String id, @RequestBody ToDoDTO todo){
        try {
            toDoService.updateTodo(id, todo);
            return new ResponseEntity<>("Updated todo with id "+ id, HttpStatus.OK);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (toDoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        try {
            toDoService.deleteTodo(id);
            return new ResponseEntity<>("Object successfully deleted with id " + id, HttpStatus.OK);
        }catch (toDoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
