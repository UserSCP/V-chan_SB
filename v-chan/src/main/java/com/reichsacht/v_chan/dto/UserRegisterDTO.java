package com.reichsacht.v_chan.dto;

import java.time.LocalDate;

import com.reichsacht.v_chan.model.Account_type;
import com.reichsacht.v_chan.model.Role;


public class UserRegisterDTO {
	private Long id;
	private String username;
	private String email;
	private String password;
	private Role role;
	private Account_type account_type;
	private String profile_photo;
	private LocalDate high_Date;
	private LocalDate low_date;
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
	public Account_type getAccount_type() {
		return account_type;
	}
	public void setAccount_type(Account_type account_type) {
		this.account_type = account_type;
	}
	public String getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}
	public LocalDate getHigh_Date() {
		return high_Date;
	}
	public void setHigh_Date(LocalDate high_Date) {
		this.high_Date = high_Date;
	}
	public LocalDate getLow_date() {
		return low_date;
	}
	public void setLow_date(LocalDate low_date) {
		this.low_date = low_date;
	}
	public UserRegisterDTO(Long id, String username, String email, String password, Role role,
			Account_type account_type, String profile_photo, LocalDate high_Date, LocalDate low_date) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.account_type = account_type;
		this.profile_photo = profile_photo;
		this.high_Date = high_Date;
		this.low_date = low_date;
	}
	public UserRegisterDTO(String username, String email, String password, Role role, Account_type account_type,
			String profile_photo, LocalDate high_Date, LocalDate low_date) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.account_type = account_type;
		this.profile_photo = profile_photo;
		this.high_Date = high_Date;
		this.low_date = low_date;
	}
	public UserRegisterDTO(String username) {
		super();
		this.username = username;
	}
	public UserRegisterDTO() {
		super();
	}
	
	
	
}
