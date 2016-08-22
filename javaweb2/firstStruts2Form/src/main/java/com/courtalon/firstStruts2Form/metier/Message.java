package com.courtalon.firstStruts2Form.metier;

public class Message {
	private int id;
	private String titre;
	private String corps;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	
	public Message() {this(0, "", "");}
	public Message(int id, String titre, String corps) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
	}
	
	
}
