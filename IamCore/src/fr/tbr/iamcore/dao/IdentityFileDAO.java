package fr.tbr.iamcore.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
		writer.println("--- identity:begin ---");
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

		scanner.reset();
		return result;
	}

	public List<Identity> search(Identity criteria) {
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
		scanner.reset();
		
		
		return resultsList;
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
