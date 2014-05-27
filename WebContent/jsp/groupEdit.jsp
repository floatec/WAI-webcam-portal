<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>User in Gruppe</title>
  </head>  
  <body>
	<form name="edit" action="groupEdit" method="post">		
		<p> User die zur Gruppe hinzugefügt werden sollen bitte auswählen<br>Für Mehrfachauswahl strg gedrückt halten... <p>
		<select multiple>
		  <option value="$">Volvo</option>
		</select> 
		<input type="hidden" name="id" value="${group.id}">
	</form>
  </body>
</html>
