package com.crud_example.service.impl;

import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.OrganizationResponseDTO;
import com.crud_example.entity.OrganizationEntity;
import com.crud_example.enums.ExceptionEnum;
import com.crud_example.exception.CustomException;
import com.crud_example.mapper.DtoMapper;
import com.crud_example.repository.OrganizationRepository;
import com.crud_example.service.OrganizationService;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final DtoMapper dtoMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, final DtoMapper dtoMapper) {
        this.organizationRepository = organizationRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public OrganizationResponseDTO insertOrganization(OrganizationRequestDTO organizationRequestDTO) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
        organizationEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
        organizationEntity.setStatus(Boolean.TRUE);
        organizationEntity.setDeactivate(Boolean.FALSE);
        organizationEntity.setName(organizationRequestDTO.getName());
        organizationEntity.setEmail(organizationRequestDTO.getEmail());
        organizationEntity.setLocation(organizationRequestDTO.getLocation());
        organizationEntity.setContactNumber(organizationRequestDTO.getContactNumber());

        organizationEntity = organizationRepository.save(organizationEntity);

        return this.mapToOrganizationResponseDTO(organizationEntity);
    }

    @Override
    public List<OrganizationResponseDTO> getOrganizationDetails() {
        List<OrganizationEntity> organizationEntityList=organizationRepository.findByStatusAndDeactivate(true,false);
        return dtoMapper.mapListOfEntityToDTOWithStandardStrategy(organizationEntityList ,new TypeToken<List<OrganizationResponseDTO>>() {
        });
    }

    /**
     *
     * @param id
     * @return
     *
     */
    @Override
    public OrganizationResponseDTO getOrganizationDetailById(Long id) {
        OrganizationEntity organizationEntity=organizationRepository.findByIdAndStatusAndDeactivate(id,true,false);
        OrganizationResponseDTO responseDTO= dtoMapper.convertToDotWithStandardStrategy(organizationEntity,OrganizationResponseDTO.class);
        return responseDTO;
    }

    /**
     *
     * @param organizationRequestDTO
     * @return
     */
    @Override
    public OrganizationResponseDTO updateOrganization(OrganizationRequestDTO organizationRequestDTO) {
        OrganizationEntity organizationEntity;
        // Check if organizationId is provided
        if (organizationRequestDTO.getId() != null) {
            // Fetch existing organization entity
            organizationEntity = organizationRepository.findById(organizationRequestDTO.getId()).orElseThrow(() -> {
                return new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.NOT_FOUND);
            });
            organizationEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            organizationEntity.setStatus(true);
            organizationEntity.setStatus(false);
        } else {
            System.out.println("organizationRequestDTO.getId() = " + organizationRequestDTO.getId());
                        // audit setter
                        organizationEntity   = new OrganizationEntity();
                        organizationEntity.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
                        organizationEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
                        // active / de-active setter
            organizationEntity.setStatus(Boolean.TRUE);
            organizationEntity.setDeactivate(Boolean.FALSE);
        }

        // Map the request DTO to the entity (this will update existing fields and preserve the created date for existing entities)
        dtoMapper.updateToEntityWithFullTypeMatchingRequired(organizationRequestDTO, organizationEntity);
        // Save the entity
        organizationEntity = organizationRepository.save(organizationEntity);
        // Map the saved entity to a response DTO
        OrganizationResponseDTO organizationResponseDTO = this.mapToOrganizationResponseDTO(organizationEntity);

        return organizationResponseDTO;
    }

    @Override
    public OrganizationResponseDTO updateOrganizationStatus(Long organizationId, Boolean activeStatus) {
        OrganizationEntity organizationEntity = organizationRepository.findById(organizationId).orElseThrow(() -> {
            return new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.NOT_FOUND);
        });
        organizationEntity.setStatus(activeStatus);

        organizationEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));

        organizationRepository.save(organizationEntity);

        OrganizationResponseDTO organizationResponseDTO = this
                .mapToOrganizationResponseDTO(organizationEntity);

        return organizationResponseDTO;
    }

    private OrganizationResponseDTO mapToOrganizationResponseDTO(OrganizationEntity organizationEntity) {
        return dtoMapper.convertToDotWithStandardStrategy(organizationEntity, OrganizationResponseDTO.class);
    }
}
