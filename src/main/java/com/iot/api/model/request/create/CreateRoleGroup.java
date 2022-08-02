package com.iot.api.model.request.create;

import com.iot.api.enums.AppRole;
import com.iot.api.enums.UserType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateRoleGroup {
    private String groupName;
    private List<AppRole> roles=new ArrayList<>();
    private UserType userType=UserType.SUPER_ADMIN;
    private Boolean isActive=true;
    private String createdByUserId;
}
