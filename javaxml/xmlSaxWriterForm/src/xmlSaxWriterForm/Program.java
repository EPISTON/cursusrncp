package xmlSaxWriterForm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Program {

	public static void main(String[] args) {
		// ecriture d'un fichier XML en sax
		
		try {
			// on récupère un objet permettant d'ecrire un fichier xml
			// via la XMLOutputFactory
			XMLStreamWriter writer =
					XMLOutputFactory.newInstance()
									.createXMLStreamWriter(
									new FileOutputStream("sortie.xml"),
									"UTF-8");
			writer.writeStartDocument("UTF-8", "1.0");
			writer.writeCharacters("\n");
			writer.writeStartElement("data");
				writer.writeCharacters("\n");
				writer.writeStartElement("item");
				writer.writeAttribute("code", "ET0001");
					writer.writeCharacters("une entree");
				writer.writeEndElement();
				writer.writeCharacters("\n");
				writer.writeStartElement("item");
				writer.writeAttribute("code", "ET0002");
					writer.writeCharacters("une autre entree");
				writer.writeEndElement();
				writer.writeCharacters("\n");
			writer.writeEndElement();
			writer.writeCharacters("\n");
			writer.writeEndDocument();
			writer.close();
			
			
			
		} catch (FileNotFoundException e) {e.printStackTrace();}
		catch (XMLStreamException e) {e.printStackTrace();}
		catch (FactoryConfigurationError e) {e.printStackTrace();}
		
		
		
		
		

	}

}
