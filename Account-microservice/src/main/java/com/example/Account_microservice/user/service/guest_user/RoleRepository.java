package com.example.Account_microservice.user.service.guest_user;

import com.example.Account_microservice.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT role_id FROM role WHERE role_name:roleName", nativeQuery = true)
    Long findIdByRoleName(@Param("roleName") String roleName);
}
