package com.fraczekkrzysztof.gymreservation.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "class")
// @JsonIdentityInfo(
// generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cl_id")
	private int id;

	@Column(name = "cl_name")
	private String name;
	@Column(name = "cl_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")//, timezone = "Europe/Warsaw")
	private LocalDateTime date;
	@Column(name = "cl_max")
	private int max;
	@Column(name = "cl_available")
	private int available;
	@ManyToOne
	@JoinColumn(name = "cl_tr_id")
	private Trainer trainer;
	@ManyToOne
	@JoinColumn(name = "cl_ac_id")
	private Activity activity;

	public Lesson() {

	}

	public Lesson(int id, String name, LocalDateTime date, int max, int available, Trainer trainer, Activity activity) {
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void changeAvailable() {
		if (this.available > 0) {
			this.available--;
		}
	}

	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + ", date=" + date + ", max=" + max + ", available=" + available
				+ ", trainer=" + trainer + ", activity=" + activity + "]";
	}

}
