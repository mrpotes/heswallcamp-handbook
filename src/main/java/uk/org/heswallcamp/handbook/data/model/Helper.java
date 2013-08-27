package uk.org.heswallcamp.handbook.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.LocalDate;

@Entity
public class Helper {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer key;
	private String name;
	private LocalDate dateOfBirth;
	private String address;
	private String email;
	private List<String> phoneNumbers;
	private List<Year> camps;
	private String notes;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public List<Year> getCamps() {
		return camps;
	}
	public void setCamps(List<Year> camps) {
		this.camps = camps;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getKey() {
		return key;
	}
	
}
