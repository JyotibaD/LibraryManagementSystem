package com.LMS.Exception;

public class BookAllreadyPresentException extends RuntimeException{
    public BookAllreadyPresentException(String message){
        super(message);
    }
}
