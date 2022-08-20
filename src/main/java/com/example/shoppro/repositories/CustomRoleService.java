package com.example.shoppro.repositories;


import com.example.shoppro.dtos.RoleDto;
import com.example.shoppro.entity.Role;
import com.example.shoppro.entity.User;

import java.util.List;

public interface CustomRoleService {

    List<RoleDto> fetchRoleList();
    Role getRoleInDbById(Integer roleId);
    Role findRoleInDbByName(String roleName);


}
