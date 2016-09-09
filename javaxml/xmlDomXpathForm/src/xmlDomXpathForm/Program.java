package xmlDomXpathForm;

import java.io.File;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		// obtenir la documentBuilderFactory
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db;
				try {
					// elle vas nous fournir un parser de fichier xml
					// fichier xml -> un objet Document
					db = dbf.newDocumentBuilder();
					// on peut lire le fichier xml et récupérer le document
					Document doc = db.parse(new File("repertoire.xml"));
					
					XPathFactory xpf = XPathFactory.newInstance();
					XPath xp = xpf.newXPath();
					/*
					 * les requettes XPath utilise une syntaxe analogue au system de fichier
					 * racine -> "/"
					 * tous les balises noms -> "/repertoire/entree/nom"
					 * 
					 */
					XPathExpression xpe = xp.compile("//repertoire/entree/nom");
					NodeList result = (NodeList)xpe.evaluate(doc, XPathConstants.NODESET);
					
					for (int i = 0; i < result.getLength(); i++) {
						switch(result.item(i).getNodeType()) {
							case Node.ELEMENT_NODE:
								System.out.println("c'est une balise");
								break;
							case Node.TEXT_NODE:
								System.out.println("c'est du texte");
								break;
						}
						System.out.println(result.item(i).getNodeName());						
						System.out.println(result.item(i).getTextContent());
					}
					
					System.out.println("-----------------------------------------");
					/*
					 *  le // permet de partir de n'importe ou
					 *  
					 *  //entree -> 3 entree
					 *  //entree/adresse -> 3 adresse
					 *  //entree/adresse[@codePays=2]/ -> 2 adresses
					 *  //entree/adresse[@codePays=2]/../nom" -> 2 noms
					 * 
					 */
					XPathExpression xpe2 = xp.compile("//entree/adresse[@codePays=2]/../nom");
					NodeList result2 = (NodeList)xpe2.evaluate(doc, XPathConstants.NODESET);
					for (int i = 0; i < result2.getLength(); i++) {
						switch(result2.item(i).getNodeType()) {
							case Node.ELEMENT_NODE:
								System.out.println("c'est une balise");
								break;
							case Node.TEXT_NODE:
								System.out.println("c'est du texte");
								break;
						}
						System.out.println(result2.item(i).getNodeName());						
						System.out.println(result2.item(i).getTextContent());
					}
					
					/*
					 * 
					 * la fonction text() de xpath renvoie le contenu textuelle
					 *  de la balise ou on est
					 *  
					 *  ATTENTION, il s'agit d'un objet de type Text, pas d'une String
					 *  
					 */
					System.out.println("---------------------------");
					XPathExpression xpe3 = xp.compile(
							"//entree/adresse[@codePays=2]/../nom/text()"
							);
					NodeList result3 = (NodeList)xpe3.evaluate(doc, XPathConstants.NODESET);
					for (int i = 0; i < result3.getLength(); i++) {
						switch(result3.item(i).getNodeType()) {
							case Node.ELEMENT_NODE:
								System.out.println("c'est une balise");
								break;
							case Node.TEXT_NODE:
								System.out.println("c'est du texte");
								break;
						}
						System.out.println(result3.item(i).getNodeName());						
						System.out.println(result3.item(i).getTextContent());
					}
					/*
					 * le @ est necessaire uniquement pour les attributs
					 * pas besoin pour les sous-balise(contenu textuel)
					 * 
					 */
					System.out.println("---------------------------");
					XPathExpression xpe4 = xp.compile(
							"//entree[age<40]/nom/text()"
							);
					NodeList result4 = (NodeList)xpe4.evaluate(doc, XPathConstants.NODESET);
					for (int i = 0; i < result4.getLength(); i++) {
						switch(result4.item(i).getNodeType()) {
							case Node.ELEMENT_NODE:
								System.out.println("c'est une balise");
								break;
							case Node.TEXT_NODE:
								System.out.println("c'est du texte");
								break;
						}
						System.out.println(result4.item(i).getNodeName());						
						System.out.println(result4.item(i).getTextContent());
					}
					System.out.println("---------------------------");
//					XPathExpression xpe5 = xp.compile(
	//						"sum(//entree/adresse[@codePays=2]/../age)"
		//					);
					//System.out.println((String)xpe5.evaluate(doc, XPathConstants.STRING));
					XPathExpression xpe5 = xp.compile(
							"sum(//entree/adresse[@codePays=2]/../age)"
							);

					System.out.println(xpe5.evaluate(doc, XPathConstants.NUMBER));
					
					System.out.println("---------------------------");
					/*
					 * attention , la numerotation commence a partir de 1
					 *  ici, je récupere la premiere balise tel de chaque contact
					 *  si je veux uniquement la premiere balise
					 *  je peux utiliser *[1]
					 */
					/*XPathExpression xpe6 = xp.compile(
							"//entree[starts-with(nom, 'B')]/contact/tel[1]/text()"
							);*/
					XPathExpression xpe6 = xp.compile(
							"//entree[nom='Courtalon')]/contact/tel[1]/text()"
							);
					NodeList result6 = (NodeList)xpe6.evaluate(doc, XPathConstants.NODESET);
					for (int i = 0; i < result6.getLength(); i++) {
						switch(result6.item(i).getNodeType()) {
							case Node.ELEMENT_NODE:
								System.out.println("c'est une balise");
								break;
							case Node.TEXT_NODE:
								System.out.println("c'est du texte");
								break;
						}
						System.out.println(result6.item(i).getNodeName());						
						System.out.println(result6.item(i).getTextContent());
					}
					
					
				} catch (ParserConfigurationException e) {e.printStackTrace();}
				catch (SAXException e) {e.printStackTrace();}
				catch (IOException e) {e.printStackTrace();}
				catch (XPathExpressionException e) {e.printStackTrace();}
				

	}

}
