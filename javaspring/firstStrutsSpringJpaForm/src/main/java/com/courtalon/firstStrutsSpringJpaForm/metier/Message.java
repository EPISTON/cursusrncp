package com.courtalon.firstStrutsSpringJpaForm.metier;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Message {
	private int id;
	private String titre;
	private String corps;
	private Set<Etiquette> etiquetages;
	
	@ManyToMany
	public Set<Etiquette> getEtiquetages() {
		if (etiquetages == null)
			etiquetages = new HashSet<>();
		return etiquetages;
	}
	public void setEtiquetages(Set<Etiquette> etiquetages) {
		this.etiquetages = etiquetages;
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
	public String getCorps() {
		return corps;
	}
	public void setCorps(String corps) {
		this.corps = corps;
	}
	
	
	

}
