<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Benutzer</title>     
  </head>
  <body>
  	<a href="userEdit?action=userAdd">+ Neuen Benutzer hizufügen</a>
  	<br />
  	<br />
  	<table border="1">
  		<tbody>
	  		<tr>	
				<td>Username</td>
				<td>Aktion</td>
			</tr>			
			<c:forEach var="user" items="${users}">
				<tr>			
					<td><c:out value="${user.username}"/></td>
					<td>
						<a href="userEdit?action=userEdit&id=${user.id}">Bearbeiten</a>
						<a href="userEdit?action=userDelete&id=${user.id}">Löschen</a>
					</td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
