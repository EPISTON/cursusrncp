package com.courtalon.myContactService.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface="com.courtalon.myContactService.ws.ContactService")
public class ContactServiceImpl implements ContactService {

	private List<Contact> contacts;
	private int compteur = 0;
	
	public ContactServiceImpl() {
		contacts = new ArrayList<Contact>();
		contacts.add(new Contact(1, "potter", "harry", "harry@poudlard.com"));
		contacts.add(new Contact(2, "granger", "hermione", "hermione@mudbloodproud.com"));
		contacts.add(new Contact(3, "weasel", "ron", "ron@gingerforever.com"));
		compteur = 3;
	}

	@Override
	public List<Contact> getContacts() {
		return contacts;
	}

	@Override
	public Contact findByID(int id) {
		return contacts.stream()
						.filter(c -> (c.getId() == id))
						.findFirst().get();
	}

	@Override
	public Contact findByEmail(String email) {
		return contacts.stream()
				.filter(c -> (c.getEmail().equals(email)))
				.findFirst().get();
	}

	@Override
	public Contact modification(Contact contact) {
		Contact contactToModify = contacts.stream()
				.filter(c -> (c.getId() == contact.getId()))
				.findFirst().get();
		if (contactToModify != null) {
			contactToModify.setEmail(contact.getEmail());
			contactToModify.setNom(contact.getNom());
			contactToModify.setPrenom(contact.getPrenom());
		}
		return contactToModify;
	}

	@Override
	public Contact ajout(Contact contact) {
		contact.setId(++compteur);
		contacts.add(contact);
		return contact;
	}

}
