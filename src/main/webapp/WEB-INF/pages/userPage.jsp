<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app" word="Недвижимость">
    <html>
    <head>
        <title>Realtor agency</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
    </head>
    <body>
    <ul>
        <li><a class="active">Помещения</a></li>
        <c:if test="${sessionScope.currentUser.getUserRole().toString() == 'ROLE_REALTOR'}">
            <li><a href="${pageContext.servletContext.contextPath}/orders">Мои заказы</a></li>
        </c:if>
    </ul>
    <div class="wrap">
        <div class="title">Доступные помещения</div>
        <c:if test="${sessionScope.currentUser.getUserRole().toString() != 'ROLE_REALTOR'}">
            <form method="get" action="${pageContext.servletContext.contextPath}/user/addRoom">
                &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                &emsp;
                <button class="table-button-add">
                    <img class="table-img" src="${pageContext.servletContext.contextPath}/images/Plus.svg"/>
                </button>
            </form>
        </c:if>
        <table class="main-table">
            <tr>
                <td>Город</td>
                <td>Улица</td>
                <td>Номер здания/квартиры/дома</td>
                <td>Кол-во комнат</td>
                <td>Площадь</td>
                <td>Описание</td>
                <td>Цена</td>
                <c:if test="${not empty currentUserRooms}">
                    <td/>
                </c:if>
            </tr>
            <tbody>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.getAddress().getCity()}</td>
                    <td>${room.getAddress().getStreet()}</td>
                    <td>${room.getAddress().getHouseNumber()}</td>
                    <td>${room.getNumberOfRooms()}</td>
                    <td>${room.getSquare()}</td>
                    <td>${room.getDescription()}</td>
                    <td>${room.getPrice()}</td>
                    <c:if test="${not empty currentUserRooms}">
                        <c:forEach var="_room" items="${currentUserRooms}">
                            <c:if test="${_room.getId().equals(room.getId())}">
                                <td class="button-td">
                                    <ul class="checkbox-ul">
                                        <li class="table-li1">
                                            <form method="post"
                                                  action="${pageContext.servletContext.contextPath}/deleteRoom/${room.id}">
                                                <button class="table-button-delete">
                                                    <img class="table-img"
                                                         src="${pageContext.servletContext.contextPath}/images/X.svg"/>
                                                </button>
                                            </form>
                                        </li>

                                        <li class="table-li2">
                                            <form method="get"
                                                  action="${pageContext.servletContext.contextPath}/updateRoom/${room.id}">
                                                <button class="table-button-update">
                                                    <img class="table-img"
                                                         src="${pageContext.servletContext.contextPath}/images/update.svg"/>
                                                </button>
                                            </form>
                                        </li>
                                    </ul>
                                </td>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
    </html>
</tags:master>