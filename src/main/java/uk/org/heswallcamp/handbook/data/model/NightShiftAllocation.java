package uk.org.heswallcamp.handbook.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class NightShiftAllocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer key;
	@JsonProperty
	private Helper helper;
	@JsonProperty
	@ManyToOne
	private NightShift nightShift;
	@JsonProperty
	private LocalDate date;
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public Helper getHelper() {
		return helper;
	}
	public void setHelper(Helper helper) {
		this.helper = helper;
	}
	public NightShift getNightShift() {
		return nightShift;
	}
	public void setNightShift(NightShift nightShift) {
		this.nightShift = nightShift;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	private Year year;
	
}
