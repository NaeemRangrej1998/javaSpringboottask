package com.crud_example.service.impl;

import com.crud_example.dto.RequestUserDTO;
import com.crud_example.dto.UserDTO;
import com.crud_example.entity.OrganizationEntity;
import com.crud_example.entity.UserEntity;
import com.crud_example.enums.ExceptionEnum;
import com.crud_example.exception.CustomException;
import com.crud_example.repository.OrganizationRepository;
import com.crud_example.repository.UserRepository;
import com.crud_example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<UserDTO> getAllUser(Pageable pageable, String searchValue) {
        Page<UserEntity> userEntityList = userRepository.findByDeactivateAndFirstNameLike(false, "%" + searchValue + "%", pageable);
        List<UserDTO> UserDTOList = userEntityList.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(UserDTOList, pageable, userEntityList.getTotalElements());

//        return userEntityList
//                .stream()
//                .map(element -> modelMapper.map(element, UserDTO.class))
//                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomException(ExceptionEnum.USER_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        userDTO.setOrganization(userEntity.getOrganizationEntity().getId());
        return userDTO;
    }

    @Override
    public UserDTO insertUpdateUser(RequestUserDTO requestUserDTO) {
        System.out.println("requestUserDTO = " + requestUserDTO.toString());
        OrganizationEntity organizationEntity = this.organizationRepository.findById(requestUserDTO.getOrganization()).orElseThrow(() -> new CustomException(ExceptionEnum.ORGANIZATION_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
        UserEntity userEntity;
        // Check if organizationId is provided
        if (requestUserDTO.getId() != null) {
            // Fetch existing organization entity
            userEntity = userRepository.findById(requestUserDTO.getId()).orElseThrow(() -> new CustomException(ExceptionEnum.USER_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
            userEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            userEntity.setStatus(true);
            userEntity.setDeactivate(false);
            userEntity.setOrganizationEntity(organizationEntity);
        } else {
            System.out.println("requestUserDTO.getId() = " + requestUserDTO.getId());
            // audit setter
            userEntity = new UserEntity();
            userEntity.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
            userEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            // active / de-active setter
            userEntity.setStatus(Boolean.TRUE);
            userEntity.setDeactivate(Boolean.FALSE);
            userEntity.setOrganizationEntity(organizationEntity);
        }

        // Map the request DTO to the entity (this will update existing fields and preserve the created date for existing entities)
        modelMapper.map(requestUserDTO, userEntity);
//        dtoMapper.updateToEntityWithFullTypeMatchingRequired(organizationRequestDTO, organizationEntity);
        // Save the entity
//        System.out.println("organizationEntity before = " + organizationEntity.getName());

        userEntity = userRepository.save(userEntity);
//        System.out.println("organizationEntity = " + organizationEntity.getName());
        // Map the saved entity to a response DTO
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        userDTO.setOrganization(organizationEntity.getId());
        return userDTO;//        System.out.println("organizationEntity = " + organizationEntity.get().getId()+" org_name"+organizationEntity.get().getName());
//        UserEntity userEntity= new UserEntity();
//        userEntity.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
//        userEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
//        userEntity.setStatus(Boolean.TRUE);
//        userEntity.setDeactivate(Boolean.FALSE);
//        userEntity.setFirstName(requestUserDTO.getFirstName());
//        userEntity.setLastName(requestUserDTO.getLastName());
//        userEntity.setRoleName(requestUserDTO.getRoleName());
//        userEntity.setEmail(requestUserDTO.getEmail());
//        userEntity.setOrganizationEntity(organizationEntity.get());

//        System.out.println("userEntity = " + new Gson().toJson(userEntity));
//        userRepository.save(userEntity);

//        UserDTO userDTO=new UserDTO();
//        userDTO.setFirstName(requestUserDTO.getFirstName());
//        userDTO.setLastName(requestUserDTO.getLastName());
//        userDTO.setRoleName(requestUserDTO.getRoleName());
//        userDTO.setEmail(requestUserDTO.getEmail());

//        UserDTO userDTO=modelMapper.map(userEntity, UserDTO.class);
//        userDTO.setOrganization(organizationEntity.get().getId());
//        return userDTO;
    }

    @Override
    public UserDTO updateUserStatus(Long userId, Boolean activeStatus) {
        UserEntity userEntity = getUserEntityByUserId(userId);
        userEntity.setStatus(activeStatus);

        userEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));

        userRepository.save(userEntity);
        UserDTO UserDTO = modelMapper.map(userEntity, UserDTO.class);
        UserDTO.setOrganization(userEntity.getOrganizationEntity().getId());
        return UserDTO;
    }

    public UserEntity getUserEntityByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionEnum.USER_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
    }
}
