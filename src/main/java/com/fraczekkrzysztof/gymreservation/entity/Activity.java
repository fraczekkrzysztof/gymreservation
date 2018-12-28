package com.fraczekkrzysztof.gymreservation.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "activity")
@JsonIgnoreProperties(value= "lessons")
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ac_id")
	private int id;
	@Column(name="ac_symbol")
	private String symbol;
	@Column(name="ac_name")
	private String name;
	@OneToMany(mappedBy="activity", cascade= {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Lesson> lessons;

	public Activity() {
		
	}


	public Activity(int id, String symbol, String name, Set<Lesson> classes) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.name = name;
		//this.classes = classes;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Lesson> getLessons() {
		return lessons;
	}


	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}


	@Override
	public String toString() {
		return "Activity [id=" + id + ", symbol=" + symbol + ", name=" + name + ", lessons=" + lessons + "]";
	}


	
	
	
}
