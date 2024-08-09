<%-- 
    Document   : home
    Created on : Aug 9, 2024, 9:52:54 PM
    Author     : LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<h1 class="text-center text-primary mt-1">USER</h1>
<c:url value="/register" var="action" />
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<form:form method="post" enctype="multipart/form-data" action="${action}">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="mb-3 mt-3">
        <button class="btn btn-success" type="submit">
            Đăng ký
        </button>
    </div>
</form:form>