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
        <li><a class="active">Клиенты</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/realtors">Риелторы</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/orders">Заказы</a></li>
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
                    <p>Имя: ${customer.getName()}</p>
                    <p>Почта: ${customer.getEmail()}</p>
                    <p>Тел. номер: ${customer.getPhoneNumber()}</p>
                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/customers">
                        <button class="submit-button" type="submit" name="userId" value="${customer.getId()}">
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