package com.example.springwithmongodbcrud_b2tech.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class ToDoDTO {

    @Id
    private String id;

    @NotNull(message = "todo cannot be null")
    private String todo;
    @NotNull(message = "todo cannot be null")
    private String description;
    @NotNull(message = "todo cannot be null")
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;

}
