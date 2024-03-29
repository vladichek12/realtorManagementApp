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
    <ul class="navigator">
        <li><a href="${pageContext.servletContext.contextPath}/admin/customers">Клиенты</a></li>
        <li><a class="active">Риелторы</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/orders">Заказы</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/rooms">Объявления</a></li>
    </ul>
    <div>
        <div class="title">Риелторы</div>

        <form action="realtor-search" method="post">
            <ul class="search-ul">
                <li class="search-li"><input type="text" name="query"></li>
                <li class="search-button"><input type="submit" value="Поиск"></li>
            </ul>
        </form>
        <div class="grid">
            <c:forEach var="realtor" items="${realtors}">
                <div class="item">
                    <div class="details" style="font-size: 26px">
                        <p><span style="font-weight: bold">Имя:</span> ${realtor.getName()}</p>
                        <p><span style="font-weight: bold">Почта:</span> ${realtor.getEmail()}</p>
                        <p><span style="font-weight: bold">Тел. номер:</span> ${realtor.getPhoneNumber()}</p>
                    </div>
                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/realtors">
                        <button class="grid-button" type="submit" name="userId" value="${realtor.getId()}">
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