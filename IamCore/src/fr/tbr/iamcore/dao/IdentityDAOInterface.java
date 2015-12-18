package fr.tbr.iamcore.dao;

import java.util.List;

import fr.tbr.iamcore.dao.exceptions.DaoUpdateException;
import fr.tbr.iamcore.datamodel.Identity;

public interface IdentityDAOInterface {

	
	public void create(Identity identity);
	
	public List<Identity> readAll();
	
	public List<Identity> search(Identity criteria);
	
	public void update(Identity identity) throws DaoUpdateException;
	public void delete(Identity identity);
	
}
