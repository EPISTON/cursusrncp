package com.courtalon.firstSpringForm.beans;

public class Missive {
	private String destinataire;
	private Message message;
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public Missive() {
		System.out.println("construction Missive");
	}
	
	@Override
	public String toString() {
		return "Missive [destinataire=" + destinataire + ", message=" + message + "]";
	}
	

}
