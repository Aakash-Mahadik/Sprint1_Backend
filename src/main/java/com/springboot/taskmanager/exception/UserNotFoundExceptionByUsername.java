package com.springboot.taskmanager.exception;

public class UserNotFoundExceptionByUsername extends RuntimeException  {

	 public UserNotFoundExceptionByUsername(String entityName, String identifier, String value) {
	        super(entityName + " with " + identifier + " '" + value + "' not found.");
	    }
}

