<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Kameras</title>     
  </head>
  <body>
  	<table border="1">
  		<tbody>
	  		<tr>
	  			<td>Id</td>				
				<td>Name</td>
				<td>URL</td>
				<td>Status</td>
			</tr>			
			<c:forEach var="cam" items="${cams}">
				<tr>
					<td><c:out value="${cam.id}"/></td>					
					<td><c:out value="${cam.name}"/></td>
					<td><c:out value="${cam.url}"/></td>
					<td><c:out value="${cam.status}" /></td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  	<!-- <a href="edit?action=add">Neue Cam hinzufügen</a> -->
  </body>
</html>
