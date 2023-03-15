<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<tags:master pageTitle="Realtor management app">--%>
    <head>
        <link rel="stylesheet" href="styles/user.css">
        <script src="js/sort.js"></script>
        <style>
            <%@include file="/styles/admin.css"%>
        </style>
    </head>
<div class="form">
    <form method="post" action="${pageContext.servletContext.contextPath}/adminUpdateOrder/${room.id}"
          enctype="multipart/form-data">
        <h2 class="title">Room details:</h2>
        <input type="hidden" value="${room.getId()}" name="id"/>
        <tags:roomStringFormUpdateWithAddress address="${room.address}" name="city" label="Город:"
                                              possibleErrors="${possibleErrors}"></tags:roomStringFormUpdateWithAddress>

        <tags:roomStringFormUpdateWithAddress address="${room.address}" name="street" label="Улица:"
                                              possibleErrors="${possibleErrors}"></tags:roomStringFormUpdateWithAddress>

        <tags:roomStringFormUpdateWithAddress address="${room.address}" name="houseNumber"
                                              label="Номер дома/квартиры/комнаты:"
                                              possibleErrors="${possibleErrors}"></tags:roomStringFormUpdateWithAddress>

        <tags:roomStringFormUpdate room="${room}" name="square" label="Площадь:"
                                   possibleErrors="${possibleErrors}"></tags:roomStringFormUpdate>

        <tags:roomStringFormUpdate room="${room}" name="numberOfRooms" label="Кол-во комнат:"
                                   possibleErrors="${possibleErrors}"></tags:roomStringFormUpdate>

        <tags:roomStringFormUpdate room="${room}" name="description" label="Описание:"
                                   possibleErrors="${possibleErrors}"></tags:roomStringFormUpdate>

        <tags:roomStringFormUpdate room="${room}" name="price" label="Цена:"
                                   possibleErrors="${possibleErrors}"></tags:roomStringFormUpdate>
        <p>Загрузите фото(.jpeg, .png)</p>
        <div class="custom-file-input">
            <input name="image" type="file" accept="image/jpeg, image/png" id="file-input" class="inputfile" multiple max="3">
            <label for="file-input">Выбрать</label>
        </div>
        <c:if test="${not empty possibleErrors['imageError']}">
            <p class="error">${possibleErrors['imageError']}</p>
        </c:if>

        <script>
            var input = document.querySelector('.custom-file-input input[type="file"]');
            var label = document.querySelector('.custom-file-input label');

            input.addEventListener('change', function() {
                var fileCount = this.files.length;
                if (fileCount > 0) {
                    label.textContent = '';
                    for (var i = 0; i < fileCount; i++) {
                        label.textContent += this.files[i].name + '\n';
                    }
                }
            });
        </script>

        <p>
            <select name="select">
                <option disabled>Выберите риелтора:</option>
                <c:forEach var="realtor" items="${realtors}">
                    <option value="${realtor.id}">${realtor.name}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <select name="type">
                <option disabled>Выберите тип помещения</option>
                <option value="FLAT">Квартира</option>
                <option value="COTTAGE">Коттедж</option>
                <option value="ROOM">Комната</option>
                <option value="HOUSE">Дом</option>
            </select>
        </p>

        <button class="submit-button">Подтвердить</button>
    </form>
</div>

<%--</tags:master>--%>