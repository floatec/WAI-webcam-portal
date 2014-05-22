<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Benutzer bearbeiten</title>
</head>
<body>
  <body>
	<form name="edit" action="userEdit" method="post">		
		<table border="1">
			<tbody>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" value="${user.username}" required></td>		
				</tr>
				<tr>		
					<td>Passwort</td>	
					<td><input type="password" name="password" value="" ></td>
				</tr>			
				<tr>	
					<td colspan="2"><input type="submit" name="btnSave" value="Save"></td>
				</tr>				
			</tbody>
		</table>
		<input type="hidden" name="id" value="${user.id}">
	</form>
  </body>
</html>