<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="UTF-8">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
      <link href="css/styles.css" rel="stylesheet">
      <title>Vista Pedido</title>
    </head>

    <body>
        <jsp:include page="/components/header.jsp" />
        
        <h1 class="text-center display-3">Mi Pedido</h1>
        <ul class="list-group w-50 mx-auto">
        <c:forEach items="${requestScope.orders}" var="order">
             <li class="list-group-item list-group-item-success d-flex justify-content-between align-items-center">
                <span class="display-6 text-capitalize">${order.value.name}</span><span class="badge text-bg-light">${order.value.stock}</span></li>
        </c:forEach>
        </ul>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    </body>

    </html>