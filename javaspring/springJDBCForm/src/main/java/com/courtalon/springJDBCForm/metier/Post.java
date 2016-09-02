package com.courtalon.springJDBCForm.metier;

public class Post {
	private int id;
	private String titre;
	private String corps;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	
	public Post() {this(0, "", "");}
	public Post(int id, String titre, String corps) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", titre=" + titre + ", corps=" + corps + "]";
	}
	
	
	
}
