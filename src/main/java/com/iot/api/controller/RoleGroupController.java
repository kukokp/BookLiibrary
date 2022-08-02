package com.iot.api.controller;

import com.iot.api.model.Book;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.CreateRoleGroup;
import com.iot.api.model.user.RoleGroup;
import com.iot.api.service.RoleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class RoleGroupController {
    private final RoleGroupService roleGroupService;

    @PostMapping("/create-role-group")
    @PreAuthorize("hasRole('ROLE_ROLEGROUP_ADD')")
    public SuccessResponse<RoleGroup> createRoleGroup(@RequestBody CreateRoleGroup createRoleGroup) {
        return roleGroupService.createRoleGroup(createRoleGroup);
    }

    @GetMapping("/get-role-group")
    @PreAuthorize("hasRole('ROLE_ROLEGROUP_READ')")
    public ResponseEntity<SuccessResponse<RoleGroup>> getAllRoleGroup() {
        return ResponseEntity.ok(roleGroupService.getAllRoleGroup());
    }

    @GetMapping("/get-role-group/{role_group_id}")
    @PreAuthorize("hasRole('ROLE_ROLEGROUP_READ')")
    public ResponseEntity<SuccessResponse<RoleGroup>> getRoleGroupById(@PathVariable String role_group_id) {
        return ResponseEntity.ok(roleGroupService.getRoleGroupById(role_group_id));
    }

}
