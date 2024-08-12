<%-- 
    Document   : list
    Created on : Aug 11, 2024, 4:16:44 PM
    Author     : LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<c:url value="/user/list" var="user_list" />
<c:url value="/user/form" var="user_form" />

<h1 class="text-center text-primary mt-3">DANH SÁCH KHÁCH HÀNG</h1>
<div class="row">
    <div class="col-md-2 col-12 bg-secondary">
        
        <form action="${user_list}">
            <div class="mb-3 mt-3">
                <label for="kw" class="form-label">Tên người dùng</label>
                <input type="text" class="form-control" id="kw" placeholder="Tên người dùng..." name="q">
            </div>
            <div class="mb-3 mt-3">
                <label for="userRoleId" class="form-label">Vai trò</label>
                <select class="form-select" name="userRoleId" id="userRoleId">
                    <option value="all" selected disabled>Chọn vai trò</option>
                    <c:forEach items="${userRoles}" var="userRole">
                        <option value="${userRole.id}">${userRole.role}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3 mt-3">
                <button class="btn btn-info" type="submit">Tìm kiếm</button>
            </div>
        </form>
    </div>
    <div class="col-md-10 col-12">
        <a class="btn btn-info m-1" href="${user_form}" />Thêm người dùng</a>
        <table class="table table-striped">
            <tr>
                <th></th>
                <th>Id</th>
                <th>Tên người dùng</th>
                <th>Vai trò</th>
                <th>Trạng thái</th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr id="course${user.id}">
                    <td>
                        <img src="${user.avatar}" width="120" />
                    </td>
                    <td>${user.id}</td>
                    <td>${user.lastName} ${user.firstName}</td>
                    <td>${user.userRoleId.role}</td>
                    <c:choose>
                        <c:when test="${user.active == true}">
                            <td class="btn-success">&#10004;</td>
                        </c:when>
                        <c:otherwise>
                            <td class="btn-danger">&#10006;</td>
                        </c:otherwise>
                    </c:choose>
                    
                    <td>
                        <a href="${user_form}/${user.id}" class="btn btn-primary">Sửa</a>

                        <button class="btn btn-warning">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
