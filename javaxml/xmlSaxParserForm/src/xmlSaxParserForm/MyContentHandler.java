package xmlSaxParserForm;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MyContentHandler implements ContentHandler {

	@Override
	public void setDocumentLocator(Locator locator) {}
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {}
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {}
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
	@Override
	public void skippedEntity(String name) throws SAXException {}

	
	// cette fonction est appelée au démarrage du Document
	@Override
	public void startDocument() throws SAXException {
		System.out.println("----------debut du document XML-------------");
	}

	// cette fonction est appelée a la fin du document XML
	@Override
	public void endDocument() throws SAXException {
		System.out.println("----------fin du document XML-------------");
	}

	// fonction appelée quand il rencontre une balise ouvrante
	// pourquoi un localName et un qName ?
	// il existe un mecanisme de namespace en xml (pour regrouper les balises)
	// localName, nom sans l'espace de nom
	// le qname, nom pleinement qualifié (complet)
	// atts -> collection des attributs de cette balise ouvrante
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		System.out.println("balise ouvrante " + qName);
		for (int i = 0; i < atts.getLength(); i++) {
			System.out.println("attribut: " + atts.getLocalName(i) + " = " + atts.getValue(i));
		}
	}

	// balise fermante
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("balise fermante " + qName);
	}

	// quand il rencontre du texte dans le document (entre 2 balises)
	// char[] ch est le buufer sur lequel il travail
	// start et lenght nous indique la position dans ce buffer 
	// et la longueur du texte qu'il a trouvé
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String texte = new String(ch, start, length);
		System.out.println("texte = " + texte);
	}


	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		System.out.println("processing instruction: " + target + " -> " + data);
	}


}
