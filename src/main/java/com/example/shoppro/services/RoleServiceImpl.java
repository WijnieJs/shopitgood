package com.example.shoppro.services;


import com.example.shoppro.dtos.RoleDto;
import com.example.shoppro.entity.Role;

import com.example.shoppro.repositories.CustomRoleService;
import com.example.shoppro.repositories.RoleRepository;
import com.example.shoppro.exceptions.ProjectNotFoundException;


import org.springframework.stereotype.Service;

import java.util.*;


@Service
public  class RoleServiceImpl implements CustomRoleService  {

    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
            this.roleRepository = roleRepository;

    }

    public List<RoleDto> fetchRoleList() {

        try {

            List<Role> roles = roleRepository.findAll();
            List<RoleDto> dtos = new ArrayList<>();

            for(Role role: roles) {
                dtos.add(transferToDto(role));
            }
            return dtos;
        } catch (Exception e) {
            throw new ProjectNotFoundException("something went really wrong in database");

        }
    }



    @Override
    public Role getRoleInDbById(Integer roleId) {

        try {
            Role roleIndb = roleRepository.findRoleById(roleId);
            roleIndb.getName();
            return roleIndb;
        } catch (Exception e) {
            throw new ProjectNotFoundException("No role found with " + roleId + " in database");

        }
}


    @Override
    public Role findRoleInDbByName(String roleName) {
        try {
            Role roleInDb =  roleRepository.findRoleByName(roleName.toUpperCase());
                roleInDb.getName();
                return roleInDb;
        }  catch (Exception e) {

            throw new ProjectNotFoundException("No role found with " + roleName + " in database");
        }
    }


    public RoleDto transferToDto(Role role) {
        var dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());

        return dto;
    }

}
