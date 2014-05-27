<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Gruppen</title>     
  </head>
  <body>
  	<table border="1">
  		<tbody>
	  		<tr>	
				<td>Gruppe</td>
				<td>Aktion</td>
			</tr>			
			<c:forEach var="group" items="${groups}">
				<tr>			
					<td><c:out value="${group.name}"/></td>
					<td>
						<a href="groupEdit?action=groupEdit&id=${group.id}">Bearbeiten</a>
					</td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
