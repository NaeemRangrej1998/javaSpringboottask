package com.crud_example.repository;

import com.crud_example.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <h1>OrganizationRepository</h1>
 * <p>
 * This will be used to perform database operation on entity OrganizationEntity
 * </p>
 *
 */
@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long> {
    /**
     * <p>
     * find based on  status deactivate
     * </p>
     *
     * @param deactivate contain the deactivate status TRUE/FALSE
     * @return List<OrganizationEntity> &lt;OrganizationEntity&gt;
     *
     */
    List<OrganizationEntity> findByStatusAndDeactivate(Boolean status, Boolean deactivate);


    /**
     * <p>
     * find based on  orgId and status deactivate
     * </p>
     *
     * @param deactivate contain the deactivate status TRUE/FALSE
     * @param orgId
     * @return OrganizationEntity &lt;OrganizationEntity&gt;
     *
     */
    Optional<OrganizationEntity> findByIdAndStatusAndDeactivate(Long id,Boolean status, Boolean deactivate);

}
