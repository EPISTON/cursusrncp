package xmlSaxParserForm;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class FilterByCodePaysHandler implements ContentHandler {

	private String currentBalise;
	private String currentNom;
	private String currentCodePays;
	private String currentEmail;
	
	
	private String codePaystoFind;
	
	
	public FilterByCodePaysHandler(String codePaystoFind) {
		this.codePaystoFind = codePaystoFind;
	}
	
	
	@Override
	public void setDocumentLocator(Locator locator) {}
	@Override
	public void endDocument() throws SAXException {}
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {}
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {}
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
	@Override
	public void processingInstruction(String target, String data) throws SAXException {}
	@Override
	public void skippedEntity(String name) throws SAXException {}

	//
	//----------------------------------------
	
	@Override
	public void startDocument() throws SAXException {
		this.currentBalise = null;
		this.currentCodePays = null;
		this.currentNom = null;
		this.currentEmail = null;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// on memmorise la balise ou on est
		this.currentBalise = localName;
		// si la balise est adresse, memoriser le codePays associé
		if (localName.equals("adresse")) {
			this.currentCodePays = atts.getValue("codePays");
		}
		else if (localName.equals("entree")) {
			this.currentBalise = null;
			this.currentCodePays = null;
			this.currentNom = null;
			this.currentEmail = null;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.currentBalise = null;
		if (localName.equals("entree")) {
			if (codePaystoFind.equals(currentCodePays)) {
				// c'est un des contact qui nous intéresse!
				System.out.println("nom = " + this.currentNom);
				System.out.println("email = " + this.currentEmail);
				// on sort du contact, tout reinitialiser
				this.currentCodePays = null;
				this.currentNom = null;
				this.currentEmail = null;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if ("nom".equals(currentBalise)) {
			if (currentNom == null)
				currentNom = new String(ch, start, length);
			else
				currentNom += new String(ch, start, length);
		}
		if ("email".equals(currentBalise)) {
			if (currentEmail == null)
				currentEmail = new String(ch, start, length);
			else
				currentEmail += new String(ch, start, length);
		}
		
	}


}
