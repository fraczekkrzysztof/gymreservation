package com.fraczekkrzysztof.gymreservation.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ac_id")
	private int id;
	@Column(name="ac_symbol")
	private String symbol;
	@Column(name="ac_name")
	private String name;
	@OneToMany(mappedBy="activity", cascade= {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Set<Class> classes;

	public Activity() {
		
	}


	public Activity(int id, String symbol, String name, Set<Class> classes) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.name = name;
		this.classes = classes;
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

	
	public Set<Class> getClasses() {
		return classes;
	}


	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}


	@Override
	public String toString() {
		return "Activity [id=" + id + ", symbol=" + symbol + ", name=" + name + ", classes=" + classes + "]";
	}


	
	
	
}
