package com.reichsacht.v_chan.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	private Long id;
	private String name;
	private String email;
	private String clave;
	@Column(name="high_date", nullable=false)
	private LocalDateTime highDate;
	@Column(name="low_date")
	private LocalDateTime lowDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public LocalDateTime getHighDate() {
		return highDate;
	}
	public void setHighDate(LocalDateTime highDate) {
		this.highDate = highDate;
	}
	public LocalDateTime getLowDate() {
		return lowDate;
	}
	public void setLowDate(LocalDateTime lowDate) {
		this.lowDate = lowDate;
	}

}
