package com.savushkin.demo.domain;

import javax.validation.constraints.NotBlank;

public class Course {

	private Long id;

	@NotBlank(message = "Укажите имя автора")
	private String author;

	@NotBlank(message = "Укажите название курса")
	private String title;

	public Course() {
	}

	public Course(Long id, String author, String title) {
		this.id = id;
		this.author = author;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
