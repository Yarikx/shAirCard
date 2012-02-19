package com.gtug.shaircard.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gtug.shaircard.servlet.Util;

@Entity
public class Event extends Jsonable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double latitude;
	private Double longitude;
	private String address;
	private Date timeBegin = new Date();
	private Date timeEnd = new Date(timeBegin.getTime() + 86400000);
	private String name;
	private String description;
	private String creatorId;
	private String password;
	private Boolean usePassword = true;
	
	public Boolean getUsePassword() {
		return usePassword;
	}

	public void setUsePassword(Boolean usePassword) {
		this.usePassword = usePassword;
	}

	private Long peopleCount = (long) 0;

	public Long getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Long peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public static String listToJson(List<Event> l) {
		Gson gson = Util.conjureGson();
		return gson.toJson(l);
	}

	public void incPeopleCount() {
		if (peopleCount == null) {
			peopleCount = (long)1;
		} else {
			peopleCount++;
		}
	}

	public static void copyData(Event from, Event to) {
		to.id = from.id;
		to.latitude = from.latitude;
		to.longitude = from.longitude;
		to.address = from.address;
		to.timeBegin = from.timeBegin;
		to.timeEnd = from.timeEnd;
		to.name = from.name;
		to.description = from.description;
		to.creatorId = from.creatorId;
		to.password = from.password;
		to.usePassword = (to.password != null && !to.password.equals(""));
	}
}
