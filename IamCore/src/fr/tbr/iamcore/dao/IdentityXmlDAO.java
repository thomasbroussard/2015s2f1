package fr.tbr.iamcore.dao;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.tbr.iamcore.dao.exceptions.DaoUpdateException;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.match.Matcher;
import fr.tbr.iamcore.services.match.impl.ContainsIdentityMatcher;

public class IdentityXmlDAO implements IdentityDAOInterface {

	Document document;
	
	private Matcher<Identity> activeMatchingStrategy = new ContainsIdentityMatcher();

	public IdentityXmlDAO() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			document = db.parse(new File("identities.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO handle exception
		}finally{
			if (document != null){
				document.getDocumentElement();
			}
		}
	}

	public List<Identity> readAll() {

		//This is creating an anonymous implementation of the Matcher interface and 
		//instantiating it at the same time
		return internalSearch(null, new Matcher<Identity>(){
			public boolean match(Identity criteria, Identity toBeMatched) {
				return true;
			}
		});
	}

	public List<Identity> search(Identity criteria) {
			return internalSearch(criteria, activeMatchingStrategy);
	}

	private List<Identity> internalSearch(Identity criteria, Matcher<Identity> identityMatcher){
		ArrayList<Identity> resultList = new ArrayList<Identity>();
		NodeList identitiesList = document.getElementsByTagName("identity");
		int length = identitiesList.getLength();
		for (int i = 0; i < length; i++) {
			Element identity = (Element) identitiesList.item(i);
			Identity identityInstance = readIdentityFromXmlElement(identity);
			if(identityMatcher.match(criteria, identityInstance)){
				resultList.add(identityInstance);
			}
		}

		return resultList;
		
	}

	private Identity readIdentityFromXmlElement(Element identity){
		NodeList properties = identity.getElementsByTagName("property");
		Identity identityInstance = new Identity();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int propertiesLength = properties.getLength();
		for (int j = 0; j < propertiesLength; j++) {
			Element property = (Element) properties.item(j);
			String attribute = property.getAttribute("name");
			String value = property.getTextContent().trim();
			switch (attribute) {
			case "displayName":
				identityInstance.setDisplayName(value);
				break;
			case "email":
				identityInstance.setEmail(value);
				break;

			case "guid":
				identityInstance.setUid(value);
				break;
				
			case "birthDate":
				try {
					Date parsedDate = simpleDateFormat.parse(value);
					identityInstance.setBirthDate(parsedDate);
				} catch (ParseException e) {
					// TODO Check if the birthDate should provoke the cancellation of the current identity reading
					e.printStackTrace();
				}
				break;
			}
		}
		return identityInstance;
	}
	
	
	@Override
	public void create(Identity identity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Identity identity) throws DaoUpdateException {
		

	}

	@Override
	public void delete(Identity identity) {
		// TODO Auto-generated method stub

	}

}
