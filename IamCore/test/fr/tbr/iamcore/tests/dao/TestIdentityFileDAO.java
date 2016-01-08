package fr.tbr.iamcore.tests.dao;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import fr.tbr.iamcore.dao.IdentityDAOInterface;
import fr.tbr.iamcore.dao.IdentityFileDAO;
import fr.tbr.iamcore.dao.IdentityXmlDAO;
import fr.tbr.iamcore.datamodel.Identity;

public class TestIdentityFileDAO {
	
	public static void main(String... args) throws Exception {
		
		
	//testCreateAndSearch();
		
//		testUpdate();
		
		readAll();
		
	}

	
	private static void readAll(){
		IdentityDAOInterface dao = new IdentityXmlDAO();
		System.out.println(dao.readAll());
	}

	private static void testUpdate() throws Exception, FileNotFoundException {
		//set the context
		IdentityDAOInterface dao = new IdentityFileDAO();
		dao.create(new Identity("123456", "test@test.com", "test identity"));
		
		//This should return at least one result
		List<Identity> list = dao.search(new Identity(null, "te", "test"));
		
		Identity foundIdentity = list.get(0);
		System.out.println("found this identity :");
		System.out.println(foundIdentity);
		System.out.println();
		
		//change the value of that identity
		foundIdentity.setDisplayName("updated test identity");
		System.out.println("modified this identity : ");
		System.out.println(foundIdentity);
		System.out.println();
		
		//try to update using the dao
		dao.update(foundIdentity);
		
		//check the result
		//checking that the old criteria resolves nothing
		list = dao.search(new Identity(null, "te", "test"));
		System.out.println("this should not contain the original identity");
		System.out.println(list);
		
		//checking that the new version can be found in the file
		list= dao.search(new Identity(null, "te", "updated"));
		System.out.println("this should contain the new version of the identity");
		System.out.println(list);
	}

	
	//TestCase
	private static void testCreateAndSearch() throws Exception {
		
		IdentityFileDAO dao = new IdentityFileDAO();
		
		dao.create(new Identity("123", "qdc@qdc.com", "Quentin Decayeux"));
		dao.create(new Identity("456", "sgu@qdc.com", "Sandy Guilleaumau"));
		dao.create(new Identity("789", "cle@qdc.com", "Clément Serrano"));
		
		System.out.println("The input is finished");
		System.out.println("This is what has been written : ");
		
		//test with partial criteria
		System.out.println(dao.search(new Identity(null, "sgu@qdc.com", null)));
		System.out.println(dao.search(new Identity(null, null, "Quentin Decayeux" )));
		
		
		
		dao.close();
		
	}


	//TestCase
	private static void testCreateAndReadAll() throws Exception {
		Scanner scanner = new Scanner(System.in);
		IdentityFileDAO dao = new IdentityFileDAO();
		String answer = "";
		
		while (!answer.equals("n")){
			Identity identity = getIdentityFromInput(scanner);
			dao.create(identity);
			System.out.println("would you like to continue? (y|n)");
			answer = scanner.nextLine();
		}
		System.out.println("The input is finished");
		System.out.println("This is what has been written : ");
		System.out.println(dao.readAll());
		
		dao.close();
		scanner.close();
	}

	
	/**
	 * get an identity from the console
	 * @param scanner
	 * @return
	 */
	public static Identity getIdentityFromInput(Scanner scanner) {
		
		System.out.println("Please, type the name for the identity");
		String identityName = scanner.nextLine();
		
		System.out.println("Please, type the email for the identity");
		String emailAddress = scanner.nextLine();

		System.out.println("Please, type the uid for the identity");
		String uid = scanner.nextLine();
				
		Identity identity = new Identity(identityName, emailAddress, uid);
		
		System.out.println("Thank you, you have input those information:");
		
		System.out.println(identity);
		
		return identity;
	}

	
}
