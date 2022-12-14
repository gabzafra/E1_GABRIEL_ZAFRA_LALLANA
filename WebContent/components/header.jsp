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
                <a class="nav-link" href="auth-form.jsp">Identificarme</a>
                <a class="nav-link" href="auth-form.jsp?register=true">Registrarme</a>
                <a class="nav-link" href="./order">Ver Cesta</a>
              </div>
            </div>
          </div>
        </nav>
      </header>