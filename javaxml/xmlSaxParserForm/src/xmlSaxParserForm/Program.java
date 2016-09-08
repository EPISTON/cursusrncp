package xmlSaxParserForm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Program {

	public static void main(String[] args) {
		/*
		 * 
		 * principe d'un parser SAX
		 * vous fournissez une objet a vous qui sera prévenu
		 * quand le parser sax rencontrera quelquechose dans le fichier lu
		 * cet objet est ce qu'on appele un ContentHandler 
		 * 		ex -> balise ouvrante, fermante, texte, etc....
		 * 
		 * lancer la lecture par sax -> appel des methode du content handler
		 * 
		 * d'ou le asynchronous de SAX
		 */

		
		try {
			// obtenir le parser sax
			XMLReader reader = XMLReaderFactory.createXMLReader();
			// j'associe mon content handler au reader
			//reader.setContentHandler(new MyContentHandler());
			reader.setContentHandler(new FilterByCodePaysHandler("2"));
			
			System.out.println("demarrage du parser");
			
			reader.parse(new InputSource(new FileInputStream("repertoire.xml")));
			
			System.out.println("parser terminé");
			
		} catch (SAXException e) {e.printStackTrace();}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {	e.printStackTrace();}
		
		
	}

}
