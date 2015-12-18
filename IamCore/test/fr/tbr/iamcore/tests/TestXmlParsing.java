package fr.tbr.iamcore.tests;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TestXmlParsing {
	
	public static void main(String[] args) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document document = db.parse(new File("identities.xml"));
		
		System.out.println(document);
		
		
		NodeList identitiesList = document.getElementsByTagName("identity");
		int length = identitiesList.getLength();
		for (int i = 0; i < length ; i++){
			Element identity = (Element) identitiesList.item(i);
			NodeList properties = identity.getElementsByTagName("property");
			for (int j =0 ; j <properties.getLength() ; j++){
				Element property = (Element) properties.item(j);
				System.out.println(property.getAttribute("name") + " : " + property.getTextContent());
			}
			System.out.println("-----");
			
			
		}
		
		
		
		
	}

}
