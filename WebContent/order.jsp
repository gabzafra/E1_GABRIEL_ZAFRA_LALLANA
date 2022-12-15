<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="w-50 mx-auto">
        <ul class="list-group">
        <c:set var = "total" scope = "page" value = "${0}"/>
        <c:forEach items="${requestScope.orders}" var="order">
        <fmt:formatNumber var="subtotal" value="${(order.value.price * order.value.stock)}"  maxFractionDigits="2" />
             <c:set var = "total" scope = "page" value = "${(total + (order.value.price * order.value.stock))}"/>
             <li class="list-group-item list-group-item-success d-flex justify-content-between align-items-center">
                <span class="display-6 text-capitalize">${order.value.name} - ${order.value.stock} <span class="text-lowercase">x</span> ${order.value.price}</span><span class="display-6 text-capitalize">${subtotal}€</span></li>
        </c:forEach>
        <fmt:formatNumber var="total" value="${total}"  maxFractionDigits="2" />
        <li class="list-group-item text-bg-success d-flex justify-content-between align-items-center">
                <span class="display-5 text-capitalize">Total: </span><span class="display-5 text-capitalize">${total}€</span></li>
        </ul>
        <div class="d-flex justify-content-end mt-3">
        <a href="./" class="btn btn-primary">Volver</a>
        <a href="./order?complete=true" class="btn btn-success ms-1">Comprar</a>
        <a href="./order?clear=true" class="btn btn-danger ms-1">Vaciar la cesta</a>
        </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    </body>

    </html>