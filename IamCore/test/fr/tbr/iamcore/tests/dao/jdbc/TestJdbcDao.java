package fr.tbr.iamcore.tests.dao.jdbc;

import java.util.List;

import fr.tbr.iamcore.dao.IdentityJdbcDAO;
import fr.tbr.iamcore.datamodel.Identity;

public class TestJdbcDao {
	
	
	
	public static void main(String[] args){
		IdentityJdbcDAO dao = new IdentityJdbcDAO();
		List<Identity> identities = dao.readAll();
		
		System.out.println(identities);
		
		
		dao.close();
		
	}

}
