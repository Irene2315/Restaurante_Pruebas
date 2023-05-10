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

</head>
<body style="
  background-image: url('https://enlacocina.b-cdn.net/wp-content/uploads/2018/02/maitre.jpg'); 
  background-repeat: no-repeat; background-size: cover;">

  <div class="container py-5 h-80" >
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card bg-dark text-white" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center " style="height:650px;">

            <div class="mb-md-5 mt-md-4 pb-5">
            
			<form method="POST">
              <h2 class="fw-bold mb-2 text-uppercase">INICIAR SESION</h2>
             
              <p class="text-white-50 mb-5">Introduzca el codigo de usuario y su contraseña</p>
              	<c:if test="${error eq true}">
    						<div class="alert alert-danger" role="alert">
        					Has introducido el usuario incorrectamente!
    						</div>
				</c:if>
				
              <div class="form-outline form-white mb-4">
                CºUsuario<input name="cUsuario" id="username" type="text" class="form-control form-control-lg" required />
               
                
              </div>

              <div class="form-outline form-white mb-4">
                Contraseña<input type="password" id="password" name="contrasena" class="form-control form-control-lg" />
               
              </div>
				<br>
              <button class="btn btn-outline-light btn-lg px-5" type="submit">Acceder</button>
              <br>
              <br>
             <a href="PaginaInicialCliente.jsp" class="btn btn-outline-light btn-lg px-5" >Volver  </a>
              

              </form>
            </div>
			
          </div>
        </div>
      </div>
    </div>
  </div>
 

</body>
</html>

			