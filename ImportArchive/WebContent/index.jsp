<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload</title>
</head>
<body>
	<form action="UploadFileServlet" enctype="multipart/form-data" method="post">
	    <label for="file">1º Passo: </label> <input type="file" name="file" id="file"><br/>
	    <br />
	    <label for="file">2º Passo: </label> <input type="submit" value="Enviar"/>
	</form>
</body>
</html>