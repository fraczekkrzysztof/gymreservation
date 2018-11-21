package com.fraczekkrzysztof.gymreservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "class")
public class Class {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cl_id")
	private int id;

	@Column(name="cl_name")
	private String name;
	@Column(name="cl_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@ManyToOne
	@JoinColumn(name="cl_tr_id")
	private Trainer trainer;
	@ManyToOne
	@JoinColumn(name="cl_ac_id")
	private Activity activity;
	
}
