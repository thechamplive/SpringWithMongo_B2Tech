package com.example.springwithmongodbcrud_b2tech.service;

import com.example.springwithmongodbcrud_b2tech.exception.toDoCollectionException;
import com.example.springwithmongodbcrud_b2tech.model.ToDoDTO;
import com.example.springwithmongodbcrud_b2tech.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class toDoServiceImpl implements toDoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(ToDoDTO todo) throws ConstraintViolationException, toDoCollectionException{
        Optional<ToDoDTO> optional = todoRepository.findByTodo(todo.getTodo());

        if (optional.isPresent()){
            throw new toDoCollectionException(toDoCollectionException.toDoAlreadyExists());
        } else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todo);
        }

    }

    @Override
    public List<ToDoDTO> getAllTodos() {
        List<ToDoDTO> todos = todoRepository.findAll();
        if (todos.size() > 0){
            return todos;
        } else {
            return new ArrayList<ToDoDTO>();
        }
    }

    @Override
    public ToDoDTO getSingleTodo(String id) throws toDoCollectionException{
        Optional<ToDoDTO> optional = todoRepository.findById(id);
        if (!optional.isPresent()){
            throw new toDoCollectionException(toDoCollectionException.NotFoundException(id));
        }
        else {
            return optional.get();
        }
    }

    @Override
    public void deleteTodo(String id) throws toDoCollectionException {
        Optional<ToDoDTO> optional = todoRepository.findById(id);
        if (!optional.isPresent()){
            throw new toDoCollectionException(toDoCollectionException.NotFoundException(id));
        } else {
            todoRepository.deleteById(String.valueOf(optional));
        }


    }

    @Override
    public void updateTodo(String id, ToDoDTO todo) throws toDoCollectionException {
        Optional<ToDoDTO> id1 = todoRepository.findById(id);
        Optional<ToDoDTO> same = todoRepository.findByTodo((todo.getTodo()));

        if (id1.isPresent()){

            if(same.isPresent() && !same.get().getId().equals(id)){
                throw new toDoCollectionException(toDoCollectionException.toDoAlreadyExists());
            }

        }

        if (id1.isPresent()){
            ToDoDTO save = id1.get();
            save.setTodo(todo.getTodo());
            save.setDescription(todo.getDescription());
            save.setCompleted(todo.getCompleted());
            save.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(save);
        } else{
            throw new toDoCollectionException(toDoCollectionException.NotFoundException(id));
        }
    }
}
