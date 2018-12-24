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
	@JoinColumn(name="res_cl_id")
	private Lesson clas;
	@Column(name="res_name")
	private String name;
	@Column(name="res_email")
	private String email;


}
