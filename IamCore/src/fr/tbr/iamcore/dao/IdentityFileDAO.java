package fr.tbr.iamcore.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.tbr.iamcore.datamodel.Identity;

/**
 * The main DAO for the Identity Class
 * 
 * @author tbrou
 *
 */
public class IdentityFileDAO {

	private static final String path = "/tests/iam/identities.txt";

	private Scanner scanner;
	private PrintWriter writer;

	public IdentityFileDAO() throws Exception {
		initIO();
	}

	/**
	 * 
	 * @param identity
	 */
	public void create(Identity identity) {
		writer.println("--- identity:begin ---");
		writer.println(identity.getDisplayName());
		writer.println(identity.getEmail());
		writer.println(identity.getUid());
		writer.println("--- identity:end---");
		writer.flush();
	}

	/**
	 * 
	 * @return
	 */
	public List<Identity> readAll() {
		List<Identity> result = new ArrayList<Identity>();
		while (scanner.hasNext()) {

			scanner.nextLine();
			String displayName = scanner.nextLine();
			String email = scanner.nextLine();
			String uid = scanner.nextLine();
			scanner.nextLine();
			Identity id = new Identity(uid, email, displayName);
			result.add(id);
		}

		return result;
	}

	public List<Identity> search(Identity criteria) throws FileNotFoundException {
		List<Identity> resultsList = new ArrayList<Identity>();

		while (scanner.hasNext()) {

			scanner.nextLine();
			String displayName = scanner.nextLine();
			String email = scanner.nextLine();
			String uid = scanner.nextLine();
			scanner.nextLine();
			Identity id = new Identity(uid, email, displayName);
			// before to add the "id" into the list, lets check that it is
			// corresponding to the given criteria

			if (email.equals(criteria.getEmail())
					|| displayName.equals(criteria.getDisplayName())) {
				//it is matching, add the found identity in the resultlist
				resultsList.add(id);
			}
		}
		//TODO find a clever way to reset the scanner
		scanner.close();
		scanner = new Scanner(new File(path));
		
		
		return resultsList;
	}

	private Identity getById(String searchedUid){
		
		Identity resultIdentity = null;
		while (scanner.hasNext()) {
			scanner.nextLine();
			String displayName = scanner.nextLine();
			String email = scanner.nextLine();
			String uid = scanner.nextLine();
			scanner.nextLine();
			Identity id = new Identity(uid, email, displayName);
			// before to add the "id" into the list, lets check that it is
			// corresponding to the given criteria
			if (searchedUid.equals(uid)) {
				//it is matching, add the found identity in the resultlist
				resultIdentity = id;
			}
		}
		return resultIdentity;
	}
	
	public void update(Identity identity) throws Exception{
		//first, search the passed identity in the file
		Identity foundIndentity = getById(identity.getUid());
		
		//create an other file to save the new content
		File file = new File(path+"-new");
		PrintWriter newPrinter = new PrintWriter(file);
		
		while(scanner.hasNext()){
			scanner.nextLine();
			String displayName = scanner.nextLine();
			String email = scanner.nextLine();
			String uid = scanner.nextLine();
			scanner.nextLine();
			if (!identity.getUid().equals((uid))){
				newPrinter.println("--- identity:begin ---");
				newPrinter.println(displayName);
				newPrinter.println(email);
				newPrinter.println(uid);
				newPrinter.println("--- identity:end ---");
				newPrinter.flush();
			}
		}
		newPrinter.println("--- identity:begin ---");
		newPrinter.println(identity.getDisplayName());
		newPrinter.println(identity.getEmail());
		newPrinter.println(identity.getUid());
		newPrinter.println("--- identity:end ---");
		writer.close();
		newPrinter.close();
		scanner.close();
		File oldFile = new File(path);
		Files.delete(oldFile.toPath());
		Files.move(file.toPath(), oldFile.toPath());
		initIO();
	}
	
	
	/**
	 * This method will check that the file exists or create it if it doesn't
	 * 
	 * @param pathname
	 * @throws IOException
	 */
	private void initIO() throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			// creation code after
			System.out.println("the file does not exists");
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
			System.out.println("file was successfully created");
		} else {
			System.out.println("the file already exists");
		}
		this.scanner = new Scanner(file);
		// open the writer
		this.writer = new PrintWriter(new FileOutputStream(file, true));
	}

	public void close() {
		this.scanner.close();
		this.writer.close();
	}

}
