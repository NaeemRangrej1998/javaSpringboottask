package com.crud_example.repository;

import com.crud_example.entity.OrganizationEntity;
import com.crud_example.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

//    @Query(value = "select * from user where email = ?1", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    UserEntity findByFirstName(String name);

    Page<UserEntity> findByDeactivateAndFirstNameLike(Boolean deactivate, String name, Pageable pageable);
}
