package com.courtalon.springDomForm.spring;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		try {
			Document doc = ctx.getBean("doc", Document.class);
			
			NodeList list = doc.getElementsByTagName("nom");
			for (int i = 0; i < list.getLength(); i++)
			{
				Element el = (Element) list.item(i);
				System.out.println("element -> " + el.getNodeName());
				System.out.println("value -> " + el.getFirstChild().getNodeValue());
			}
			
			System.out.println("-----------------------------");
			
			
			XPathExpression xpe = ctx.getBean("xpe", XPathExpression.class);
			
			// je requette le document
			list = (NodeList) xpe.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < list.getLength(); i++)
			{
				System.out.println("value -> " + list.item(i).getNodeValue());
			}
			
			
		
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}





}
