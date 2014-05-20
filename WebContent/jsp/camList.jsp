<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Kameras</title>     
  </head>
  <body>
  	<a href="edit?action=camAdd">+ Neue Kamera hizufügen</a>
  	<br />
  	<br />
  	<table border="1">
  		<tbody>
	  		<tr>	
				<td>Name</td>
				<td>URL</td>
				<td>Aufnahme</td>
				<td>Aktion</td>
			</tr>			
			<c:forEach var="cam" items="${cams}">
				<tr>			
					<td><c:out value="${cam.name}"/></td>
					<td><c:out value="${cam.url}"/></td>
					<td>
						<a href="edit?action=toggleStatus&id=${cam.id}&status=${cam.status}">
							<c:if test="${cam.status}">Aktiv</c:if>
							<c:if test="${!cam.status}">Inaktiv</c:if>
						</a>	
					</td>
					<td><a href="edit?action=camEdit&id=${cam.id}">Bearbeiten</a></td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
