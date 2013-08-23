package uk.org.heswallcamp.handbook.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.appengine.api.datastore.Key;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Boy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String name;
	@JsonProperty
	private LocalDate dateOfBirth;
	@JsonProperty
	private String address;
	@JsonProperty
	private List<String> phoneNumbers;
	@JsonProperty
	private String disability;
	@JsonProperty
	private String specialNeeds;
	@JsonProperty
	private String allergies;
	@JsonProperty
	private String notes;
	@JsonProperty
	private List<String> medications;
	private List<Key> campYears;
	@JsonProperty
	@Transient
	private List<Integer> camps;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getDisability() {
		return disability;
	}
	public void setDisability(String disability) {
		this.disability = disability;
	}
	public String getSpecialNeeds() {
		return specialNeeds;
	}
	public void setSpecialNeeds(String specialNeeds) {
		this.specialNeeds = specialNeeds;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<Key> getCampYears() {
		return campYears;
	}
	public void setCampYears(List<Key> campYears) {
		this.campYears = campYears;
	}
	public List<Integer> getCamps() {
		return camps;
	}
	public void setCamps(List<Integer> camps) {
		this.camps = camps;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
}