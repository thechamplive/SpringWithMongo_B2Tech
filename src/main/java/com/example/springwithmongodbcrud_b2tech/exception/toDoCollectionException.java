package com.example.springwithmongodbcrud_b2tech.exception;

public class toDoCollectionException extends Exception{

    /**
     *
     */

    private static final long serialVersionUID =1L;

    public toDoCollectionException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return "todo with id " + id + " not found";
    }

    public static String toDoAlreadyExists(){
        return "todo with given name already exists";
    }
}
