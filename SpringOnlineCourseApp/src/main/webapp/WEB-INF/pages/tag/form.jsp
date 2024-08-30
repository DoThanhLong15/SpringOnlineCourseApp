<%-- 
    Document   : form
    Created on : Aug 30, 2024, 9:35:55 PM
    Author     : LONG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/tags/form/save" var="action" />

<h1 class="text-center text-primary mt-3">QUẢN LÝ TỪ KHÓA</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="tag">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="mb-3 mt-3">
        <label for="name" class="form-label">Từ khóa</label>
        <form:input path="name" type="text" class="form-control" id="name" placeholder="Từ khóa..." name="name" />
    </div>

    <div class="mb-3 mt-3">
        <form:hidden path="id" />

        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${tag.id != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>