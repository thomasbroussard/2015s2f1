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
import fr.tbr.iamcore.services.match.Matcher;
import fr.tbr.iamcore.services.match.impl.StartsWithIdentityMatcher;

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

	private Matcher<Identity> activeMatchingStrategy = new StartsWithIdentityMatcher();
	
	public IdentityFileDAO() throws Exception {
		initIO();
	}

	/**
	 * 
	 * @param identity
	 */
	public void create(Identity identity) {
		writeIdentity(identity, writer);
	}

	private void writeIdentity(Identity identity, PrintWriter printWriter) {
		printWriter.println("--- identity:begin ---");
		printWriter.println(identity.getDisplayName());
		printWriter.println(identity.getEmail());
		printWriter.println(identity.getUid());
		printWriter.println("--- identity:end---");
		printWriter.flush();
	}

	/**
	 * 
	 * @return
	 */
	public List<Identity> readAll() {
		List<Identity> result = new ArrayList<Identity>();
		while (scanner.hasNext()) {
			result.add(readIdentity(scanner));
		}

		try {
			resetScanner(scanner);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Identity> search(Identity criteria)
			throws FileNotFoundException {
		List<Identity> resultsList = new ArrayList<Identity>();
		while (scanner.hasNext()) {
			Identity id = readIdentity(scanner);
			// before to add the "id" into the list, lets check that it is
			// corresponding to the given criteria
			if (activeMatchingStrategy.match(criteria, id)){
				// it is matching, add the found identity in the resultlist
				resultsList.add(id);
			}
		}

		resetScanner(scanner);
		return resultsList;
	}

	private void resetScanner(Scanner scannerInstance)
			throws FileNotFoundException {
		scannerInstance.close();
		scannerInstance = new Scanner(new File(path));
	}

	private Identity readIdentity(Scanner scannerInstance) {
		scannerInstance.nextLine();
		String displayName = scannerInstance.nextLine();
		String email = scannerInstance.nextLine();
		String uid = scannerInstance.nextLine();
		scannerInstance.nextLine();
		return new Identity(uid, email, displayName);
	}

	private Identity getById(String searchedUid) {

		Identity resultIdentity = null;
		while (scanner.hasNext()) {
			Identity id = readIdentity(scanner);
			// before to add the "id" into the list, lets check that it is
			// corresponding to the given criteria
			if (searchedUid.equals(id.getUid())) {
				// it is matching, add the found identity in the resultlist
				resultIdentity = id;
			}
		}
		return resultIdentity;
	}

	public void update(Identity identity) throws Exception {

		// create an other file to save the new content
		File file = new File(path + "-new");
		PrintWriter newPrinter = new PrintWriter(file);

		while (scanner.hasNext()) {
			Identity id = readIdentity(scanner);
			if (!identity.getUid().equals((id.getUid()))) {
				writeIdentity(id, newPrinter);
			}
		}
		writeIdentity(identity, newPrinter);
		newPrinter.close();
		close();
		replace(new File(path), file);
		initIO();
	}

	private void replace(File oldFile, File newFile) throws IOException {
		Files.delete(oldFile.toPath());
		Files.move(newFile.toPath(), oldFile.toPath());
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
