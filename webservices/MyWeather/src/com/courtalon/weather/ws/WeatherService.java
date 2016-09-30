package com.courtalon.weather.ws;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface WeatherService {
	
	@WebMethod
	String getForecast(@WebParam(name="ville")String ville,
					   @WebParam(name="date")Date date);
	

}
