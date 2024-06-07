package com.crud_example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class ErrorDetails {
    private Date timestamp;
    private String message;
//    private int status;
    private Object error;
    private String path;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ErrorDetails(HttpStatus httpStatus,Date timestamp, String message, String path) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.error = httpStatus.name();
        this.message = message;
        this.path = path;
//        this.status = httpStatus.value();
    }

    public ErrorDetails(Date date) {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorDetails(HttpStatus httpStatus, Date timestamp, String message, String path, List<String> error) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
//        this.status = httpStatus.value();
        this.error = error;
        this.message = message;
        this.path = path;
    }

//    public ErrorDetails(Date timestamp, String path, List<String> error) {
//        this.timestamp = timestamp;
//        this.error = error;
//        this.path = path;
//    }

}
