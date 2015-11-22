package fr.tbr.iamcore.tests.dao;

import java.util.Scanner;

import fr.tbr.iamcore.dao.IdentityFileDAO;
import fr.tbr.iamcore.datamodel.Identity;

public class TestIdentityFileDAO {
	
	public static void main(String... args) throws Exception {
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
