package com.example.shoppro.controllers;


import com.example.shoppro.dtos.RoleDto;
import com.example.shoppro.entity.Role;
import com.example.shoppro.repositories.CustomRoleService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.shoppro.services.MapValidationErrorService;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    private final CustomRoleService customRoleService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public RoleController(CustomRoleService customRoleService, MapValidationErrorService mapValidationErrorService) {
        this.customRoleService = customRoleService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    //
    @GetMapping(value = "allroles")
    public ResponseEntity<List<RoleDto>> fetchRoleList()  {
            List<RoleDto> dtos;

            dtos = customRoleService.fetchRoleList();
        return ResponseEntity.ok().body(dtos);

    }

        @GetMapping(value = "/rolebyid/{roleId}")
    public ResponseEntity<?> getThisRoleById(@PathVariable("roleId") Integer roleId) {


         Role role = customRoleService.getRoleInDbById(roleId);

         return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

//    @GetMapping(value = "/rolebyid/{roleId}")
//    public ResponseEntity<?> getRoleById(@PathVariable("roleId") Integer roleId) {
//
//
//         RoleDto role = customRoleService.getRoleById(roleId);
//sdfsdfsdfsf
//         return new ResponseEntity<RoleDto>(role, HttpStatus.OK);
//    }
    @GetMapping(value = "/rolebyname/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("roleName") String roleName) {


        Role role = customRoleService.findRoleInDbByName(roleName);

        return ResponseEntity.ok().body(role);
    }
}
