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
        <li><a href="userList">Benutzer</a></li>
         <li class="active"><a href="camList">Kameras</a></li>
         <li><a href="groupList">Gruppen</a></li>
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <li><a href="logout">Logout</a></li>
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
  	<a class="btn btn-default" href="camEdit?action=camAdd">+ Neue Kamera hizuf�gen</a>
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
						<a class="btn btn-default" href="camEdit?action=toggleStatus&id=${cam.id}&status=${cam.status}">
							<c:if test="${cam.status}">Aktiv</c:if>
							<c:if test="${!cam.status}">Inaktiv</c:if>
						</a>	
					</td>
					<td><a class="btn btn-default" href="camEdit?action=camEdit&id=${cam.id}">Bearbeiten</a></td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  </body>
</html>
