<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</head>
<style>
body {
	background: url('https://wallpaperaccess.com/full/7066812.jpg');
}

.form-control:focus {
	box-shadow: none;
	border-color: #BA68C8
}

.profile-button {
	background: rgb(99, 39, 120);
	box-shadow: none;
	border: none
}

.profile-button:hover {
	background: #682773
}

.profile-button:focus {
	background: #682773;
	box-shadow: none
}

.profile-button:active {
	background: #682773;
	box-shadow: none
}

.back:hover {
	color: #682773;
	cursor: pointer
}

.labels {
	font-size: 11px
}

.add-experience:hover {
	background: #BA68C8;
	color: #fff;
	cursor: pointer;
	border: solid 1px #BA68C8
}
</style>

<body>
	<div class="container rounded bg-white mt-5 mb-5" style="width: 45%;">
		<div class="row">
			<div class="col-md-6 border-right">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img class="rounded-circle mt-5" width="150px"
						src="https://cdn.icon-icons.com/icons2/3199/PNG/512/circle_user_icon_195462.png">
					<br>
					<h3 class="font-weight-bold">${usuario.nombre}</h3> 
					<h5 class="text-black-50">${usuario.correoTrabajo}</h5>
				</div>
			</div>
			<div class="col-md-6 border-right">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h1 class="fw-bold">Perfil de Usuario</h1>
					</div>
					<div class="row mt-2">
						cUsuario:
						<h4 class="fw-bold">${usuario.cUsuario}</h4>
					</div>
					<div class="row mt-2">
						Nombre:
						<h4 class="fw-bold">${usuario.nombre}</h4>
						Apellido:
						<h4 class="fw-bold">${usuario.apellido}</h4>
					</div>
					<div class="row mt-3">
						Teléfono:
						<h4 class="fw-bold">${usuario.telefono}</h4>
						Correo Trabajo:
						<h4 class="fw-bold">${usuario.correoTrabajo}</h4>
						Trabajo:
						<h4 class="fw-bold">${usuario.rol.nombre}</h4>
					</div>
					<a href="VerUsuarios" class="btn btn-dark mt-5"
						style="margin-left: 90px;" onclick="history.go(-1);return false;">Volver
					</a>
				</div>

			</div>

		</div>
	</div>

</body>
</html>