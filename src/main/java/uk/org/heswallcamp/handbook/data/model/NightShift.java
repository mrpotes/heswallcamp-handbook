package uk.org.heswallcamp.handbook.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class NightShift {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer key;
	@JsonProperty
	private LocalTime time;
	@ManyToOne
	private Year year;
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Hours getDuration() {
		return duration;
	}
	public void setDuration(Hours duration) {
		this.duration = duration;
	}
	@JsonProperty
	private Hours duration;
	
}
