package com.babatunde.learningspring.webservice;

public class Response<T> {
    public T data;
    public String message;
    public Boolean status;
    public Exception error;

    public Response(T data, String message, Boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Response(Exception error, String message, Boolean status) {
        this.message = message;
        this.status = status;
        this.error = error;
    }

    public Response(T data, String message) {
        this.data = data;
        this.message = message;
        this.status = true;
    }

    public Response(String message, Error error) {
        this.message = message;
        this.status = false;
    }
}
