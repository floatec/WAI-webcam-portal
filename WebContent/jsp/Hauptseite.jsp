<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hauptseite</title>
</head>
<body>
  <h1>Willkommen <%= request.getParameter("loginName") %></h1>
 <!-- <form method="post" name="Login" action="/">
Standort auswählen<br />
<input type="text" name="stantOrt" /><br />
Kamera auswählen<br />
<input type="password" name="kamera" /><br />
<input type="submit" value="Bestätigen" />
<input type="reset" value="reset" /> -->

<form method="post" action="Hauptseite.jsp">
Standort auswählen<br />
<select name="Standort">
<option value="Alto">Alto</option>
<option value="Esteem">Esteem</option>
<option value="Honda City">Honda City</option>
<option value="Chevrolet">Chevrolet</option>
</select>
<br/>
Kamera auswählen<br />

<select name="Kamera">
<option value="Alto">Alto</option>
<option value="Esteem">Esteem</option>
<option value="Honda City">Honda City</option>
<option value="Chevrolet">Chevrolet</option>
</select>
<br>
<input type="Submit" value="OK">
<hr>
<%
String st=request.getParameter("sel");
if(st!=null){
    out.println("You have selected: "+st);
}
%>
 <h2>Das aktuelle Bild:</h2>
 <table>
<tr><td>BILD ANZEIGE</td></tr>
</table>
</form>
</body>
</html>