package com.crud_example.enums;

public enum ApiResponsesEnum {
    ORGANIZATION_INSERTED_SUCCESSFULLY("Organization Inserted Successfully", "ORGANIZATION_INSERTED_SUCCESSFULLY"),
    GET_ORGANIZATION_LIST("Get Organization List","GET_ORGANIZATION_LIST"),
    ORGANIZATION_UPDATED_SUCCESSFULLY("Organization Updated Successfully","ORGANIZATION_UPDATED_SUCCESSFULLY");
    private final String value;

    private String message;

    ApiResponsesEnum(String value) {
        this.value = value;
    }

    ApiResponsesEnum(String message, String value) {
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
