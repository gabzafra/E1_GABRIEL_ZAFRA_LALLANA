<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="UTF-8">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
      <link href="css/styles.css" rel="stylesheet">
      <title>Vista Principal</title>
    </head>

    <body>
      <header>
        <nav class="navbar navbar-expand-lg bg-light navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">Tienda</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
              aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link" href="#">Identificarme</a>
                <a class="nav-link" href="#">Registrarme</a>
                <a class="nav-link disabled">Ver Cesta</a>
              </div>
            </div>
          </div>
        </nav>
      </header>
      <c:if test="${!empty requestScope.list}">
        <jsp:include page="/components/product-list.jsp" />
      </c:if>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    </body>

    </html>