<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	color: black;
}

body {
	background-image: url('https://wallpapercave.com/wp/wp8645275.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
}
.form-container {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
	background-color: white;
	
}
</style>
</head>

<body>
	<div class="container">
	<div class="form-container">
		<h2 class="fw-bold">${producto.nombre }</h2>

		<p class="fw-bold">
			<b>CALORIAS:</b>${producto.calorias } KCAL
		</p>
		<p class="fw-bold">
			<b>PROTEINAS:</b>${producto.proteinas} G
		</p>

		<a href="VerProductos" class="btn btn-dark">Volver</a>
		</div>
		</div>
</body>
</html>