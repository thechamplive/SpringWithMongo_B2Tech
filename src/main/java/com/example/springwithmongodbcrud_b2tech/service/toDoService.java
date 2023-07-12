package com.example.springwithmongodbcrud_b2tech.service;

import com.example.springwithmongodbcrud_b2tech.model.ToDoDTO;
import jakarta.validation.ConstraintViolationException;
import com.example.springwithmongodbcrud_b2tech.exception.toDoCollectionException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface toDoService {



    public void createTodo(ToDoDTO todo) throws ConstraintViolationException, toDoCollectionException;

    public List<ToDoDTO> getAllTodos();

    public ToDoDTO getSingleTodo(String id) throws toDoCollectionException;

    public void deleteTodo(String id) throws toDoCollectionException;

    public void updateTodo (String id, ToDoDTO todo)throws toDoCollectionException;
}
