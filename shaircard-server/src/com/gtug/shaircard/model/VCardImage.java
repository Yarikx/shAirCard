package com.gtug.shaircard.model;

import javax.persistence.Id;

public class VCardImage {

	@Id
	private Long id;
	private Long vcardId;
	private String base64Image;
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
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	
	
}
