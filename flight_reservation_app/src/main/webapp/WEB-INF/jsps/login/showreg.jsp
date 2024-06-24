<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h2>Register Here</h2>
	<div class="container"> 
	${msg}
	</div>
	<form action="saveReg" method="post">
		<pre>
		first name<input type="text" name="firstName" />
		last name<input type="text" name="lastName" />
		email<input type="text" name="email" />
		password<input type="text" name="password" />
		<input type="submit" value="save" />
	</pre>
	</form>

</body>
</html>