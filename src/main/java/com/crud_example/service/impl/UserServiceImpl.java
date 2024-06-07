package com.crud_example.service.impl;

import com.crud_example.dto.OrganizationResponseDTO;
import com.crud_example.dto.RequestUserDTO;
import com.crud_example.dto.UserDTO;
import com.crud_example.entity.OrganizationEntity;
import com.crud_example.entity.UserEntity;
import com.crud_example.repository.OrganizationRepository;
import com.crud_example.repository.UserRepository;
import com.crud_example.service.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(OrganizationRepository organizationRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUserByOrganization() {
        return List.of();
    }

    @Override
    public UserDTO insertUpdateUser(RequestUserDTO requestUserDTO) {
        Optional<OrganizationEntity> organizationEntity = this.organizationRepository.findById(requestUserDTO.getOrganization());
        System.out.println("organizationEntity = " + organizationEntity.get().getId()+" org_name"+organizationEntity.get().getName());
        UserEntity userEntity= new UserEntity();
        userEntity.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
        userEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
        userEntity.setStatus(Boolean.TRUE);
        userEntity.setDeactivate(Boolean.FALSE);
        userEntity.setFirstName(requestUserDTO.getFirstName());
        userEntity.setLastName(requestUserDTO.getLastName());
        userEntity.setRoleName(requestUserDTO.getRoleName());
        userEntity.setEmail(requestUserDTO.getEmail());
        userEntity.setOrganizationEntity(organizationEntity.get());

//        System.out.println("userEntity = " + new Gson().toJson(userEntity));
        userRepository.save(userEntity);

//        UserDTO userDTO=new UserDTO();
//        userDTO.setFirstName(requestUserDTO.getFirstName());
//        userDTO.setLastName(requestUserDTO.getLastName());
//        userDTO.setRoleName(requestUserDTO.getRoleName());
//        userDTO.setEmail(requestUserDTO.getEmail());

        UserDTO userDTO=modelMapper.map(userEntity, UserDTO.class);
        userDTO.setOrganization(organizationEntity.get().getId());
        return userDTO;
    }
}
