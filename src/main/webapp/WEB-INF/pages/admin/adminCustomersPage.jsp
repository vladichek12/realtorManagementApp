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
        <li><a class="active">Клиенты</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/realtors">Риелторы</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/orders">Заказы</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/rooms">Объявления</a></li>
    </ul>
    <div>
        <div class="title">Клиенты</div>
        <form action="customer-search" method="post">
            <ul class="search-ul">
                <li class="search-li"><input type="text" name="query"></li>
                <li class="search-button"><input type="submit" value="Поиск"></li>
            </ul>
        </form>
        <div class="grid">
            <c:forEach var="customer" items="${customers}">
                <div class="item">
                    <div class="details" style="font-size: 26px">
                        <p><span style="font-weight: bold">Имя:</span> ${customer.getName()}</p>
                        <p><span style="font-weight: bold">Почта:</span> ${customer.getEmail()}</p>
                        <p><span style="font-weight: bold">Тел. номер:</span> ${customer.getPhoneNumber()}</p>
                    </div>

                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/customers">
                        <button class="grid-button" type="submit" name="userId" value="${customer.getId()}">
                            Удалить
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>

    </div>
    </div>
    </body>
    </html>
</tags:master>