package com.crud_example.exception;

import com.crud_example.dto.ApiResponse;
import com.crud_example.dto.ErrorDetails;
import com.crud_example.utils.BaseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest req) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
        ErrorDetails errorDetails = new ErrorDetails(httpStatus, new Date(), httpStatus.name(), req.getServletPath(),errorMessages);
        ApiResponse apiResponse = new ApiResponse(httpStatus, httpStatus.name(), errorDetails);
        return ResponseEntity.status(httpStatus).body(apiResponse);

//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        ApiResponse apiResponse = new ApiResponse(errors, HttpStatus.BAD_REQUEST);
//        return apiResponse;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = new ErrorDetails(httpStatus, new Date(), ex.getMessage(), req.getServletPath());
        ApiResponse apiResponse=new ApiResponse(httpStatus,httpStatus.name(),errorDetails);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(HttpServletRequest req, CustomException e) {
        HttpStatus httpStatus = e.getHttpStatus();
        ErrorDetails errorDetails = new ErrorDetails(httpStatus, new Date(), e.getMessage(), req.getServletPath());
        errorDetails.setError(e.getMessage());
//        errorDetails.setMessage(httpStatus.name());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDetails);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDetails errorDetails = new ErrorDetails(httpStatus, new Date(),
                "Something went wrong", req.getServletPath());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDetails);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }
}
