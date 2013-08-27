package uk.org.heswallcamp.handbook.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class DormAllocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Integer key;
	@JsonProperty
	private Dorm dorm;
	@JsonProperty
	@ManyToOne
	private Year year;
	@JsonProperty
	@ManyToMany
	private List<Boy> boys;
	@JsonProperty
	@ManyToMany
	private List<Helper> helpers;
	
	public Dorm getDorm() {
		return dorm;
	}
	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public List<Boy> getBoys() {
		return boys;
	}
	public void setBoys(List<Boy> boys) {
		this.boys = boys;
	}
	public List<Helper> getHelpers() {
		return helpers;
	}
	public void setHelpers(List<Helper> helpers) {
		this.helpers = helpers;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
}
