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
      <jsp:include page="/components/header.jsp" />
        <div id="auth-form" class="auth container mx-auto m-3">
        <c:if test="${empty param.register}">
            <form action="<%=request.getContextPath()%>/auth" method="POST">
        </c:if>
        <c:if test="${!empty param.register}">
            <form action="<%=request.getContextPath()%>/auth?register=true" method="POST">
        </c:if>
            <fieldset>
                <c:if test="${empty param.register}">
                    <legend>Datos de acceso</legend>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" name="email" placeholder="Email"
                            value="${requestScope.client.email}">
                            <label for="email">Email</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" name="pass" placeholder="Contraseña"
                            value="">
                            <label for="pass">Contraseña</label>
                        </div>
                </c:if>
                <c:if test="${!empty param.register}">
                    <legend>Nuevo cliente</legend>
                
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="name" placeholder="Nombre"
                        value="${requestScope.client.name}">
                    <label for="name">Nombre</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="surnames" placeholder="Apellidos"
                        value="${requestScope.client.surnames}">
                    <label for="surnames">Apellidos</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" name="email" placeholder="Email"
                        value="${requestScope.client.email}">
                    <label for="email">Email</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="phone" placeholder="Teléfono"
                        value="${requestScope.client.phone}">
                    <label for="phone">Teléfono</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" name="pass" placeholder="Contraseña"
                        value="">
                    <label for="pass">Contraseña</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" name="pass2" placeholder="Contraseña"
                        value="">
                    <label for="pass2">Repita Contraseña</label>
                </div>
                </c:if>
                <c:if test="${empty param.register}">
                    <button type="submit" class="btn btn-primary">Entrar</button>
                </c:if>
                <c:if test="${!empty param.register}">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </c:if>
                <a href="./" class="btn btn-danger">Cancelar</a>
            </fieldset>
        </form>
        <p>${param.error}</p>
        <c:if test="${!empty requestScope.error}">
            <jsp:include page="/components/error-msj.jsp">
                <jsp:param name="msj" value="${requestScope.error}" />
            </jsp:include>
        </c:if>
    </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    </body>

    </html>
    