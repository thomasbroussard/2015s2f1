/**
 * 
 */
package fr.tbr.iamcore.tests.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import fr.tbr.iamcore.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public class TestJdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		//testJdbcConnection();

		Connection connection = getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = prepareStatement.executeQuery();
		while (rs.next()){
			String displayName = rs.getString("DISPLAY_NAME");
			String email = rs.getString("EMAIL_ADDRESS");
			Date date = rs.getDate("BIRTHDATE");
			String uid = rs.getString("UID");
			
			Identity identity = new Identity(uid, email, displayName);
			identity.setBirthDate(date);
			System.out.println(identity);
		}
		
		connection.close();
		
		
	}

	private static void testJdbcConnection() {
		try {
			Connection connection = getConnection();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/IAMDataBase;create=true", "tom", "tom");
		System.out.println(connection.getSchema());
		return connection;
	}

}
