package com.crud_example.repository;

import com.crud_example.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long> {

    List<OrganizationEntity> findByStatusAndDeactivate(Boolean status, Boolean deactivate);

    OrganizationEntity findByIdAndStatusAndDeactivate(Long id,Boolean status, Boolean deactivate);

}
