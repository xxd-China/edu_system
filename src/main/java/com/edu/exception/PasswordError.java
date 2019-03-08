package com.edu.exception;

public class PasswordError extends Exception{

	//异常信息
    public String message;

    public PasswordError(String message) {
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
