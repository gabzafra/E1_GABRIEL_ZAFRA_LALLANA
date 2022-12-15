<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <header>
        <nav class="navbar navbar-expand-lg bg-light navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="./">Tienda</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
              aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <c:if test="${not empty requestScope.clientName}">
                    <span class="navbar-text fst-italic">Hola ${requestScope.clientName}</span>
                    <a class="nav-link" href="./auth?exit=true">Salir</a>
                </c:if>
                <c:if test="${empty requestScope.clientName}">
                    <a class="nav-link" href="auth-form.jsp">Identificarme</a>
                    <a class="nav-link" href="auth-form.jsp?register=true">Registrarme</a>
                </c:if>
                <c:if test="${requestScope.productsNumber != 0}">
                    <a class="nav-link" href="./order">Ver Cesta <span class="badge badge-pill bg-success">${requestScope.productsNumber}</span></a>
                </c:if>
                <c:if test="${requestScope.productsNumber == 0}">
                    <a class="nav-link disabled" href="./order">Ver Cesta</a>
                </c:if>
              </div>
            </div>
          </div>
        </nav>
      </header>