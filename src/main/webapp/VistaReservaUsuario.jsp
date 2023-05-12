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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	color: black;
}

.form-container {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}

.container {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	color: black;
}

body {
	background-image:
		url('https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_1944,h_1024/https://h-o-m-e.org/wp-content/uploads/2023/01/carbone-nyc-reservations-1-1.jpg');
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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse " id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="PaginaUsuario">GESTIONAR USUARIOS</a></li>

					<li class="nav-item"><a class="nav-link active"
						href="PaginaEvento" tabindex="-1" aria-disabled="true">GESTIONAR
							EVENTOS</a></li>

					<li class="nav-item"><a class="nav-link active"
						href="PaginaReservaUsuario">GESTIONAR RESERVAS</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="PaginaProductos">GESTIONAR PRODUCTOS</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="PaginaPlato">GESTIONAR PLATOS</a></li>
				</ul>

				<div class="nav-item dropdown" style="margin-left: 240px;">
					<a href="#" data-toggle="dropdown"
						class="nav-item nav-link dropdown-toggle user-action"
						style="color: white;"> ${sessionScope.usuarioLogueado.nombre}
						${sessionScope.usuarioLogueado.apellido}<b class="caret"></b>
					</a>
					<div class="dropdown-menu">
						<a
							href="VerUsuario?cUsuario=${sessionScope.usuarioLogueado.cUsuario}"
							class="dropdown-item"><i class="fa fa-user-o"></i> Perfil</a> <a
							href="ModificarUsuario?cUsuario=${sessionScope.usuarioLogueado.cUsuario}"
							class="dropdown-item"><i class="fa  fa-pencil "></i> Editar
							Perfil</a>
						<div class="divider dropdown-divider"></div>
						<a href="CerrarSesion" class="dropdown-item"><i
							class="material-icons">&#xE8AC;</i> Cerrar Sesion</a>
					</div>
				</div>

			</div>
		</div>
	</nav>
	<div class="container">
		<div class="form-container">

			<h1 class="fw-bold">GESTIONES RESERVAS</h1>

			<h1 class="fw-bold">Ver Reservas</h1>

			<form method="get" action="VerReservasUsuario">
				<p>
					DNI: <input type="text" name="DNI" value="${reserva.cliente.dni}" />
					<input type="submit" class="btn-primary" value="Buscar">
				</p>
			</form>
			<table class="table">

				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">nReserva</th>
						<th scope="col">Fecha</th>
						<th scope="col">Evento</th>
						<th scope="col"></th>
						<th scope="col"></th>


					</tr>
				</thead>
				<tbody>

					<c:forEach items="${reservas}" var="reserva">
						<tr>
							<th scope="row"></th>
							<td>${reserva.nReserva}</td>
							<td>${reserva.fecha}</td>
							<td>${reserva.evento.nombre}</td>
							<c:if test="${sessionScope.usuarioLogueado.rol.id == 1}">
								<td><a href="ModificarReserva?nReserva=${reserva.nReserva}"
									class="btn btn-secondary">modificar</a></td>
								<td><a href="EliminarReserva?nReserva=${reserva.nReserva}"
									class="btn btn-danger">eliminar</a></td>

							</c:if>
						</tr>

					</c:forEach>


				</tbody>
			</table>

		</div>
	</div>
</body>
</html>