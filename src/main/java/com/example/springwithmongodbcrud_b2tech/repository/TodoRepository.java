package com.example.springwithmongodbcrud_b2tech.repository;

import com.example.springwithmongodbcrud_b2tech.model.ToDoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<ToDoDTO, String> {

    @Query("{'todo': ?0}")
    Optional<ToDoDTO> findByTodo(String todo);



}
