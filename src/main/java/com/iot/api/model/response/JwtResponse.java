package com.iot.api.model.response;

import com.iot.api.enums.UserStatus;
import com.iot.api.enums.UserType;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String userName;
	private String fullName;
	private Long mobileNo;
	private String email;
	private List<String> roles;
	private UserType userType;
	private UserStatus userStatus;

	public JwtResponse(String accessToken, String id, String userName, String fullName, Long mobileNo, String email, List<String> roles, UserType userType, UserStatus userStatus) {
		this.token = accessToken;
		this.id = id;
		this.userName = userName;
		this.fullName=fullName;
		this.mobileNo=mobileNo;
		this.email = email;
		this.roles = roles;
		this.userType = userType;
		this.userStatus = userStatus;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
