package com.gtug.shaircard.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Entity
public class VCard extends Jsonable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String middleName;
	private String surname;
	private String company;
	private String position;
	private String creatorId;
	private String email;
	private String phone;
	private Text base64Image;
	private Long eventId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Text getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(Text base64Image) {
		this.base64Image = base64Image;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public static String listToJson(List<VCard> l) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss Z")
				.create();
		return gson.toJson(l);
	}

	public static void copyData(VCard from, VCard to) {
		to.firstName = from.firstName;
		to.middleName = from.middleName;
		to.surname = from.surname;
		to.company = from.company;
		to.position = from.position;
		to.base64Image = from.base64Image;
		to.creatorId = from.creatorId;
	}
}
