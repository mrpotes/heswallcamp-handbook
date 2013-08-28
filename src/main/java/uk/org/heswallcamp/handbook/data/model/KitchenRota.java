package uk.org.heswallcamp.handbook.data.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class KitchenRota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Integer key;
	@JsonProperty
	@OneToOne
	private MealTime mealTime;
	@JsonProperty
	@ElementCollection
	private List<Helper> helpers;
	
	public List<Helper> getHelpers() {
		return helpers;
	}
	public void setHelpers(List<Helper> helpers) {
		this.helpers = helpers;
	}
	public MealTime getMealTime() {
		return mealTime;
	}
	public void setMealTime(MealTime mealTime) {
		this.mealTime = mealTime;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	
}
