package com.courtalon.myContactClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.courtalon.myContactService.ws.Contact;
import com.courtalon.myContactService.ws.ContactService;



public class Program {

	public static void main(String[] args) throws MalformedURLException {
	
			URL url = new URL("http://localhost:8282/contactService?wsdl");
			
			
			QName qname = new QName(
				"http://ws.myContactService.courtalon.com/",
				"ContactServiceImplService");
			
			
			Service service = Service.create(url, qname);
			
			
			ContactService contactsService = service.getPort(ContactService.class);
				
			List<Contact> contacts = contactsService.getContacts();
			for (Contact c: contacts)
				System.out.println(c);
			
			
			
	}

}
