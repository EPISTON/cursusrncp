package xmljavaexo2Form;

import java.io.File;
import java.io.IOException;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// elle vas nous fournir un parser de fichier xml
			// fichier xml -> un objet Document
			DocumentBuilder db = dbf.newDocumentBuilder();
			// on peut lire le fichier xml et récupérer le document
			Document doc = db.parse(new File("books.xml"));
			
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xp = xpf.newXPath();
			
			// A) requeter les titres de tous les livres
			XPathExpression xpe = xp.compile("/catalog/book/title/text()");
			NodeList result = (NodeList)xpe.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < result.getLength(); i++) {
				System.out.println(result.item(i).getTextContent());
			}
			System.out.println("----------------------------");
			
			//B) requeter les titres de tous les livres de langue anglaise
			XPathExpression xpe2 = xp.compile("/catalog/book[@langage='en']/title/text()");
			NodeList result2 = (NodeList)xpe2.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < result2.getLength(); i++) {
				System.out.println(result2.item(i).getTextContent());
			}
			System.out.println("----------------------------");
			
			// C) requeter les titres de tous les livres de langue anglaise dont le prix
			// est supérieur a une certaine valeur
			XPathExpression xpe3 = xp.compile("/catalog/book[@langage='en' and price>10]/title/text()");
			//XPathExpression xpe3 = xp.compile("/catalog/book[@langage='en'][price>10]/title/text()");
			NodeList result3 = (NodeList)xpe3.evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < result3.getLength(); i++) {
				System.out.println(result3.item(i).getTextContent());
			}
			System.out.println("----------------------------");
			
			// D) requeter les titres de tous les livres dont le prix
			//	est supérieur a une certaine valeur et le stock inférieur a une autre valeur
			XPathExpression xpe4 = xp.compile("/catalog/book[@stock<10  and price>10]/title/text()");
			NodeList result4 = (NodeList)xpe4.evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < result4.getLength(); i++) {
				System.out.println(result4.item(i).getTextContent());
			}
			System.out.println("----------------------------");
			
			//en utilisant XPATH, exporter un nouveau fichier XML contenant
			//	uniquement les livres d'un certain auteur
			XPathExpression xpe5 = xp.compile("/catalog/book[author=\"Corets, Eva\"]");
			NodeList result5 = (NodeList)xpe5.evaluate(doc, XPathConstants.NODESET);

			Document export1 = db.newDocument();
			Element catalog = export1.createElement("catalog");
			export1.appendChild(catalog);
			
			for (int i = 0; i < result5.getLength(); i++) {
				catalog.appendChild(export1.adoptNode(result5.item(i)));
			}
			
			

			Transformer tf = TransformerFactory.newInstance().newTransformer();
			StreamResult destination = new StreamResult(new File("sortie1.xml"));
			DOMSource source = new DOMSource(export1);
			
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			tf.transform(source, destination);
			System.out.println("----------------------------");
			
			/*
			 en utilisant XPATH, exporter un nouveau fichier XML contenant
			uniquement les titres et stock des livres d'un prix > valeur
			<livres>
				<livre>
					<title>..</title>
					<stock>...</stock>
				</livre>
				...
			</livres>
						
			*/
			XPathExpression xpe6 = xp.compile("/catalog/book[price>10]");
			NodeList result6 = (NodeList)xpe6.evaluate(doc, XPathConstants.NODESET);
			Document export2 = db.newDocument();
			Element livres = export2.createElement("livres");
			for (int i = 0; i < result6.getLength(); i++) {
				Element book = (Element)result6.item(i);
				Element livre = export2.createElement("livre");
				// j'ai "copie" la balise "title" d'un document a l'autre
				livre.appendChild(export2.adoptNode(book.getElementsByTagName("title").item(0)));
				Element stock = export2.createElement("stock");
				stock.setTextContent(book.getAttribute("stock"));
				livre.appendChild(stock);
				livres.appendChild(livre);
			}
			export2.appendChild(livres);
			StreamResult destination2 = new StreamResult(new File("sortie2.xml"));
			DOMSource source2 = new DOMSource(export2);
			
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			tf.transform(source2, destination2);
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			


	}

}
