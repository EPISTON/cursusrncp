#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	private int id;
	private String titre;
	private String corps;

	@Id
	@GeneratedValue	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}



	public Message() {this(0,"","");}

	public Message(int id, String titre, String corps) {
		this.id = id;
		this.titre = titre;
		this.corps = corps;
	}
	

}
