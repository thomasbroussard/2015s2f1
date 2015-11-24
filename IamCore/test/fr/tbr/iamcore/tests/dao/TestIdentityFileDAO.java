package fr.tbr.iamcore.tests.dao;

import java.util.Scanner;

import fr.tbr.iamcore.dao.IdentityFileDAO;
import fr.tbr.iamcore.datamodel.Identity;

public class TestIdentityFileDAO {
	
	public static void main(String... args) throws Exception {
		
		
		testCreateAndSearch();
		
		
		
		
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
