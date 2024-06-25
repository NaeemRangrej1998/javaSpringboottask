package com.crud_example.enums;

public enum ApiResponsesEnum {
    ORGANIZATION_INSERTED_SUCCESSFULLY("Organization Inserted Successfully", "ORGANIZATION_INSERTED_SUCCESSFULLY"),
    GET_ORGANIZATION_LIST("Get Organization List","GET_ORGANIZATION_LIST"),
    GET_ORGANIZATION_DETAILS("Get Organization Details","GET_ORGANIZATION_DETAILS"),
    ORGANIZATION_UPDATED_SUCCESSFULLY("Organization UPDATED Successfully","ORGANIZATION_UPDATED_SUCCESSFULLY"),
    ORGANIZATION_STATUS_UPDATED_SUCCESSFULLY("Organization Status Updated Successfully","ORGANIZATION_STATUS_UPDATED_SUCCESSFULLY"),
    USER_INSERTED_SUCCESSFULLY("User Inserted Successfully", "USER_INSERTED_SUCCESSFULLY") ,
    GET_ALL_USER_LIST("Get All User List","GET_ALL_USER_LIST"),
    USER_UPDATED_SUCCESSFULLY("User UPDATED Successfully", "USER_UPDATED_SUCCESSFULLY") ,
    GET_USER_DETAILS("Get User Details","GET_USER_DETAILS"),
    USER_STATUS_UPDATED_SUCCESSFULLY("User Status Updated Successfully","USER_STATUS_UPDATED_SUCCESSFULLY");



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
