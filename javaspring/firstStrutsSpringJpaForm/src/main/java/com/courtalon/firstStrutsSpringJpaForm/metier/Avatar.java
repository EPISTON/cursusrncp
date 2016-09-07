package com.courtalon.firstStrutsSpringJpaForm.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Lob;

@Entity
public class Avatar {
	private int id;
	private String fileName;
	private String contentType;

	
	// si on veut stocker directement les données binaires dans la table
	//@Lob
	//private byte[] data;
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}
	@Column(length=100)
	public String getContentType() {return contentType;}
	public void setContentType(String contentType) {this.contentType = contentType;}
	
	public Avatar() { this(0, "", "");}
	public Avatar(int id, String fileName, String contentType) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.contentType = contentType;
	}

}
