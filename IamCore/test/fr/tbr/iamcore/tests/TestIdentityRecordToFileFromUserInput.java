package fr.tbr.iamcore.tests;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import fr.tbr.iamcore.datamodel.Identity;

public class TestIdentityRecordToFileFromUserInput {
	
	
	public static void main(String[] args) throws IOException{
		
		String filePath = "/tests/identities.txt";
		File file = ensureFileExists(filePath);
		PrintWriter writer = new PrintWriter(file);
		
		Scanner scanner = new Scanner(System.in);
		Identity identity = getIdentityFromInput(scanner);
		Identity identity2 = getIdentityFromInput(scanner);
		
		writeIdentityIntoFile(writer, identity);
	
		writeIdentityIntoFile(writer, identity2);
		
		scanner.close();
		writer.close();
		
		
	}



	private static void writeIdentityIntoFile(PrintWriter writer,
			Identity identity) {
		writer.println(identity.getDisplayName());
		writer.println(identity.getEmail());
		writer.println(identity.getUid());
	}
	
	

	/**
	 * This method will check that the file exists or create it if it doesn't
	 * @param pathname
	 * @throws IOException
	 */
	public static File ensureFileExists(String pathname) throws IOException {
		File file = new File(pathname);
		if (!file.exists()){
			//creation code after
			System.out.println("the file does not exists");
			File parent = file.getParentFile();
			if (!parent.exists()){
				parent.mkdirs();
			}
			file.createNewFile();
			System.out.println("file was successfully created");
		}else{
			System.out.println("the file already exists");
		}
		return file;
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
