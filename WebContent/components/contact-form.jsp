<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div id="contact-form" class="contact container mx-auto m-3">
        <c:if test="${empty requestScope.update}">
            <form action="<%=request.getContextPath()%>/contact" method="POST">
        </c:if>
        <c:if test="${!empty requestScope.update}">
            <form onsubmit="event.preventDefault(); sendPut(this)" action="<%=request.getContextPath()%>/contact?upd=${requestScope.contact.id}" method="POST">
        </c:if>
            <fieldset>
                <c:if test="${empty requestScope.update}">
                    <legend>Nuevo contacto</legend>
                </c:if>
                <c:if test="${!empty requestScope.update}">
                    <legend>Actualizar contacto</legend>
                </c:if>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="name" placeholder="Nombre"
                        value="${requestScope.contact.name}">
                    <label for="name">Nombre</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="surnames" placeholder="Apellidos"
                        value="${requestScope.contact.surnames}">
                    <label for="surnames">Apellidos</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" name="email" placeholder="Email"
                        value="${requestScope.contact.email}">
                    <label for="email">Email</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="phone" placeholder="TelÃ©fono"
                        value="${requestScope.contact.phone}">
                    <label for="phone">Teléfono</label>
                </div>
                <div class="form-floating mb-3">
                    <textarea class="form-control" name="coments"
                        placeholder="Comentarios">${requestScope.contact.coments}</textarea>
                    <label for="coments">Comentarios</label>
                </div>
                <c:if test="${empty requestScope.update}">
                    <button type="submit" class="btn btn-primary">Añadir</button>
                </c:if>
                <c:if test="${!empty requestScope.update}">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                    <a href="./contact" class="btn btn-danger">Cancelar</a>
                </c:if>
                <c:if test="${empty requestScope.list && empty requestScope.detail && empty requestScope.update}">
                    <a href="./contact" class="btn btn-success">Ver contactos</a>
                </c:if>
            </fieldset>
        </form>
        <p>${param.error}</p>
        <c:if test="${!empty requestScope.error}">
            <jsp:include page="/components/error-msj.jsp">
                <jsp:param name="msj" value="${requestScope.error}" />
            </jsp:include>
        </c:if>
    </div>