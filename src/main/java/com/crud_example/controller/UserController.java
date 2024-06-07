package com.crud_example.controller;

import com.crud_example.dto.ApiResponse;
import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.RequestUserDTO;
import com.crud_example.dto.UserDTO;
import com.crud_example.enums.ApiResponsesEnum;
import com.crud_example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> insertOrganization(@RequestBody RequestUserDTO requestUserDTO){
        UserDTO userDTO=userService.insertUpdateUser(requestUserDTO);
        return new ResponseEntity<>(
                new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_INSERTED_SUCCESSFULLY.getValue(), userDTO),
                HttpStatus.OK);
    }
}
