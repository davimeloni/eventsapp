package com.davimeloni.eventapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	@NotEmpty
	private String name;
	@NotEmpty
	private String place;
	@NotEmpty
	private String date;
	@NotEmpty
	private String time;
	
	@OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Guest> guest;
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<Guest> getGuest() {
		return guest;
	}
	public void setGuest(List<Guest> guest) {
		this.guest = guest;
	}

	
}
