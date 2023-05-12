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
			<form method="POST" action="ModificarUsuario">
				<h1 class="fw-bold">Modificar Usuario</h1>
				<c:if test="${error eq true}">
    						<div class="alert alert-danger" role="alert">
        					Has introducido el usuario incorrectamente!
    						</div>
				</c:if>
				<p class="fw-bold">
					cUsuario: ${usuario.cUsuario} <input type="hidden" name="cUsuario"
						value="${usuario.cUsuario}" /> <br>
				</p>

				<p class="fw-bold">
					Nombre: <input type="text" name="nombre" value="${usuario.nombre}" required />
					<br>
				</p>
				<br>
				<p class="fw-bold">
					Apellido: <input type="text" name="apellido"
						value="${usuario.apellido}" required /> <br>
				</p>
				<p class="fw-bold">
					Contraseña: <input type="text" name="contrasena"
						value="${usuario.contrasena}" required /> <br>
				</p>
				<br>
				<p class="fw-bold">
					Telefono: <input type="text" name="telefono"
						value="${usuario.telefono}" required /> <br>
				</p>
				<br>
				<p class="fw-bold">
					CorreoTrabajo: <input type="email" name="correoTrabajo"
						value="${usuario.correoTrabajo}" required  /> <br>
				</p>
				<br>
				
				<p> <c:if test="${sessionScope.usuarioLogueado.rol.id == 1}">
    							
					Trabajos: <select name="rol" required >
						<option value="0"></option>
						<c:forEach items="${roles}" var="rol">
							<c:if test="${rol.id == usuario.rol.id}">
								<option value="${rol.id}" selected>${rol.nombre}</option>
							</c:if>
							<c:if test="${rol.id != usuario.rol.id}">
								<option value="${rol.id}">${rol.nombre}</option>
							</c:if>
						</c:forEach>
					</select>
					
					 </c:if>
				</p>
				<br> <input type="submit" class="btn btn-primary"  value="Enviar" /> 
					<a href="VerUsuarios" class="btn btn-dark" onclick="history.go(-1);return false;" >Volver </a>
			</form>
		</div>
	</div>







</body>
</html>