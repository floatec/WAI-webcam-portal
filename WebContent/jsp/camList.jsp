<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
  <!-- JQuery -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>  
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>     
    <title>Kameras</title>     
  </head>
  <body>
  	<a href="camEdit?action=camAdd">+ Neue Kamera hizufügen</a>
  	<br />
  	<br />
  	<table class="table">
  		<tbody>
	  		<tr>	
				<td>Name</td>
				<td>URL</td>
				<td>Aufnahme</td>
				<td>Aktion</td>
			</tr>			
			<c:forEach var="cam" items="${cams}">
				<tr <c:if test="${cam.status}">class="success"</c:if><c:if test="${!cam.status}">class="danger"</c:if>>			
					<td><c:out value="${cam.name}"/></td>
					<td><c:out value="${cam.url}"/></td>
					<td>
						<a  href="camEdit?action=toggleStatus&id=${cam.id}&status=${cam.status}">
							<c:if test="${cam.status}">Aktiv</c:if>
							<c:if test="${!cam.status}">Inaktiv</c:if>
						</a>	
					</td>
					<td><a href="camEdit?action=camEdit&id=${cam.id}">Bearbeiten</a></td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
