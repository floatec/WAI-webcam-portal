<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ page import="model.Picture" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>${cam.name}</title>  
    <!-- JQuery -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>  
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<!--  datepicker -->
<script src="js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="css/datepicker.css">
<style type="text/css">
.form-control {
width:300px;
   display: inline-block;
    
}
</style>
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
        <li class="active"><a href="pictureList">Bilder</a></li>
        <li><a href="userList">Benutzer</a></li>
         <li><a href="camList">Kameras</a></li>
         <li><a href="groupList">Gruppen</a></li>
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <li><a href="logout">Logout</a></li>
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
 <div style="text-align:center">   
 <input type="text" type="text" class="form-control" id="datepick" value="${date}" onchange="">
 

  
<select class="form-control" name="menu1" id="menu1">
<option value="/">Kamera wählen</option>
<c:forEach var="curcam" items="${cams}">
				<option value="?date=${date}&cam=${curcam.id}">${curcam.name}</option>
			</c:forEach>	


</select> 
<script type="text/javascript">
 var urlmenu = document.getElementById( 'menu1' );
 urlmenu.onchange = function() {
	 window.location.href = (  this.options[ this.selectedIndex ].value );
 };
 
 
 
 function loadDate() {
	 var date = document.getElementById( 'datepick');
	 window.location.href = (  '?cam=${cam.id}&date='+date.value );
 };
 $('#datepick').datepicker({
	    format: "yyyy-mm-dd"
	}).on('changeDate', loadDate);
</script><br>
	
	  <h1>${cam.name}</h1>
			<c:forEach var="picture" items="${pictures}">
				<a href="img/${picture.path}" target="_BLANK"><img src="img/${picture.getPathSmall()}"/></a>
			</c:forEach>	
  	</div>
  	<br>
  </body>
</html>
