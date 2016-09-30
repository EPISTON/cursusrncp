package com.courtalon.ProfileWebAppForm.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.courtalon.ProfileWebAppForm.metier.UserProfile;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface ProfileService {
	
	@WebMethod
	List<UserProfile> getProfiles();
	
	@WebMethod
	UserProfile getProfil(@WebParam(name="id")int id);
	
}
