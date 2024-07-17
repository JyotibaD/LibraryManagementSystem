package com.LMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotBlankException extends RuntimeException{
    public NotBlankException(String meassage){
        super(meassage);
    }
}
