package uk.org.heswallcamp.handbook.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class KitchenRota {

	@Id
	@JsonProperty
	private MealTime mealTime;
	@JsonProperty
	private Helper helper;
	
	public Helper getHelper() {
		return helper;
	}
	public void setHelper(Helper helper) {
		this.helper = helper;
	}
	public MealTime getMealTime() {
		return mealTime;
	}
	public void setMealTime(MealTime mealTime) {
		this.mealTime = mealTime;
	}
	
}
