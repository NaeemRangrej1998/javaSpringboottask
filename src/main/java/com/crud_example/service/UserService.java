package com.crud_example.service;

import com.crud_example.dto.RequestUserDTO;
import com.crud_example.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDTO> getAllUserByOrganization();

    UserDTO insertUpdateUser (RequestUserDTO requestUserDTO);
}
