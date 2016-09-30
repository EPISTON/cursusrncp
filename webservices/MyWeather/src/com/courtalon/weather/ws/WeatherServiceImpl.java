package com.courtalon.weather.ws;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.jws.WebService;

@WebService(endpointInterface="com.courtalon.weather.ws.WeatherService")
public class WeatherServiceImpl implements WeatherService {
	
	private static final String[] WEATHER = 
		{"sunny", "rain", "cloudy", "snow", "bilzzard", "foggy", "storm", "apocalypse"};
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String getForecast(String ville, Date date) {
		Random rd = new Random();
		return "weather at " + ville + " is " + WEATHER[rd.nextInt(WEATHER.length)]
				+ " on " + sdf.format(date);
	}

}
