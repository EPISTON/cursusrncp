package com.courtalon.myFirstService;

import javax.xml.ws.Endpoint;

import com.courtalon.myFirstService.ws.SimpleServiceImpl;

public class ServicePublisher {

	public static void main(String[] args) {
		// ici, nous publions le service
		// c'est à dire qu'il lance un veritable serveur
		System.out.println("lancement...");
		Endpoint.publish("http://localhost:8181/ws/simple", new SimpleServiceImpl());
		// http://localhost:8181/ws/simple?wsdl  pour acceder au wsdl
		System.out.println("lancé...");
	}

}
