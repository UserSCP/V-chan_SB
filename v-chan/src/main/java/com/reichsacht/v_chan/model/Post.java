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
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String post;
	@CreationTimestamp
	@Column(name = "high_date", nullable = false)
	private LocalDate highDate;
	@Column(name = "low_date")
	private LocalDate lowDate;


	public Post(Long id, String post,
			LocalDate highDate, LocalDate lowDate) {
		super();
		this.id = id;
		this.post = post;
		this.highDate = highDate;
		this.lowDate = lowDate;
	}
	
	public Post(String post, LocalDate highDate,
			LocalDate lowDate) {
		super();
		this.post = post;
		this.highDate = highDate;
		this.lowDate = lowDate;
	}

	public Post() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
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
