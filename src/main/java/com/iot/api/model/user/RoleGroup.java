package com.iot.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.api.enums.AppRole;
import com.iot.api.enums.UserStatus;
import com.iot.api.enums.UserType;
import com.iot.api.model.Author;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
@RequiredArgsConstructor
public class RoleGroup {
    @Id
    private String id;
    private String groupName;
    private List<AppRole> roles=new ArrayList<>();
    private UserType userType=UserType.SUPER_ADMIN;
    private Boolean isActive=true;

    @JsonIgnore
    @DBRef
    private AppUser createdByAppUser;
}
