package com.gtug.shaircard.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.stanfy.content.UniqueObject;

public class VCard implements UniqueObject, Serializable {

	public static class Text {
		public Text(String t) {
			value = t;
		}

		String value;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 545331556042499694L;
	private Long id = -1L;
	private String firstName;
	private String middleName;
	private String surname;
	private String company;
	private String position;
	private String creatorId;
	private Text base64Image;
	private Long eventId;

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String localUri;

	// @Override
	// public boolean equals(Object o) {
	// if (o instanceof VCard) {
	// return ((VCard) o).getId() == this.getId()&&
	// ((VCard) o).getFirstName().equals(object)
	// } else {
	// return super.equals(o);
	// }
	// }

	public long getId() {
		return id != null ? id : -1L;
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

	public static VCard getSampleCard() {
		VCard card = new VCard();
		card.firstName = "Forrest";
		card.surname = "Gump";
		card.company = "Bubba Gump Co.";
		card.eventId = (long) 42;
		return card;
	}

	public static String listToJson(List<VCard> l) {
		Gson gson = new Gson();
		return gson.toJson(l);
	}

	public static void copyData(VCard from, VCard to) {
		to.firstName = from.firstName;
		to.middleName = from.middleName;
		to.surname = from.surname;
		to.company = from.company;
		to.position = from.position;
		to.base64Image = from.base64Image;
	}
}
