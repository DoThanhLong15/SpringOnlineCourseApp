<%-- 
    Document   : form
    Created on : Aug 11, 2024, 4:17:02 PM
    Author     : LONG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/user/form" var="action" />

<h1 class="text-center text-primary mt-3">QUẢN LÝ NGƯỜI DÙNG</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="user">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="mb-3 mt-3">
        <label for="firstName" class="form-label">Tên người dùng</label>
        <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="Tên người dùng..." name="firstName" />

    </div>
    <div class="mb-3 mt-3">
        <label for="lastName" class="form-label">Họ và tên lót</label>
        <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Họ và tên lót..." name="lastName" />
    </div>
    <div class="mb-3 mt-3">
        <label for="email" class="form-label">Email</label>
        <form:input path="email" type="text" class="form-control" id="email" placeholder="Email..." name="email" />
    </div>
    <div class="mb-3 mt-3">
        <label for="username" class="form-label">Tên đăng nhập</label>
        <form:input path="username" type="text" class="form-control" id="username" placeholder="Tên đăng nhập..." name="username" />
    </div>
    <div class="mb-3 mt-3">
        <label for="password" class="form-label">Mật khẩu</label>
        <form:input path="password" type="password" class="form-control" id="password" placeholder="Mật khẩu..." name="password" />
    </div>
    <div class="mb-3 mt-3">
        <label for="file" class="form-label">Ảnh người dùng</label>
        <form:input path="file" type="file" accept=".jpg,.png" class="form-control" id="file" name="file" />
        <c:if test="${user.avatar != null}">
            <img class="mt-1" src="${user.avatar}" alt="${user.avatar}" width="120" />
        </c:if>
    </div>
    <div class="mb-3 mt-3">
        <label class="form-label">Vai trò</label>
        <form:select class="form-select" path="userRoleId">
            <option value="noRole" disabled selected>Chọn vai trò</option>
            <c:forEach items="${userRoles}" var="userRole">
                <c:choose>
                    <c:when test="${userRole.id == user.userRoleId.id}">
                        <option value="${userRole.id}" selected>${userRole.role}</option>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${userRole.id != 1}">
                            <option value="${userRole.id}">${userRole.role}</option>
                        </c:if>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <form:hidden path="id" />
        <form:hidden path="avatar" />
        <form:hidden path="active" />
        <form:hidden path="createdDate" />
        <form:hidden path="updatedDate" />
        <form:hidden path="oldPassword" value="${user.password}"/>
        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${user.id != null}">
                    Cập nhật người dùng
                </c:when>
                <c:otherwise>
                    Thêm người dùng
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>