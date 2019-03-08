package com.edu.exception;

public class DeleteException extends Exception{

	//异常信息
    public String message;

    public DeleteException(String message) {
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
