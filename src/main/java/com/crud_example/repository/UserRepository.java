package com.crud_example.repository;

import com.crud_example.entity.OrganizationEntity;
import com.crud_example.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Page<UserEntity> findByDeactivateAndFirstNameLike(Boolean deactivate, String name, Pageable pageable);
}
