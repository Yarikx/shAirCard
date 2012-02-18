package com.gtug.shaircard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.stanfy.content.UniqueObject;

public class Event implements Serializable, UniqueObject {

	private Long id;
	private Double latitude;
	private Double longitude;
	private String address;
	private Date timeBegin;
	private String name;
	private String description;
	private String creatorId;
	private String password;

	@Override
	public long getId() {
		return id.longValue();
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

	private Date timeEnd;

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
		Gson gson = new Gson();
		return gson.toJson(l);
	}

}
