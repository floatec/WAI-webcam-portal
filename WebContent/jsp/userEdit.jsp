<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <!-- JQuery -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>  
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<title>Benutzer bearbeiten</title>
</head>
<body>
  <body>
	<form name="edit" action="userEdit" method="post">		
		<table border="0">
			<tbody>
				<tr>
					<td>Username:</td>
					<td><input type="text" class="form-control" name="username" value="${user.username}" required></td>		
				</tr>
				<tr>		
					<td>Passwort:</td>	
					<td><input type="password" class="form-control" name="password" value="" ></td>
				</tr>			
				<tr>	
					<td>&nbsp;</td><td colspan="1"><input class="btn btn-success" type="submit" name="btnSave" value="Save"></td>
				</tr>				
			</tbody>
		</table>
		<input type="hidden" name="id" value="${user.id}">
	</form>
  </body>
</html>