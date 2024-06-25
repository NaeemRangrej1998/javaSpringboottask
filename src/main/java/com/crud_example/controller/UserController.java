package com.crud_example.controller;

import com.crud_example.dto.*;
import com.crud_example.enums.ApiResponsesEnum;
import com.crud_example.enums.ExceptionEnum;
import com.crud_example.exception.CustomException;
import com.crud_example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> insertUser(@RequestBody RequestUserDTO requestUserDTO){
        UserDTO userDTO=userService.insertUpdateUser(requestUserDTO);
        return new ResponseEntity<>(
                new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_INSERTED_SUCCESSFULLY.getValue(), userDTO),
                HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                                   @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortAs, "id"));
        Page<UserDTO> userDTO=userService.getAllUser(pageable,searchValue.trim());
        return new ResponseEntity<>(
                new ApiResponse(HttpStatus.OK, ApiResponsesEnum.GET_ALL_USER_LIST.getValue(), userDTO),
                HttpStatus.OK);
    }
    @PutMapping(value = "/updateUserById/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @RequestBody RequestUserDTO requestUserDTO) {

        try {
            requestUserDTO.setId(userId);
            UserDTO userDTO = userService.insertUpdateUser(requestUserDTO);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_UPDATED_SUCCESSFULLY.getValue(), userDTO),
                    HttpStatus.OK);

        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/getUserById/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getUserByUserId(@PathVariable Long userId) {

        try {
            UserDTO userDTO = userService.getUserById(userId);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.GET_USER_DETAILS.getValue(), userDTO),
                    HttpStatus.OK);

        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/status/{userId}/{activeStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateOrganizationStatus(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "activeStatus") Boolean activeStatus) {
        try {
            UserDTO userDTO = userService.updateUserStatus(userId, activeStatus);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_STATUS_UPDATED_SUCCESSFULLY.getValue(), userDTO),
                    HttpStatus.OK);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
