<%-- 
    Document   : product
    Created on : Aug 10, 2024, 2:43:58 PM
    Author     : LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<c:url value="/course/list" var="course_list" />
<c:url value="/course/form" var="course_form" />

<h1 class="text-center text-primary mt-3">DANH SÁCH KHÓA HỌC</h1>
<div class="row">
    <div class="col-md-2 col-12 bg-secondary">
        
        <form action="${course_list}">
            <div class="mb-3 mt-3">
                <label for="kw" class="form-label">Từ khóa:</label>
                <input type="text" class="form-control" id="kw" placeholder="Từ khóa..." name="q">
            </div>
            <div class="mb-3 mt-3">
                <label for="fromPrice" class="form-label">Từ giá (VNĐ):</label>
                <input type="number" class="form-control" id="fromPrice" placeholder="Từ giá..." name="fromPrice">
            </div>
            <div class="mb-3 mt-3">
                <label for="toPrice" class="form-label">Đến giá (VNĐ):</label>
                <input type="number" class="form-control" id="toPrice" placeholder="Đến giá..." name="toPrice">
            </div>
            <div class="mb-3 mt-3">
                <label for="cateId" class="form-label">Danh mục</label>
                <select class="form-select" name="cateId" id="cateId">
                    <option value="all" selected disabled>Chọn danh mục</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3 mt-3">
                <button class="btn btn-info" type="submit">Tìm kiếm</button>
            </div>
        </form>
    </div>
    <div class="col-md-10 col-12">
        <a class="btn btn-info m-1" href="${course_form}" />Thêm khóa học</a>
        <table class="table table-striped">
            <tr>
                <th></th>
                <th>Id</th>
                <th>Tên khóa học</th>
                <th>Gía</th>
                <th>Trạng thái</th>
                <th></th>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr id="course${course.id}">
                    <td>
                        <img src="${course.image}" width="120" />
                    </td>
                    <td>${course.id}</td>
                    <td>${course.title}</td>
                    <td>${String.format("%,d", course.price)} VND</td>
                    <c:choose>
                        <c:when test="${course.status == true}">
                            <td class="btn-success">&#10004;</td>
                        </c:when>
                        <c:otherwise>
                            <td class="btn-danger">&#10006;</td>
                        </c:otherwise>
                    </c:choose>
                    
                    <td>
                        <a href="" class="btn btn-primary">Sửa</a>

                        <button class="btn btn-warning">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>