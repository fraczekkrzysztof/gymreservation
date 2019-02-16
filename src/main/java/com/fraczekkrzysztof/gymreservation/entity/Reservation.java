package com.fraczekkrzysztof.gymreservation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id")
	private int id;
	@ManyToOne
	@JoinColumn(name = "res_cl_id")
	private Lesson lesson;
	@Column(name = "res_name")
	private String name;
	@Column(name = "res_email")
	private String email;
	@Column(name = "res_confirmed")
	private boolean confirmed = false;
	@Column(name = "res_waiting_lp")
	private int waiting;

	public Reservation() {

	}

	public Reservation(int id, Lesson lesson, String name, String email, boolean confirmed, int waiting) {
		super();
		this.id = id;
		this.lesson = lesson;
		this.name = name;
		this.email = email;
		this.confirmed = confirmed;
		this.waiting = waiting;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
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

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

}
