package com.reichsacht.v_chan.dto;

import com.reichsacht.v_chan.model.Role;


public class UserRegisterDTO {
	private Long id;
	private String username;
	private String email;
	private String password;
	private Role role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UserRegisterDTO(Long id, String username, String email, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public UserRegisterDTO(String username) {
		super();
		this.username = username;
	}
	public UserRegisterDTO() {
		super();
	}
	
}