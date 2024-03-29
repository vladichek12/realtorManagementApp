<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-16BE" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tags:master pageTitle="Realtor management app" word="Недвижимость">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Details</title>
    <style>
        <%@include file="/styles/admin.css"%>
    </style>
</head>
<body>
<div class="container">
    <h1>Room Details</h1>

    <p>Type: ${room.getType()}</p>

    <div>
        <c:forEach var="image" items="${room.getRoomImage()}">
            <img src="data:${image.getType()};base64, ${fn:escapeXml(Base64.getEncoder().encodeToString(image.getImage()))}" />
        </c:forEach>
    </div>

    <p>Address: г.${room.getAddress().getCity()}, ул. ${room.getAddress().getStreet()}, д. ${room.getAddress().getHouseNumber()}</p>
    <p>Number of rooms: ${room.getNumberOfRooms()}</p>
    <p>Square: ${room.getSquare()}</p>
    <p>Price: ${room.getPrice()}$</p>
</div>
</body>
</html>
</tags:master>
