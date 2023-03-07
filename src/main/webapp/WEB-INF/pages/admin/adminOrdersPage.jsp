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
        <li><a href="${pageContext.servletContext.contextPath}/admin/realtors">Realtors</a></li>
        <li><a class="active">Orders</a></li>
    </ul>
    <div>
        <div class="title">Orders</div>
<%--        <form method="get" action="${pageContext.servletContext.contextPath}/adminAddOrder">
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;
            <button class="table-button-add">
                <img class="table-img" src="${pageContext.servletContext.contextPath}/images/Plus.svg"/>
            </button>
        </form>--%>
        <%--<table class="main-table">
            <tr>
                <td>Realtor</td>
                <td>Room</td>
                <td>Action</td>
            </tr>
            <tbody>
            <c:forEach var="element" items="${orders}">
                <tr>
                    <td>${element.getRealtor().getName()}</td>
                    <td>${element.getRoom().getAddress().toString()}</td>
                    <td>
                        <form method="post" action="${pageContext.servletContext.contextPath}/admin/orders">
                            <button class="table-button-delete" type="submit" name="userId" value="${element.getId()}">
                                <img class="table-img" src="${pageContext.servletContext.contextPath}/images/X.svg"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>--%>

        <form action="order-search" method="post">
            <ul class="search-ul">
                <li class = "search-li"><input type="text" name="query"></li>
                <li class = "search-button"><input type="submit" value="Search" ></li>
            </ul>
            <ul class="filters-ul">
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="flat" > Квартиры</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="house"> Дома</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="cottage"> Коттеджи</label>
                </li>
                <li class="filters-li">
                    <label><input type="checkbox" name="filter" value="room"> Комнаты</label>
                </li>
            </ul>
        </form>
        <div class="grid">
            <c:forEach var="room" items="${rooms}">
                <div class="item">
                    <p>Описание: ${room.getDescription()}</p>
                    <p>Адрес: г.${room.getAddress().getCity()}, ул. ${room.getAddress().getStreet()},
                        д. ${room.getAddress().getHouseNumber()}</p>
                    <p>Количетсво комнат: ${room.getNumberOfRooms()}</p>
                    <p>Площадь: ${room.getSquare()}</p>
                    <p>Цена: ${room.getPrice()}</p>
                    <form method="get"  action="${pageContext.servletContext.contextPath}/adminUpdateOrder/${room.id}">
                    <button class="submit-button">Редактировать</button>
                    </form>
                    <form method="post" action="${pageContext.servletContext.contextPath}/admin/orders">
                        <button class="submit-button" type="submit" name="userId" value="${room.getId()}">
                            Удалить
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

    </div>
    </div>
    </body>
    </html>
</tags:master>