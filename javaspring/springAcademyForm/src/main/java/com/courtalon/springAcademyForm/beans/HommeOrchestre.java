package com.courtalon.springAcademyForm.beans;

import java.util.Set;

public class HommeOrchestre implements IArtiste {
	private String nom;
	private Set<IInstrument> instruments;
	
	public Set<IInstrument> getInstruments() {return instruments;}
	public void setInstruments(Set<IInstrument> instruments) {this.instruments = instruments;}
	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() { return nom;}

	@Override
	public void faireNumero() {
		System.out.println("moi, " + getNom() + " vais vous eblouir");
		for (IInstrument i : instruments) {
			i.jouer();
		}
	}

}
