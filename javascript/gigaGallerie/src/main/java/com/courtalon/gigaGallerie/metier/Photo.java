package com.courtalon.gigaGallerie.metier;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Photo {
	private int id;
	private String nom;
	private String description;
	private Date dateCreation;
	private String fileName;
	private String contentType;
	
	private Galerie galerie;
	private Set<Tag> tags;
	
	@ManyToMany
	public Set<Tag> getTags() {
		if (tags == null)
			tags = new HashSet<>();
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	@ManyToOne
	public Galerie getGalerie() {
		return galerie;
	}
	public void setGalerie(Galerie galerie) {
		this.galerie = galerie;
	}
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.DATE)
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public Photo() { this(0, "", "", null, "", "");}
	public Photo(int id, String nom, String description, Date dateCreation, String fileName, String contentType) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dateCreation = dateCreation;
		this.fileName = fileName;
		this.contentType = contentType;
	}
	
	
	

}
