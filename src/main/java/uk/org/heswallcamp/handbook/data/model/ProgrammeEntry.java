package uk.org.heswallcamp.handbook.data.model;

import javax.persistence.Entity;

import org.joda.time.DateTime;
import org.joda.time.Duration;

@Entity
public class ProgrammeEntry {

	private DateTime time;
	private Duration duration;
	private String title;
	private String details;
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
