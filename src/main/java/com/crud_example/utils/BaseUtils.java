package com.crud_example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class BaseUtils {

    public static String getJson(Object object){
        ObjectMapper obj = new ObjectMapper();
        try {
            return obj.writer().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
