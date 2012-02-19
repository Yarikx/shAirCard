package com.gtug.shaircard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

@Entity
public class VCardImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long vcardId;
	private Blob image;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVcardId() {
		return vcardId;
	}
	public void setVcardId(Long vcardId) {
		this.vcardId = vcardId;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	
}
