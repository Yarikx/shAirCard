package com.gtug.shaircard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

@Entity
public class VCardImage {

	@Id
	private Long vcardId;
	private Blob image;
	
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
