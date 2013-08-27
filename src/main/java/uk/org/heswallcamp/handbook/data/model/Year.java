package uk.org.heswallcamp.handbook.data.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Year {

	@Id
	@JsonProperty
	private Integer year;
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<Boy> boys;
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<Helper> helpers;
	@JsonProperty
	private LocalDate startDate;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Set<Helper> getHelpers() {
		return helpers;
	}
	public void setHelpers(Set<Helper> helpers) {
		this.helpers = helpers;
	}
	public Set<Boy> getBoys() {
		return boys;
	}
	public void setBoys(Set<Boy> boys) {
		this.boys = boys;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
		
}
