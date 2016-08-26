package com.courtalon.jpa_associationonetomany.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Auteur {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	// la collection des posts de l'auteur
	// ici un set, car il n'ya pas de relation d'ordre des posts
	// il n'y a pas dans la base, quoi que ce soit qui indique la "position" du post
	private Set<Post> posts;

	// cette méthode permet d'ajouter un post a un auteur
	// en se chargant de mettre a jour le post, car c'est l'entité
	// maitre de l'association, (qui contient la cle etrangere)
	public void addPost(Post p) {
		if (!getPosts().contains(p)) {
			getPosts().add(p);
			p.setAuteur(this);
		}
	}
	
	@OneToMany(mappedBy="auteur" /*,fetch=FetchType.EAGER*/,
				cascade={CascadeType.REMOVE, CascadeType.MERGE})
	public Set<Post> getPosts() {
		if (posts == null) {
			posts = new HashSet<>();
		}
		return posts;
	}
	
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public Auteur() { this(0, "", "", "");}
	public Auteur(int id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
	
	

}
