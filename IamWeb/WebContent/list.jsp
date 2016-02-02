<%@page import="fr.tbr.iamcore.datamodel.Identity"%>
<%@page import="java.util.List"%>
<%@page import="fr.tbr.iamcore.dao.IdentityJdbcDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identities list</title>
<%
	IdentityJdbcDAO dao = new IdentityJdbcDAO();
	List<Identity> list = dao.readAll();
%>
</head>
<body>

<table>
	<thead>
		<tr>
			<th>Display Name</th>
			
		</tr>
	</thead>
	<tbody>
		<%for (Identity identity : list){ %>
		<tr>
			<td><%=identity.getDisplayName() %></td>
		</tr>
		<%} %>
	</tbody>

</table>


</body>
</html>