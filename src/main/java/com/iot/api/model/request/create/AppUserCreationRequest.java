package com.iot.api.model.request.create;

import com.iot.api.enums.AppRole;
import com.iot.api.enums.UserStatus;
import com.iot.api.enums.UserType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppUserCreationRequest {
    private String userName;
    private String fullName;
    private Long mobileNo;
    private String email;
    private String password;
    private List<AppRole> appRoles = new ArrayList<>();
    private UserType userType = UserType.CONSUMER;
    private UserStatus userStatus = UserStatus.PENDING;
}
