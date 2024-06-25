package com.crud_example.service;

import com.crud_example.dto.RequestUserDTO;
import com.crud_example.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Page<UserDTO> getAllUser(Pageable pageable, String searchValue);
    UserDTO getUserById(Long userId);

    UserDTO insertUpdateUser (RequestUserDTO requestUserDTO);
    UserDTO updateUserStatus(Long userId,Boolean activeStatus);
}
