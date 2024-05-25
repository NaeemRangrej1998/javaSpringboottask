package com.crud_example.service;

import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.OrganizationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <h1>OrganizationService</h1>
 * <p>
 * This Service will be used to manage all Organization related apis
 * </p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 18-05-2020
 */
@Service
public interface OrganizationService {

    OrganizationResponseDTO insertOrganization(OrganizationRequestDTO organizationRequestDTO);
    /**
     * <p>
     * getAllOrganization Details
     * </p>
     * @return OrganizationResponseDTO
     *
     */
    List<OrganizationResponseDTO> getOrganizationDetails();
    /**
     * <p>
     * getOrganizationDetailById  Details By Id
     * </p>
     *
     * @param orgId (Long id)
     * @return OrganizationResponseDTO
     *
     */
    OrganizationResponseDTO getOrganizationDetailById(Long id);
    /**
     * <p>
     * updateOrganization Details
     * </p>
     *
     * @param OrganizationRequestDTO contain the organization details
     * @return OrganizationResponseDTO
     *
     */
    OrganizationResponseDTO insertUpdateOrganization(OrganizationRequestDTO organizationRequestDTO);
    /**
     * <p>
     * updateOrganization Details By Active Status
     * </p>
     * @param activeStatus
     *
     * @param organizationId
     * @return OrganizationResponseDTO
     *
     */
    OrganizationResponseDTO updateOrganizationStatus(Long organizationId, Boolean activeStatus);

}
