package fr.tbr.iamcore.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.tbr.iamcore.dao.exceptions.DaoUpdateException;
import fr.tbr.iamcore.datamodel.Identity;

public class IdentityXmlDAO implements IdentityDAOInterface {

	Document document;

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

		ArrayList<Identity> resultList = new ArrayList<Identity>();
		NodeList identitiesList = document.getElementsByTagName("identity");
		int length = identitiesList.getLength();
		for (int i = 0; i < length; i++) {
			Element identity = (Element) identitiesList.item(i);
			NodeList properties = identity.getElementsByTagName("property");
			Identity identityInstance = new Identity();
			int propertiesLength = properties.getLength();
			for (int j = 0; j < propertiesLength; j++) {
				Element property = (Element) properties.item(j);
				String attribute = property.getAttribute("name");
				System.out.println(attribute + " : "
						+ property.getTextContent());
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
				}
			}
			resultList.add(identityInstance);

		}

		return resultList;
	}

	public List<Identity> search(Identity identity) {
		return new ArrayList<Identity>();
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
