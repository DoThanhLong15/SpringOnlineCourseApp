<%-- 
    Document   : header
    Created on : Aug 9, 2024, 3:07:01 PM
    Author     : LONG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Khóa học Online</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Danh mục</a>
                <ul class="dropdown-menu">
                    <c:forEach items="${categories}" var="category">
                        <li>
                            <a class="dropdown-item" href="#">${category.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
        </div>
    </div>
</nav>
