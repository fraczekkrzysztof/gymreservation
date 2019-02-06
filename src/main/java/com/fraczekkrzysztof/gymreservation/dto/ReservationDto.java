package com.fraczekkrzysztof.gymreservation.dto;

public class ReservationDto {

	private int id;
	private int lesson;
	private String name;
	private String email;
	
	public ReservationDto() {
		
	}

	public ReservationDto(int id, int lessson, String name, String email) {
		super();
		this.id = id;
		this.lesson = lessson;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLesson() {
		return lesson;
	}

	public void setLesson(int lessson) {
		this.lesson = lessson;
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

	
	
}
