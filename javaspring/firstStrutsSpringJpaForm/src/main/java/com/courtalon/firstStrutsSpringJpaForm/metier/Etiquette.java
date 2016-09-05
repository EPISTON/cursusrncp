package com.courtalon.firstStrutsSpringJpaForm.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Etiquette {
	private int id;
	private String libelle;
	private Set<Message> tagged_messages;
	
	@ManyToMany(mappedBy="etiquetages")
	public Set<Message> getTagged_messages() {
		if (tagged_messages == null)
			tagged_messages = new HashSet<>();
		return tagged_messages;
	}
	public void setTagged_messages(Set<Message> tagged_messages) {
		this.tagged_messages = tagged_messages;
	}
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	public Etiquette() {this(0, ""); }
	public Etiquette(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Etiquette [id=" + id + ", libelle=" + libelle + "]";
	}
	
	

}
