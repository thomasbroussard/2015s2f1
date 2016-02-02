package fr.tbr.iam.iamweb.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tbr.iamcore.dao.IdentityJdbcDAO;
import fr.tbr.iamcore.datamodel.Identity;

/**
 * Servlet implementation class Create
 */
@WebServlet(description = "The main servlet for Identity Creation", urlPatterns = { "/Create" })
public final class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Create() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("got a Post request");
		Map<String, String[]> parameterMap = request.getParameterMap();
		Identity identity = new Identity();
		
		identity.setDisplayName(extractParameterValue(parameterMap, "displayName"));
		identity.setEmail(extractParameterValue(parameterMap, "emailAddress"));
		String dateAsString = extractParameterValue(parameterMap, "birthDate");
		try{
			
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateAsString);
			identity.setBirthDate(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		IdentityJdbcDAO dao = new IdentityJdbcDAO();
		dao.create(identity);
		dao.close();
		
	}

	private String extractParameterValue(Map<String, String[]> parameterMap, String parameterName) {
		String parameterValue = null;
		String[] values = parameterMap.get(parameterName);
		if (values != null){
			parameterValue = values[0];
		}
		return parameterValue;
	}

}
