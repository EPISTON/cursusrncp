package xmljavaschemaForm;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		SchemaFactory sfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		try {
			Schema schema = sfactory.newSchema(new File("repertoire.xsd"));
			
			// on veut valider, on obtient un objet validateur a partir du schema
			Validator validateur = schema.newValidator();
			
			validateur.validate(new StreamSource(new File("repertoire.xml")));
			
			System.out.println("validation réussie");
			
		} catch (SAXException e) {e.printStackTrace();}
		catch (IOException e) {	e.printStackTrace();}
		

	}

}
