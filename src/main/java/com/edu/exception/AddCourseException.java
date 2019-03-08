package com.edu.exception;


public class AddCourseException extends Exception{

	//异常信息
    public String message;

    public AddCourseException(String message) {
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
