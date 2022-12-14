<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="contact-list container">
            <h1>Productos</h1>
            <c:forEach items="${requestScope.list}" var="product">
                <div class="row align-items-center border p-2">
                    <div class="col">
                        <a href="./product?id=${product.value.id}">${product.value.name}</a>
                    </div>
                </div>
            </c:forEach>
        </div>