package com.reichsacht.v_chan.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@CreationTimestamp
	@Column(name = "high_date", nullable = false)
	private LocalDate highDate;
	@Column(name = "low_date")
	private LocalDate lowDate;

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

	public LocalDate getHighDate() {
		return highDate;
	}

	public void setHighDate(LocalDate highDate) {
		this.highDate = highDate;
	}

	public LocalDate getLowDate() {
		return lowDate;
	}

	public void setLowDate(LocalDate lowDate) {
		this.lowDate = lowDate;
	}

	public User(Long id, String username, String email, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;

	}


	public User(String username, String email, String password, Role role, LocalDate highDate, LocalDate lowDate) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.highDate = highDate;
		this.lowDate = lowDate;
	}

	public User() {
		super();
	}

}
