package com.iot.api.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.api.enums.UserStatus;
import com.iot.api.enums.UserType;
import com.iot.api.model.user.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final String id;

    private final String userName;
    private final String fullName;
    private final Long mobileNo;

    private final String email;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;
    private final UserType userType;
    private  final UserStatus userStatus;

    public UserDetailsImpl(String id, String userName, String fullName, Long mobileNo, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, UserType userType, UserStatus userStatus) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    public static UserDetailsImpl build(AppUser user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getFullName(),
                user.getMobileNo(),
                user.getEmail(),
                user.getPassword(),
                authorities, user.getUserType(), user.getUserStatus());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

	public String getFullName() {
		return fullName;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }


    public UserType getUserType() {
        return userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
}
