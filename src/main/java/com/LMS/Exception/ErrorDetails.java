package com.LMS.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
public class ErrorDetails {

    private String message;

    public ErrorDetails(String message) {
        super();

        this.message = message;

    }


}
