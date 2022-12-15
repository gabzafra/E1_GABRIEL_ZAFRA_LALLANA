<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <h1 class="text-center display-3">Productos</h1>
        <div class="product-container row row-cols-3 g-4">         
            <c:forEach items="${requestScope.list}" var="product">
            <div class="col">
             <div class="product card text-bg-light">
                            <div class="card-header display-6 text-capitalize">${product.value.name}</div>
                <img src="./img/${product.value.name}.jpg" class="card-img-top w-75 mx-auto mt-2" alt="${product.value.name}">
                <div class="card-body">
                    <p class="card-text">${product.value.description}</p>
                    <p class="display-5">${product.value.price}€</p>
                    <a href="./order?id=${product.value.id}" class="btn btn-success">Añadir a la cesta</a>
                </div>
            </div>
            </div>
            </c:forEach>

        </div>