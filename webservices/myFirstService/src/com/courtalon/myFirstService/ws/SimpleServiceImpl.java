package com.courtalon.myFirstService.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface="com.courtalon.myFirstService.ws.SimpleService")
public class SimpleServiceImpl implements SimpleService {

	private List<Tache> taches;
	
	
	
	public SimpleServiceImpl() {
		taches = new ArrayList<Tache>();
		taches.add(new Tache(1, "faire les courses", 3));
		taches.add(new Tache(2, "inventer la teleportation", 4));
		taches.add(new Tache(3, "nettoyer la salle de bain", 1));
		taches.add(new Tache(4, "finir la saison de game of throne", 2));
	}



	@Override
	public String getSalutation(String name) {
		return "bonjour, "+ name + ", quel beau web service!";
	}
	
	@Override
	public Tache getTache(int id) {
		for (Tache t : taches) {
			if (t.getId() == id)
				return t;
		}
		return null;
	}

	@Override
	public Tache[] getTaches() {
		return taches.toArray(new Tache[] {});
	}
	
	

}
