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
  </head>
  <body>
 <form name="form1">
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
  	
  	<br>
  </body>
</html>
