package fr.tbr.iamcore.dao.exceptions;

public class DaoUpdateException extends Exception{
	
	
	public DaoUpdateException(Object problematicInstance) {
		// use String.valueOf() instead of toString() when you are not sure of the parameter initialization
		super("a problem occured while updating this instance " + String.valueOf(problematicInstance));

	}
	
	

}
