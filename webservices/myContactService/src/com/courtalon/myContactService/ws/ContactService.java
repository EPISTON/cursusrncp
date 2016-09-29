package com.courtalon.myContactService.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL)
public interface ContactService {
	
	@WebMethod
	List<Contact> getContacts();
	@WebMethod
	Contact findByID(@WebParam(name="id") int id);
	@WebMethod
	Contact findByEmail(@WebParam(name="email") String email);
	@WebMethod
	Contact modification(@WebParam(name="contact") Contact contact);
	@WebMethod
	Contact ajout(@WebParam(name="contact") Contact contact);

}
