package com.courtalon.myFirstClient;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.courtalon.myFirstService.ws.SimpleService;
import com.courtalon.myFirstService.ws.Tache;

public class ServiceConsumer {

	public static void main(String[] args) throws MalformedURLException {
		// adresse du wsdl du service a appeler
		URL url = new URL("http://localhost:8181/ws/simple?wsdl");
		
		// on indique le service visé dans le wsdl
		QName qname = new QName(
			"http://ws.myFirstService.courtalon.com/",
			"SimpleServiceImplService");
		
		// pour creer un service, que je puisse appeler
		// j'ai besoin du wsdl, et du nom qualifié du service dans celui-ci
		Service service = Service.create(url, qname);
		
		// la classe Service vas nous renvoyer un objet implémentant
		// l'interface SimpleService
		// lorqu'on apellera une methode de cet objet, jax-ws serialisera
		// l'appel, enverra un message SOAP au service distant
		// puis nous renverra la réponse du service
		SimpleService tachesService = service.getPort(SimpleService.class);
		
		System.out.println(tachesService.getSalutation("vincent"));
		
		Tache[] taches = tachesService.getTaches();
		for (Tache t: taches) {
			System.out.println(t.getId() + " - " + t.getPriorite() + " - " + t.getTitre());
		}
		
	}

}
