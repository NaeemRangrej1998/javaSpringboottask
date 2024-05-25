package com.crud_example.service;

import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.OrganizationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {
    OrganizationResponseDTO insertOrganization(OrganizationRequestDTO organizationRequestDTO);
    List<OrganizationResponseDTO> getOrganizationDetails();
    OrganizationResponseDTO getOrganizationDetailById(Long id);
    OrganizationResponseDTO updateOrganization(OrganizationRequestDTO organizationRequestDTO);
    OrganizationResponseDTO updateOrganizationStatus(Long organizationId, Boolean activeStatus);

}
