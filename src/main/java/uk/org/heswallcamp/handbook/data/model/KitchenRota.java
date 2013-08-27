package uk.org.heswallcamp.handbook.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class KitchenRota {

	@Id
	@JsonProperty
	private MealTime mealTime;
	@JsonProperty
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
	
}
