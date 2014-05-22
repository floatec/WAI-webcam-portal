<%@ page language="java" contentType="text/html" %>
<%@ page import="model.Cam" %>
<%@ page import="model.Picture" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>${cam.name}</title>    
  </head>
  <body>
 <form name="form1">
  <input value="${date}" id="date" name="date"><input type="button" value="aktualisieren" id="refrash">
<select name="menu1" id="menu1">
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
 
 var refrash = document.getElementById( 'refrash' );
 var date = document.getElementById( 'date' )
 refrash.onClick = function() {
	 window.location.href = (  '?cam=${cam.id}&date='+date.value );
 };
</script><br><
  <h1>${cam.name}</h1>	
			<c:forEach var="picture" items="${pictures}">
				<img src="img/${picture.path}"/>
			</c:forEach>	
  	
  	<br>
  </body>
</html>
