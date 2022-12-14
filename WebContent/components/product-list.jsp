<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="contact-list container">
            <c:if test="${!empty param.error}">
                <jsp:include page="/components/error-msj.jsp">
                    <jsp:param name="msj" value="Se ha producido un error al intentar eliminar el contacto." />
                </jsp:include>
            </c:if>
            <h1>Contactos</h1>
            <c:forEach items="${requestScope.list}" var="contact">
                <div class="row align-items-center border p-2">
                    <div class="col">
                        <a href="./contact?id=${contact.id}">${contact.name}</a>
                    </div>
                    <div class="col-1">
                        <a href="./contact?upd=${contact.id}" class="btn btn-primary">Editar</a>
                    </div>
                    <div class="col-1">
                        <button onclick="sendDelete('./contact?id=${contact.id}')" type="button"
                            class="btn btn-danger">Borrar</button>
                    </div>
                </div>
            </c:forEach>
        </div>