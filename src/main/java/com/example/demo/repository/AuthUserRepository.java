package com.example.demo.repository;

import com.example.demo.entity.AuthUser;
import com.example.demo.entity.Role;
import com.example.demo.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, UUID>, JpaSpecificationExecutor<AuthUser> {
    AuthUser findByEmailAndBlockedFalse(String email);
    AuthUser findAuthUserByIdAndBlockedFalse(UUID id);

    boolean existsAuthUserByEmail(String email);
    boolean existsAuthUserByPhone(String phone);

    @Modifying
    @Transactional
    void updateAuthUserOnlineFalseById(UUID id);

    @Modifying
    @Transactional
    void updateAuthUserOnlineTrueById(UUID id);

    @Transactional
    @Modifying
    @Query(value = "update AuthUser set blocked=?1 where id=?2")
    void updateAuthUserBlockedById(boolean blocked,UUID id);

    @Transactional
    @Modifying
    @Query(value = "update AuthUser set role=?1 where id=?2")
    void updateRole(Role role,UUID id);

    @Modifying
    @Transactional
    @Query(value = "update AuthUser set phone=:phone,firstName=:firstName,gender=:gender,lastName=:lastName,imagePath=:imagePath,birthdate=:birthdate where id=:id")
    void updateAuthUser(String phone, String firstName, String lastName, String imagePath, Gender gender, LocalDate birthdate,UUID id);

    @Query(value = "select count(au.id) from AuthUser au")
    int findAllSize();

    @Query(value = "select au.active from AuthUser au where au.id=?1")
    boolean isActive(UUID userId);
}