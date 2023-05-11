<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	color: black;
}

body {
	background-image: url('https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_1944,h_1024/https://h-o-m-e.org/wp-content/uploads/2023/01/carbone-nyc-reservations-1-1.jpg');
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
    <form method="POST" action="ModificarReserva">
      <h1 class="fw-bold">Modificar Reserva</h1>
      <c:if test="${error eq true}">
    						<div class="alert alert-danger" role="alert">
        					Has introducido la reserva incorrectamente!
    						</div>
	  </c:if>
      <p class="fw-bold">Nº Reserva:
        <input type="hidden" name="nReserva"  value="${reserva.nReserva}"required>${reserva.nReserva}
        <br>
      </p>
      <p class="fw-bold">Fecha:
        <input type="date" name="fecha"  value="${reserva.fecha}" required>
      </p>
      <p class="fw-bold">DNI:
        <input type="hidden" name="dni"  value="${reserva.cliente.dni}" required>${reserva.cliente.dni}
      </p>
      <p class="fw-bold">Nombre:
        <input type="text" name="nombre"  value="${reserva.cliente.nombre}" required>
      </p>
      <p class="fw-bold">Telefono:
        <input type="text" name="telefono" value="${reserva.cliente.telefono}" required>
      </p>
      <p class="fw-bold">Eventos:
        <select name="evento" required>
          <option value="" ></option>
          <c:forEach items="${eventos}" var="evento">
            <c:if test="${evento.cEvento == reserva.evento.cEvento}">
              <option value="${evento.cEvento}" selected> ${evento.nombre} </option>
            </c:if>
            <c:if test="${evento.cEvento != reserva.evento.cEvento}">
              <option value="${evento.cEvento}"> ${evento.nombre} </option>
            </c:if>
          </c:forEach>
        </select> 
      </p>
      <input type="submit" class="btn btn-secondary" value="Modificar">
      	<a href="PaginaReservaUsuario" class="btn btn-dark">Volver </a>
		</form>
	
	</div>
</div>
</body>
</html>