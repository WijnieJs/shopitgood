package com.example.shoppro.repositories;

import com.example.shoppro.dtos.RoleDto;
import com.example.shoppro.entity.Role;
import com.example.shoppro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
 public interface RoleRepository extends JpaRepository<Role, Integer> {
// long countByLastname(String lastname);
 Role findRoleByName(String lastname);
 Role findRoleById(Integer userId); //


}
