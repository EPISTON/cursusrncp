package com.courtalon.jpa_heritageSingleTableForm.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="4")
public class ClientGold extends Client {
	private String codeGold;

	public String getCodeGold() {
		return codeGold;
	}

	public void setCodeGold(String codeGold) {
		this.codeGold = codeGold;
	}

	public ClientGold()  { this(0, "", "", "", 0.0, "");}
	public ClientGold(int id, String nom, String prenom, String codeClient, double soldeCompte, String codeGold) {
		super(id, nom, prenom, codeClient, soldeCompte);
		this.codeGold = codeGold;
	}

	@Override
	public String toString() {
		return "ClientGold [codeGold=" + codeGold + ", getCodeClient()=" + getCodeClient() + ", getSoldeCompte()="
				+ getSoldeCompte() + ", getId()=" + getId() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ "]";
	}

	

}
