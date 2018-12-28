package com.fraczekkrzysztof.gymreservation.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fraczekkrzysztof.gymreservation.entity.Activity;
import com.fraczekkrzysztof.gymreservation.entity.Trainer;

public class LessonDto {

	private int id;
	private String name;
	private Date date;
	private int max;
	private int available;
	private int trainer;
	private int activity;
	
	public LessonDto() {
		
	}

	public LessonDto(int id, String name, Date date, int max, int available, int trainer, int activity) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.max = max;
		this.available = available;
		this.trainer = trainer;
		this.activity = activity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getTrainer() {
		return trainer;
	}

	public void setTrainer(int trainer) {
		this.trainer = trainer;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}
	
	
}
