<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Cam Portal</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li ><a href="pictureList">Bilder</a></li>
        <li class="active"><a href="userList">Benutzer</a></li>
         <li><a href="camList">Kameras</a></li>
         <li><a href="groupList">Gruppen</a></li>
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <li><a href="logout">Logout</a></li>
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	<form name="edit" action="userEdit" method="post">
		<table border="0">
			<tbody>
				<tr>
					<td>Username:</td>
					<td><input class="form-control" type="text" name="username" value="" required></td>		
				</tr>
				<tr>		
					<td>Passwort:</td>	
					<td><input class="form-control" type="password" name="password" value="" required></td>
				</tr>			
				<tr>	
					<td colspan="2"><input  class="btn btn-success" type="submit" name="btnSave" value="Save"></td>
				</tr>
				<tr>	
					<td></td>
					<td>
						<select name="cams" multiple>
							<c:forEach var="cam" items="${cams}">			
					 			<option <c:if test="${cam.access!=0}">selected</c:if> value="${cam.camid}">
					 				<c:out value="${cam.name}" />
					 			</option>
							</c:forEach>
						</select>
					</td>
				</tr>					
			</tbody>
		</table>
	</form>
  </body>
</html>
