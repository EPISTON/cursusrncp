package xmlJaxbForm;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Menestrel {
	private int id;
	private String nom;
	private String email;
	private String instrument;
	private int age;
	
	public int getId() {return id;}
	@XmlAttribute
	public void setId(int id) {this.id = id;}
	
	public String getNom() {return nom;}
	@XmlElement
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	@XmlElement
	public void setEmail(String email) {this.email = email;}
	public String getInstrument() {return instrument;}
	@XmlElement
	public void setInstrument(String instrument) {this.instrument = instrument;}
	public int getAge() {return age;}
	@XmlElement
	public void setAge(int age) {this.age = age;}
	
	public Menestrel() { this(0, "", "", "", 0);}
	public Menestrel(int id, String nom, String email, String instrument, int age) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.instrument = instrument;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Menestrel [id=" + id + ", nom=" + nom + ", email=" + email + ", instrument=" + instrument + ", age="
				+ age + "]";
	}
	
	
	
}
