package com.reichsacht.v_chan.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String clave;
	@CreationTimestamp
	@Column(name="high_date", nullable=false)
	private LocalDate highDate;
	@Column(name="low_date")
	private LocalDate lowDate;
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

}
