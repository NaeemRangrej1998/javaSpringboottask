package com.crud_example.controller;

import com.crud_example.dto.ApiResponse;
import com.crud_example.dto.OrganizationRequestDTO;
import com.crud_example.dto.OrganizationResponseDTO;
import com.crud_example.enums.ExceptionEnum;
import com.crud_example.exception.CustomException;
import com.crud_example.service.OrganizationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud_example.enums.ApiResponsesEnum;

import java.util.List;
/**
 * <h1>OrganizationController</h1>
 * <p>
 * Used for manage organization related API like InsertOrganization, UpdateOrganization, GetOrganization,
 * getOrganizationById,changeOrganizationStatus, etc
 * </p>
 */
@RequestMapping("/oranization")
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;


    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    /**
     * <p>
     * This api is used  for save Organization Details
     *  </p>
     *
     * @param OrganizationResponseDTO contain the organization details
     * @return ResponseEntity &lt;ApiResponse&gt;
     */
    @PostMapping(value = "/addOranization", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> insertOrganization(@RequestBody
            OrganizationRequestDTO organizationRequestDTO) {
        try {
            organizationRequestDTO.setId(null);
            OrganizationResponseDTO responseDTO = organizationService.insertUpdateOrganization(organizationRequestDTO);

            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ORGANIZATION_INSERTED_SUCCESSFULLY.getValue(),responseDTO),
                    HttpStatus.OK);
        }  catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This api is used  for get All  Organization Details
     *  </p>
     * @Param OrganizationRequestDTO it's contain organization details
     * @return ResponseEntity &lt;ApiResponse&gt;
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getOrganization( @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                        @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                                        @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs) {
        try {
            Pageable pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortAs,"name"));
            Page<OrganizationResponseDTO> organizationEntities = organizationService.getOrganizationDetails(pageable, searchValue.trim());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.GET_ORGANIZATION_LIST.getValue(), organizationEntities),HttpStatus.OK);

        }   catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This api is used  for save Organization Details
     *  </p>
     * @param orgId contain the organization Id
     * @return ResponseEntity &lt;ApiResponse&gt;
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

    /**
     * <p>
     * This api is used  for update Organization Details By orgId
     *  </p>
     *
     * @param OrganizationRequestDTO contain the organization details
     * @return ResponseEntity &lt;ApiResponse&gt;
     */
    @PutMapping(value = "/updateOrgnanization/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateOrganization(@PathVariable Long organizationId,@RequestBody OrganizationRequestDTO organizationRequestDTO ) {

        try {
            organizationRequestDTO.setId(organizationId);
            OrganizationResponseDTO responseDTO = organizationService.insertUpdateOrganization(organizationRequestDTO);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ORGANIZATION_UPDATED_SUCCESSFULLY.getValue(), responseDTO),
                    HttpStatus.OK);

        } catch (CustomException e) {
            throw e;
        }catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /**
     * <p>
     * This api is used  for update Organization status Details By orgId
     *  </p>
     *
     * @param {activeStatus} and orgId
     * @return ResponseEntity &lt;ApiResponse&gt;
     */
    @PutMapping(value = "/status/{organizationId}/{activeStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateOrganizationStatus(
            @PathVariable(value = "organizationId") Long organizationId,
            @PathVariable(value = "activeStatus") Boolean activeStatus) {
        try {
            OrganizationResponseDTO responseDTO = organizationService.updateOrganizationStatus(organizationId, activeStatus);
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ORGANIZATION_STATUS_UPDATED_SUCCESSFULLY.getValue(), responseDTO),
                    HttpStatus.OK);        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
