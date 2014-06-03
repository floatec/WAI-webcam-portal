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
					<c:if test="${user.id != 1 || loggedInUser.id == 1}">
						<td><c:out value="${user.username}"/></td>
						<td>
							<a class="btn btn-default" href="userEdit?action=userEdit&id=${user.id}">Bearbeiten</a>
							<c:if test="${user.id != 1}">
								<a class="btn btn-default" href="userEdit?action=userDelete&id=${user.id}">Löschen</a>
							</c:if>
						</td>
						</c:if>
					</td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
