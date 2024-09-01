<%-- 
    Document   : form
    Created on : Aug 11, 2024, 2:25:40 PM
    Author     : LONG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/courses/form/save" var="action" />

<h1 class="text-center text-primary mt-3">QUẢN LÝ KHÓA HỌC</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="course">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="mb-3 mt-3">
        <label for="title" class="form-label">Tên khóa học</label>
        <form:input path="title" type="text" class="form-control" id="title" placeholder="Tên khóa học..." name="title" />
    </div>
    <div class="mb-3 mt-3">
        <label for="description" class="form-label">Mô tả</label>
        <form:textarea path="description" type="text" class="form-control" id="description" placeholder="Mô tả..." name="description" rows="4"/>
    </div>
    <div class="mb-3 mt-3">
        <label for="price" class="form-label">Gía khóa học</label>
        <form:input path="price" type="number" class="form-control" id="price" placeholder="Gía khóa học..." name="price" />
    </div>
    <div class="mb-3 mt-3">
        <label for="file" class="form-label">Ảnh khóa học</label>
        <form:input path="file" type="file" accept=".jpg,.png" class="form-control" id="file" name="file" />
        <c:if test="${course.image != null}">
            <img class="mt-1" src="${course.image}" alt="${course.image}" width="120" />
        </c:if>
    </div>
    <div class="mb-3 mt-3">
        <label class="form-label">Danh mục</label>
        <form:select class="form-select" path="categoryId" >
            <option disabled selected>Chọn danh mục</option>
            <c:forEach items="${categories}" var="category">
                <c:choose>
                    <c:when test="${category.id == course.categoryId.id}">
                        <option value="${course.id}" selected>${category.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${category.id}">${category.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <label class="form-label">Giảng viên</label>
        <form:select class="form-select" path="lecturerId" >
            <option disabled selected>Chọn giảng viên</option>
            <c:forEach items="${users}" var="user">
                <c:choose>
                    <c:when test="${user.id == course.lecturerId.id}">
                        <option value="${user.id}" selected>${user.lastName} ${user.firstName}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${user.id}">${user.lastName} ${user.firstName}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <label for="tagInput">Từ khóa</label>
        <div id="tagContainer" class="mb-2 flex">
            <c:forEach items="${course.courseTagForm}" varStatus="status">
                <form:hidden path="courseTagForm[${status.index}].tagId"/>
            </c:forEach>
        </div>

        <div class="tagFormContainer">
            <c:forEach items="${course.courseTagCollection}" varStatus="status" var="courseTag">
                <div>
                    <label class="tag">${courseTag.tagId.name}</label>
                    <form:hidden path="courseTagCollection[${status.index}].id"/>
                    <form:hidden path="courseTagCollection[${status.index}].courseId.id"/>
                    <form:hidden path="courseTagCollection[${status.index}].tagId"/>
                </div>
            </c:forEach>
        </div>

        <input type="text" id="tagInput" class="form-control tag-input" placeholder="Tìm từ khóa">
        <ul class="list-group mt-1" id="search-container"></ul>
    </div>
    <div class="mb-3 mt-3">
        <form:hidden path="id" />
        <form:hidden path="image" />
        <form:hidden path="status" />
        <form:hidden path="rating" />
        <form:hidden path="ratingCount" />
        <form:hidden path="participantCount" />

        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${course.id != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

<script>
    let tagIndex = '${course.courseTagForm.size()}' === '0' ? 0 : parseInt('${course.courseTagForm.size()}');
    let tagFormContainer = document.getElementsByClassName("tagFormContainer")[0];

    const addTagForm = (id, tagName) => {

        const container = document.createElement('div');
        container.setAttribute('class', 'tag-' + id);

        const tagIdInput = document.createElement('input');
        tagIdInput.setAttribute('type', 'text');
        tagIdInput.setAttribute('name', 'courseTagForm[' + tagIndex + '].tagId');
        tagIdInput.setAttribute('value', id);
        tagIdInput.setAttribute('hidden', true);

        container.appendChild(tagIdInput);

        document.querySelector('.tagFormContainer').appendChild(container);

        tagIndex++;
    };
</script>
<script src="<c:url value="/js/TagInput.js" />"></script>