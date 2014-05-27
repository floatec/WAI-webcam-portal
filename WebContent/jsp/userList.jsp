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
    <title>Benutzer</title>     
  </head>
  <body>
  	<a class="btn btn-default" href="userEdit?action=userAdd">+ Neuen Benutzer hizufügen</a>
  	<br />
  	<br />
  	<table class="table">
  		<tbody>
	  		<tr>	
				<td>Username</td>
				<td>Aktion</td>
			</tr>			
			<c:forEach var="user" items="${users}">
				<tr>			
					<td><c:out value="${user.username}"/></td>
					<td>
						<a class="btn btn-default" href="userEdit?action=userEdit&id=${user.id}">Bearbeiten</a>
						<a class="btn btn-default" href="userEdit?action=userDelete&id=${user.id}">Löschen</a>
					</td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
