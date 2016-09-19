package com.courtalon.gigaGallerie.metier;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Galerie {
	private int id;
	private String titre;
	private Date dateCreation;
	private Set<Photo> photos;
	
	
	@OneToMany(mappedBy="galerie")
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	@Temporal(TemporalType.DATE)
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public Galerie() { this(0, "", null);}
	public Galerie(int id, String titre, Date dateCreation) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateCreation = dateCreation;
	}
	
	
}
