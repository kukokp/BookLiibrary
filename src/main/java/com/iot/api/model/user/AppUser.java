package com.iot.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.api.enums.AppRole;
import com.iot.api.enums.UserStatus;
import com.iot.api.enums.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
@RequiredArgsConstructor
public class AppUser {
    @Id
    private String id;
    private String userName;
    private String fullName;
    private Long mobileNo;
    private String email;
    @JsonIgnore
    private String password;
    private List<AppRole> appRoles=new ArrayList<>();
    private UserType userType;
    private UserStatus userStatus;
}
