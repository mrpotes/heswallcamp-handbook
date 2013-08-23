package uk.org.heswallcamp.handbook.data.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Year {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	private Integer year;
	private Set<Key> boys;
	private Set<Key> helpers;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Key getKey() {
		return key;
	}
	public Set<Key> getHelpers() {
		return helpers;
	}
	public void setHelpers(Set<Key> helpers) {
		this.helpers = helpers;
	}
	public Set<Key> getBoys() {
		return boys;
	}
	public void setBoys(Set<Key> boys) {
		this.boys = boys;
	}
		
}
