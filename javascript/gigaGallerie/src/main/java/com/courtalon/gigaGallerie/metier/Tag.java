package com.courtalon.gigaGallerie.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	private int id;
	private String libelle;
	private Set<Photo> photos;
	
	@ManyToMany(mappedBy="tags")
	public Set<Photo> getPhotos() {
		if (photos == null)
			photos = new HashSet<>();
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
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Tag() { this(0, "");}
	public Tag(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	

}
