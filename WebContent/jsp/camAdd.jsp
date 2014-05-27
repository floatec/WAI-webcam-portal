<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>     
    <title>Kamera hinzufügen</title>
        <!-- JQuery -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>  
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
  </head>
  <body>
	<form name="edit" action="camEdit" method="post">
		<table border="0">
			<tbody>
				<tr>
					<td>Name:</td>
					<td><input class="form-control" type="text" name="name" value=""></td>		
				</tr>
				<tr>		
					<td>URL:</td>	
					<td><input class="form-control" type="text" name="url" value=""></td>
				</tr>		
				<tr>		
					<td>Aufnahme: </td>	
						<td><input class="checkbox" type="checkbox" name="status" value="status" checked></td>			
				</tr>		
				<tr>	
					<td colspan="2"><input  class="btn btn-success" type="submit" name="btnSave" value="Save"></td>
				</tr>				
			</tbody>
		</table>
	</form>
  </body>
</html>
