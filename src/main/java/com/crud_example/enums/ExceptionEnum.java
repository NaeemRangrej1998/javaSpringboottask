package com.crud_example.enums;

public enum ExceptionEnum {

    SOMETHING_WENT_WRONG("Something went wrong");

    private String value;

    private String message;

    ExceptionEnum(String value) {
        this.value = value;
    }

    ExceptionEnum(String message, String value) {
        this.message = message;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
