package com.edu.exception;

public class AlreadyExistException extends Exception{

	//异常信息
    public String message;

    public AlreadyExistException(String message) {
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
