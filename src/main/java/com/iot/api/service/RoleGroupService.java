package com.iot.api.service;

import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.CreateRoleGroup;
import com.iot.api.model.user.AppUser;
import com.iot.api.model.user.RoleGroup;
import com.iot.api.repository.AppUserRepository;
import com.iot.api.repository.RoleGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleGroupService {

    private final AppUserRepository appUserRepository;
    private final RoleGroupRepository roleGroupRepository;

    public SuccessResponse createRoleGroup(CreateRoleGroup createRoleGroup) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());

        if (!roleGroupRepository.existsByGroupName(createRoleGroup.getGroupName())) {
            Optional<AppUser> appUser = appUserRepository.findById(createRoleGroup.getCreatedByUserId());
            if (appUser.isPresent()) {
                RoleGroup roleGroupToCreate = new RoleGroup();
                BeanUtils.copyProperties(createRoleGroup, roleGroupToCreate);
                roleGroupToCreate.setCreatedByAppUser(appUser.get());
                successResponse.setData(Collections.singletonList(roleGroupRepository.save(roleGroupToCreate)));
            } else {
                successResponse.setStatus(HttpStatus.NO_CONTENT.value());
                successResponse.setMessage("User Not Found");
            }
        } else {
            successResponse.setStatus(HttpStatus.NO_CONTENT.value());
            successResponse.setMessage("Group Name Already Exits :".concat(createRoleGroup.getGroupName()));
        }
        return successResponse;
    }

    public SuccessResponse getAllRoleGroup() {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(roleGroupRepository.findAll());
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        return successResponse;
    }
    public SuccessResponse getRoleGroupById(String role_group_id) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(Collections.singletonList(roleGroupRepository.findById(role_group_id).get()));
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        return successResponse;
    }

}
