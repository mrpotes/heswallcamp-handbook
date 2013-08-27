package uk.org.heswallcamp.handbook.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MealTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer key;
	@JsonProperty
	private LocalDate date;
	@JsonProperty
	private Meal meal;
	@JsonProperty
	private boolean inCamp;
	@JsonProperty
	private String location;
	@JsonProperty
	private Year year;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public boolean isInCamp() {
		return inCamp;
	}
	public void setInCamp(boolean inCamp) {
		this.inCamp = inCamp;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	
	
}
