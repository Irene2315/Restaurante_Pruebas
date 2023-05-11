<!DOCTYPE html>
<html>
<head>
  <title>Centrado de Div con Bootstrap</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-image: url('https://enlacocina.b-cdn.net/wp-content/uploads/2018/02/maitre.jpg');
      background-repeat: no-repeat;
      background-size: cover;
    }
    .center-div {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .btn-group {
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
    }
    .form-container {
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
      background-color: white;
      width: 35vw;
      
    }
    a.btn{
    font-size:30px;
     }
     h1{
     margin: 0 auto;
     margin-bottom:10px;
     }
  </style>
</head>
<body>
  <div class="container center-div">
    <div class="form-container">
      <div class="row">
        <div class="col">
          <div class="btn-group">
              <h1>¡Bienvenid@ ${sessionScope.usuarioLogueado.nombre}, tu eres ${sessionScope.usuarioLogueado.rol.nombre}!</h1>
            <a href="VerUsuarios" class="btn btn-dark btn-block text-center">Gestor usuarios</a>
            <a href="VerEventos" class="btn btn-dark  btn-block text-center">Gestor eventos</a>
            <a href="ReservasU" class="btn btn-dark  btn-block text-center">Gestor reservas</a>
            <a href="VerProductos" class="btn btn-dark  btn-block text-center">Gestor productos</a>
            <a href="VerPlatos" class="btn btn-dark  btn-block text-center">Gestor platos</a>
            <a href="CerrarSesion" class="btn btn-danger btn-block text-center">Cerrar sesión</a>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</body>
</html>