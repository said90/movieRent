package com.movierent.exception;

public class NotFoundExceptionModel extends RuntimeException {

	//recieving  message
	public  NotFoundExceptionModel(String message) {
		super(message);
	} 
}
