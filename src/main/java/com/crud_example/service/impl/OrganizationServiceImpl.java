package com.crud_example.service.impl;

import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.OrganizationResponseDTO;
import com.crud_example.entity.OrganizationEntity;
import com.crud_example.enums.ExceptionEnum;
import com.crud_example.exception.CustomException;
import com.crud_example.mapper.DtoMapper;
import com.crud_example.repository.OrganizationRepository;
import com.crud_example.service.OrganizationService;
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

/**
 * <h1>OrganizationServiceImpl</h1>
 *
 * <p>
 * This ServiceImpl will be used to manage all Organization related apis
 * </p>
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;

    private ModelMapper modelMapper;
    private final DtoMapper dtoMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, final DtoMapper dtoMapper,final ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.dtoMapper = dtoMapper;
        this.modelMapper=modelMapper;
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
        return modelMapper.map(organizationEntity,OrganizationResponseDTO.class);

//        return this.mapToOrganizationResponseDTO(organizationEntity);
    }

    /**
     * <p>
     * Fetch Organization Details  it's active or deactivate. Implemented
     * </p>
     * @return OrganizationResponseDTO
     */
    @Override
    public Page<OrganizationResponseDTO> getOrganizationDetails(Pageable pageable, String searchValue) {
        System.out.println("organizationEntityList = " );
        Page<OrganizationEntity> organizationEntityList = organizationRepository.findByDeactivateAndNameLike(
        false, "%" + searchValue + "%", pageable);
        System.out.println("organizationEntityList = " + organizationEntityList.stream().toList());
        List<OrganizationResponseDTO> organizationResponseDTOList =organizationEntityList.stream().map((org)->modelMapper.map(org   ,OrganizationResponseDTO.class)).collect(Collectors.toList());
//        List<OrganizationResponseDTO> organizationResponseDTOList = this
//                .mapToListOfOrganizationResponseDTO(organizationEntityList.getContent());

        return new PageImpl<>(organizationResponseDTOList, pageable, organizationEntityList.getTotalElements());

    }

    /**
     * <p>
     * Get Organization Details based on Id
     * </p>
     *
     * @param organizationId contain the organization id
     * @return OrganizationResponseDTO
     */
    @Override
    public OrganizationResponseDTO getOrganizationDetailById(Long id) {
        OrganizationEntity organizationEntity = organizationRepository
                .findByIdAndStatusAndDeactivate(id, true, false)
                .orElseThrow(() -> new CustomException(ExceptionEnum.ORGANIZATION_ENTITY_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
        return modelMapper.map(organizationEntity,OrganizationResponseDTO.class);
//        OrganizationResponseDTO responseDTO= dtoMapper.convertToDotWithStandardStrategy(organizationEntity,OrganizationResponseDTO.class);
//        return responseDTO;
    }

    /**
     * <p>
     * Insert/Update Organization Status based on organizationId
     * </p>
     *
     * @param organizationId contain the organization id
     * @return OrganizationResponseDTO
     */
    @Override
    public OrganizationResponseDTO insertUpdateOrganization(OrganizationRequestDTO organizationRequestDTO) {
        OrganizationEntity organizationEntity;
        // Check if organizationId is provided
        if (organizationRequestDTO.getId() != null) {
            // Fetch existing organization entity
            organizationEntity = organizationRepository.findById(organizationRequestDTO.getId()).orElseThrow(() -> new CustomException(ExceptionEnum.ORGANIZATION_ENTITY_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));
            organizationEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            organizationEntity.setStatus(true);
            organizationEntity.setDeactivate(false);
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
                modelMapper.map(organizationRequestDTO, organizationEntity);
//        dtoMapper.updateToEntityWithFullTypeMatchingRequired(organizationRequestDTO, organizationEntity);
        // Save the entity
        System.out.println("organizationEntity before = " + organizationEntity.getName());

        organizationEntity = organizationRepository.save(organizationEntity);
        System.out.println("organizationEntity = " + organizationEntity.getName());
        // Map the saved entity to a response DTO
        return modelMapper.map(organizationEntity, OrganizationResponseDTO.class);
//        OrganizationResponseDTO organizationResponseDTO = this.mapToOrganizationResponseDTO(organizationEntity);
//        return organizationResponseDTO;
    }

    /**
     * <p>
     * Update Organization Status based on Active/DeActive
     * </p>
     *
     * @param organizationId contain the organization id
     * @param activeStatus   contain the active/deactivate status
     * @return OrganizationResponseDTO
     */
    @Override
    public OrganizationResponseDTO updateOrganizationStatus(Long organizationId, Boolean activeStatus) {
        OrganizationEntity organizationEntity = organizationRepository.findById(organizationId).orElseThrow(() -> {
            return new CustomException(ExceptionEnum.ORGANIZATION_ENTITY_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND);
        });
        organizationEntity.setStatus(activeStatus);

        organizationEntity.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));

        organizationRepository.save(organizationEntity);

//        OrganizationResponseDTO organizationResponseDTO = this
//                .mapToOrganizationResponseDTO(organizationEntity);
        return modelMapper.map(organizationEntity,OrganizationResponseDTO.class);

//        return organizationResponseDTO;
    }

    /**
     * <p>
     *     This methos id used for convert Entity To DTO
     * </p>
     * @param organizationEntity
     * @return OrganizationResponseDTO
     */
//    private OrganizationResponseDTO mapToOrganizationResponseDTO(OrganizationEntity organizationEntity) {
//        return dtoMapper.convertToDotWithStandardStrategy(organizationEntity, OrganizationResponseDTO.class);
//    }
//
//    private List<OrganizationResponseDTO> mapToListOfOrganizationResponseDTO(
//            List<OrganizationEntity> organizationEntityList) {
//        return dtoMapper.mapListOfEntityToDTOWithStandardStrategy(organizationEntityList,
//                TypeToken.of(new TypeToken<List<OrganizationResponseDTO>>() {
//                }.getType()));
//    }
}
