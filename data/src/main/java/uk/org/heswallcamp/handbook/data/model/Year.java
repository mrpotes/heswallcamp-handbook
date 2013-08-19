package uk.org.heswallcamp.handbook.data.model;

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
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
		
}
