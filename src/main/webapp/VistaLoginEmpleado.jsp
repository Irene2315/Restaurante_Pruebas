<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<style type="text/css">
.login-card {
	width: 300px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #e8e8e8;
	box-shadow: 2px 2px 10px #ccc;
	margin-top: 10px;
}

.card-header {
	text-align: center;
	margin-bottom: 20px
}

.card-header .log {
	margin: 0;
	font-size: 24px;
	color: black;
}

.form-group {
	color: black;
	margin-bottom: 15px;
}

label {
	font-size: 18px;
	margin-bottom: 5px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 12px 20px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	transition: 0.5s;
}

input[type="submit"] {
	width: 100%;
	background-color: #333;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #ccc;
	color: black;
}
</style>
</head>
<body>

	<div class="login-card">
		<div class="card-header">
			<div class="log">Iniciar Sesion</div>

		</div>
		<form method="POST">
			<div class="form-group">
				<label for="username">CºUsuario:</label> <input required=""
					name="cUsuario" id="username" type="text">
			</div>
			<div class="form-group">
				<label for="password">Contraseña:</label> <input required=""
					name="contrasena" id="password" type="password">
			</div>
			<div class="form-group">
				<input type="submit" value="Enviar" class="btn btn-primary">
			</div>
			<div class="form-group">
				<a href="PaginaInicialCliente.jsp" class="btn btn-secondary"
					onclick="history.back()">Volver</a>
			</div>
		</form>
	</div>


</body>
</html>