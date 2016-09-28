package com.courtalon.myFirstService.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService		// cette annotation declare notre interface comme web service
@SOAPBinding(style=Style.DOCUMENT)  // ici, nous choisissons le "style" de message SOAP utilis�
public interface SimpleService {

	@WebMethod	// nous indiquons que cette m�thode devra etre expos�e comme op�ration du webservice
	String getSalutation(String name);
	
	@WebMethod
	Tache getTache(int id);
	
	@WebMethod
	Tache[] getTaches();
}
