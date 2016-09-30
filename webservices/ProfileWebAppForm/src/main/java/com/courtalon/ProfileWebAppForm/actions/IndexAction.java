package com.courtalon.ProfileWebAppForm.actions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.ProfileWebAppForm.metier.UserProfile;
import com.courtalon.ProfileWebAppForm.repositories.UserProfileRepository;
import com.courtalon.weather.ws.WeatherService;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(ActionSupport.class); 
	private static final long serialVersionUID = 1L;

	private WeatherService weatherService;
	public WeatherService getWeatherService() {
		return weatherService;
	}
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	private UserProfileRepository userProfileRepository;
	public UserProfileRepository getUserProfileRepository() {
		return userProfileRepository;
	}
	public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	private String message;
	private UserProfile userProfile;
	public UserProfile getUserProfile() {
		return userProfile;
	}
	private int UserProfileID;
	public int getUserProfileID() {
		return UserProfileID;
	}
	public void setUserProfileID(int userProfileID) {
		UserProfileID = userProfileID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/*
	 * 	actions
	 */
	public String details() {
		userProfile = userProfileRepository.findOne(getUserProfileID());
		Calendar cal = Calendar.getInstance();
		Date d = new Date();
		message = weatherService.getForecast(userProfile.getVille(), d);
		cal.setTime(d);
		cal.add(Calendar.DATE, 1);
		message += ", " + weatherService.getForecast(userProfile.getVille(),
													cal.getTime());
		return SUCCESS;
	}

	public String index() {
		log.info("appel de index!");
		message = "bonjour depuis index, me meteo: "
				+ weatherService.getForecast("paris", new Date());
		return SUCCESS;
	}

}
