package com.courtalon.weather;

import javax.xml.ws.Endpoint;

import com.courtalon.weather.ws.WeatherServiceImpl;

public class WeatherPublish {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8383/weather",
				new WeatherServiceImpl());
		System.out.println("service d�marr� sur: http://localhost:8383/weather");
	}

}
