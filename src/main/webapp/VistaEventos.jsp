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
	margin-top:10px;
	color: black;
}

body {
	background-image: url('https://wallpaperaccess.com/full/3014601.jpg');
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
				<form class="d-flex">
					<a href="CerrarSesion" class="btn btn-danger">Cerrar Sesion </a>
				</form>

			</div>
		</div>
	</nav>
	<div class="container">
		<div class="form-container">
			<h1 class="fw-bold">GESTIONES EVENTOS</h1>
			<div class="row 2">

				<c:if test="${sessionScope.usuarioLogueado.rol.id == 1}">
					<div class="col-6">
						<h1 class="fw-bold">Insertar Evento</h1>

						<form method="POST" action="InsertarEvento">


							<p>
								Nombre: <input type="text" name="nombre" required/> <br>
							</p>
							<br> <input type="submit" class="btn btn-secondary"
								value="Insertar" />



						</form>

					</div>
				</c:if>

				<div class="col-6">

					<h1 class="fw-bold">Ver Eventos</h1>



					<form method="get" action="VerEventos">

						<table class="table">

							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">cEvento</th>
									<th scope="col">nombre</th>
									<th scope="col"></th>
									<th scope="col"></th>



								</tr>
							</thead>
							<tbody>

								<c:forEach items="${eventos}" var="evento">
									<tr>
										<th scope="row"></th>
										<td>${evento.cEvento}</td>
										<td>${evento.nombre}</td>
										<c:if test="${sessionScope.usuarioLogueado.rol.id == 1}">
											<td><a href="EliminarEvento?c_evento=${evento.cEvento}"
												class="btn btn-danger">eliminar</a></td>

										</c:if>


									</tr>

								</c:forEach>


							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>