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

.form-container {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}
body {
	background-image: url('https://foodandtravel.mx/wp-content/uploads/2023/04/Comida-popular-Por.jpg');
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

			<form method="POST" action="RegistrarPlato">
				<div class="row 2">
					<div class="col-6">
						<h1 class="fw-bold">Resgistrar Plato</h1>
						 <c:if test="${error eq true}">
    						<div class="alert alert-danger" role="alert">
        					Has introducido el plato incorrectamente!
    						</div>
	 					 </c:if>
						<p>
						
							Nombre: <input type="text" name="nombre" required/> <br>
						</p>
						<br>
						<p>
							Precio: <input type="text" name="precio" required/> <br>
						</p>
						<input type="submit" class="btn btn-secondary" value="Guardar" />
						<a href="PaginaPlato" class="btn btn-dark">Volver </a>
					</div>


					<div class="col-6">
						<h1 class="fw-bold">PRODUCTOS</h1>
						<c:if test="${productosLleno eq false}">
    						<div class="alert alert-danger" role="alert">
        					Debes selecionar al menos un producto!
    						</div>
	 					 </c:if>
						<table class="table">

							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">c_producto</th>
									<th scope="col">nombre</th>
									<th></th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${productos}" var="producto">
									<tr>
										<th scope="row"></th>
										<td>${producto.cProducto}</td>
										<td>${producto.nombre}</td>

										<td><input class="form-check-input" type="checkbox"
											id="flexCheckDefault" value="${producto.cProducto}"
											name="${producto.cProducto}"></td>


									</tr>

								</c:forEach>




							</tbody>
						</table>

					</div>
				</div>

			</form>
		</div>
	</div>

</body>
</html>







