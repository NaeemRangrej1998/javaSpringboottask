package com.crud_example.controller;

import com.crud_example.dto.ApiResponse;
import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.OrganizationResponseDTO;
import com.crud_example.enums.ExceptionEnum;
import com.crud_example.exception.CustomException;
import com.crud_example.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud_example.enums.ApiResponsesEnum;

import java.util.List;

@RequestMapping("/oranization")
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;


    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     *
     * @param organizationRequestDTO
     * @return
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> insertOrganization(@RequestBody
            OrganizationRequestDTO organizationRequestDTO) {
        try {
            organizationRequestDTO.setId(null);
            OrganizationResponseDTO responseDTO = organizationService.updateOrganization(organizationRequestDTO);

            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ORGANIZATION_INSERTED_SUCCESSFULLY.getValue(),responseDTO),
                    HttpStatus.OK);
        }  catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllOrganization", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getOrganization() {
        try {
            List<OrganizationResponseDTO> organizationEntities = organizationService.getOrganizationDetails();
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.GET_ORGANIZATION_LIST.getValue(), organizationEntities),HttpStatus.OK);

        }   catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param orgId
     * @return
     */
    @GetMapping(value = "/{orgId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getOrganizationById(@PathVariable("orgId") Long orgId) {
        try {
            OrganizationResponseDTO organizationEntities = organizationService.getOrganizationDetailById(orgId);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.GET_ORGANIZATION_LIST.getValue(), organizationEntities),HttpStatus.OK);

        }   catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateOrganization(@PathVariable Long organizationId,@RequestBody OrganizationRequestDTO organizationRequestDTO ) {

        try {
            organizationRequestDTO.setId(organizationId);
            OrganizationResponseDTO responseDTO = organizationService.updateOrganization(organizationRequestDTO);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ORGANIZATION_UPDATED_SUCCESSFULLY.getValue(), responseDTO),
                    HttpStatus.OK);

        } catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/status/{organizationId}/{activeStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateOrganizationStatus(
            @PathVariable(value = "organizationId") Long organizationId,
            @PathVariable(value = "activeStatus") Boolean activeStatus) {
        try {
            OrganizationResponseDTO responseDTO = organizationService.updateOrganizationStatus(organizationId, activeStatus);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ORGANIZATION_UPDATED_SUCCESSFULLY.getValue(), responseDTO),
                    HttpStatus.OK);        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
