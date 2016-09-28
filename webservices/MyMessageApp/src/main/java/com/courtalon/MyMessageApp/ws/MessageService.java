package com.courtalon.MyMessageApp.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface MessageService {
	
	@WebMethod
	String salutation(String name);

}
