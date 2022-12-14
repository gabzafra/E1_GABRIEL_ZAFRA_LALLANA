<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="contact container mx-auto m-3">
    <article>
        <h1>${requestScope.detail.name} ${requestScope.detail.surnames}</h1>
        <p><span>Email: </span><a href="mailto:email@email.com">${requestScope.detail.email}</a></p>
        <p><span>Teléfono: </span>${requestScope.detail.phone}</p>
        <p>${requestScope.detail.coments}</p>
    </article>
    <a href="./contact" class="btn btn-primary">Volver</a>
</div>