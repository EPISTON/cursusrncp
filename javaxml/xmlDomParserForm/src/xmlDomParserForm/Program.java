package xmlDomParserForm;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		
		// obtenir la documentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// elle vas nous fournir un parser de fichier xml
			// fichier xml -> un objet Document
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			// on peut lire le fichier xml et récupérer le document
			Document doc = db.parse(new File("repertoire.xml"));
			
			// l'interface Node est implementée par tous
			// les types d'objet (Element, Attr, Text, etc...)
			// que l'on peut rencontrer en XML
			NodeList result =  doc.getElementsByTagName("nom");
			for (int i = 0; i < result.getLength(); i++) {
				// même si c'est une NodeList, je sais que je n'aurais ici
				// que des ELement, ayant demandé des balises avec getElementsByTagName
				Element balise = (Element)result.item(i);
				System.out.println(balise.getTextContent());
			}
			Random rd = new Random();
			System.out.println("------------------------------");
			NodeList result2 = doc.getElementsByTagName("entree");
			for (int i = 0; i < result2.getLength(); i++) {
				Element entree = (Element)result2.item(i);
				
				Element age = doc.createElement("age");
				age.appendChild(doc.createTextNode(""+ ( rd.nextInt(41) + 20)));
				entree.appendChild(age);
				
				
				System.out.println(entree.getAttribute("no"));
				NodeList childs = entree.getElementsByTagName("email");
				for (int j = 0; j < childs.getLength(); j++) {
					Element email = (Element)childs.item(j);
					// j'affiche le contenu entre la bilse email ouvrante et fermante
					System.out.println(email.getTextContent());
					System.out.println("code pays = " +
					((Element)email.getParentNode()
								   .getPreviousSibling()
								   .getPreviousSibling()).getAttribute("codePays"));
				}
			}
			
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			StreamResult destination = new StreamResult(new File("sortie.xml"));
			DOMSource source = new DOMSource(doc);
			
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			
			tf.transform(source, destination);
			
			
		} catch (ParserConfigurationException e) {e.printStackTrace();}
		catch (SAXException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();	}
		catch (TransformerConfigurationException e) {e.printStackTrace();}
		catch (TransformerFactoryConfigurationError e) {e.printStackTrace();}
		catch (TransformerException e) {e.printStackTrace();}
		

	}

}
