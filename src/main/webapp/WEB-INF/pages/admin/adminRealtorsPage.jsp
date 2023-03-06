<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app" word="Недвижимость">
    <html>
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>
    <body>
    <ul>
        <li><a href="${pageContext.servletContext.contextPath}/admin/customers">Customers</a></li>
        <li><a class="active">Realtors</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/orders">Orders</a></li>
    </ul>
    <div>
        <div class="title">Realtors</div>
        <%--<table class="main-table">
            <tr>
                <td>Name</td>
                <td>Email</td>
                <td>Action</td>
            </tr>
            <tbody>
            <c:forEach var="element" items="${realtors}">
                <tr>
                    <td>${element.getName()}</td>
                    <td>${element.getEmail()}</td>
                    <td>
                        <form method="post" action="">
                            <button class="table-button-delete" type="submit" name="userId" value="${element.getId()}">
                                <img class="table-img" src="${pageContext.servletContext.contextPath}/images/X.svg"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>--%>

        <form action="realtor-search" method="post">
            <input type="text" name="query">
            <input type="submit" value="Search">
        </form>
        <div class="grid">
            <c:forEach var="realtor" items="${realtors}">
                <div class="item">
                    <p>Имя: ${realtor.getName()}</p>
                    <p>Почта: ${realtor.getEmail()}</p>
                    <p>Тел. номер: ${realtor.getPhoneNumber()}</p>
                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/realtors">
                        <button class="submit-button" type="submit" name="userId" value="${realtor.getId()}">
                            Удалить
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
    </body>
</tags:master>