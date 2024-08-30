<%-- 
    Document   : list
    Created on : Aug 30, 2024, 3:08:25 PM
    Author     : LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<c:url value="/categories/list" var="category_list" />
<c:url value="/categories/form" var="category_form" />

<h1 class="text-center text-primary mt-3">DANH SÁCH DANH MỤC</h1>
<div class="row">
    <div class="col-md-2 col-12 bg-secondary">

        <form action="${category_list}">
            <div class="mb-3 mt-3">
                <label for="kw" class="form-label">Từ khóa:</label>
                <input type="text" class="form-control" id="kw" placeholder="Từ khóa..." name="q">
            </div>
            <div class="mb-3 mt-3">
                <button class="btn btn-info" type="submit">Tìm kiếm</button>
            </div>
        </form>
    </div>
    <div class="col-md-10 col-12">
        <a class="btn btn-info m-1" href="${category_form}" />Thêm khóa học</a>
        <table class="table table-striped">
            <tr>
                <th>Id</th>
                <th>Tên danh mục</th>
                <th></th>
            </tr>
            <c:forEach items="${categories}" var="category">
                <tr id="category${category.id}">
                    <td>${category.id}</td>
                    <td>${category.name}</td>

                    <td>
                        <a href="" class="btn btn-primary">Sửa</a>

                        <button class="btn btn-warning">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div>
            <ul class="pagination">
                <c:set value="?page=" var="page"/>
                <c:if test="${q != null}">
                    <c:set value="?q=${q}&page=" var="page"/>
                </c:if>
                <c:if test="${totalPages > 1}">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="${page}1"><<</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="${page}${currentPage - 1}"><</a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <li class="page-item active" aria-current="page">
                                    <span class="page-link">${i}</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a href="${page}${i}" class="page-link">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a href="${page}${currentPage + 1}" class="page-link">></a>
                        </li>
                        <li class="page-item">
                            <a href="${page}${totalPages}" class="page-link">>></a>
                        </li>
                    </c:if>
                </c:if>
            </ul>
        </div>
    </div>
</div>