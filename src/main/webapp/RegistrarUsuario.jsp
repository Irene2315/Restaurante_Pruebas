<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  page import="java.sql.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	background-image: url('https://wallpaperaccess.com/full/7066812.jpg');
	background-repeat: no-repeat;
	background-size: cover;
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
			<h1 class="fw-bold">Registrar Usuario</h1>
			<c:if test="${error eq true}">
    						<div class="alert alert-danger" role="alert">
        					Has introducido el usuario incorrectamente!
    						</div>
				</c:if>
			<form method="POST" action="InsertarUsuario">

				<p>
					Nombre: <input type="text" name="nombre" required /> <br>
				</p>
				<br>
				<p>
					Apellido: <input type="text" name="apellido" required /> <br>
				</p>
				<br>
				<p>
					Contraseña: <input type="text" name="contrasena" required/> <br>
				</p>
				<br>
				<p>
					Telefono: <input type="text" name="telefono" required/> <br>
				</p>
				<br>
				<p>
					correoTrabajo: <input type="email" name="correoTrabajo" required /> <br>
				</p>
				<br> Roles:<select name="rol">
					<option value="0"></option>
					<c:forEach items="${roles}" var="rol">
						<option value="${ rol.id}">${rol.nombre}</option>
					</c:forEach>
				</select> <br> <br> <input type="submit" class="btn btn-secondary"
					value="Guardar" />
					<a href="VerUsuarios" class="btn btn-dark">Volver</a>
			</form>
			
			</div>
			</div>
</body>
</html>