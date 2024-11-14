package com.reichsacht.v_chan.dto;

import java.time.LocalDate;

public class PostUploadDTO {
	private Long id;
	private String post;
	private LocalDate high_Date;
	private LocalDate low_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPost() {
		return post;
	}
	public void setUsername(String post) {
		this.post = post;
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
	public PostUploadDTO(Long id, String post, LocalDate high_Date,
			LocalDate low_date) {
		super();
		this.id = id;
		this.post = post;
		this.high_Date = high_Date;
		this.low_date = null;
	}
	public PostUploadDTO(String post) {
		super();
		this.post = post;
	}
	public PostUploadDTO() {
		super();
	}
	public PostUploadDTO(String post,LocalDate high_Date,
			LocalDate low_date) {
		super();
		this.post = post;
		this.high_Date = high_Date;
		this.low_date = low_date;
	}
	
}
