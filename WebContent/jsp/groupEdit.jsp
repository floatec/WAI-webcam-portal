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
		<table>
			<tr>	
				<td></td>
				<td>
					<select name="users" multiple>
						<c:forEach var="user" items="${groups}">			
				 			<option <c:if test="${user.access!=0}">selected</c:if> value="${user.userid}">
				 				<c:out value="${user.name}" />
				 			</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td><td colspan="1"><input class="btn btn-success" type="submit" name="btnSave" value="Save"></td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${group}">
	</form>
  </body>
</html>
