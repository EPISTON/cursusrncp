package com.courtalon.myContactService;

import javax.xml.ws.Endpoint;

import com.courtalon.myContactService.ws.ContactServiceImpl;

public class ContactPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8282/contactService", new ContactServiceImpl());
		System.out.println("service contacts démarré");
	}

}
