<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
 
<p>Zum Login bitte Benutzername und Passwort eingeben:<br /><p>
<hr />
 
<form method="post" name="Login" action="LoginCheck">
Benutzername<br />
<input type="text" name="loginName" /><br />
Passwort<br />
<input type="password" name="password" /><br />
<input type="submit" value="login" />
<input type="reset" value="reset" />
</form>
 
</body>
</html>