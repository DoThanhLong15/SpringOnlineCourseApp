<%-- 
    Document   : header
    Created on : Aug 9, 2024, 3:07:01 PM
    Author     : LONG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<c:url value="/" var="home"/>
<c:url value="/logout" var="logout"/>
<c:url value="/categories/list" var="categories_list"/>
<c:url value="/courses/list" var="course_list"/>
<c:url value="/users/list" var="user_list"/>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${home}">Khóa học Online</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link" href="${categories_list}">Danh mục</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${course_list}">Khóa học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${user_list}">Người dùng</a>
                </li>
                <s:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link text-primary" href="${home}">
                            Xin chào
                            <s:authentication property="principal.username" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-danger" href="${logout}">
                            Đăng xuất
                        </a>
                    </li>
                </s:authorize>
            </ul>
        </div>
    </div>
</nav>
