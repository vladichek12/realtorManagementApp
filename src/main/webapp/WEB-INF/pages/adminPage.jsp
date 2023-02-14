<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
    <html>
    <head>
        <title>Library</title>
        <link rel="stylesheet" href="styles/admin.css">
        <script src="js/sort.js"></script>
    </head>
    <body>
    <div>
        <div class="wrap">
            <div>Customers</div>
            <table>
                <tr>
                    <td>Name</td>
                    <td>Email</td>
                </tr>
                <tbody>
                <c:forEach var ="element" items = "${customers}">
                    <tr>
                        <td>${element.getName()}</td>
                        <td>${element.getEmail()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="wrap">
            <div>Realtors</div>
            <table>
                <tr>
                    <td>Name</td>
                    <td>Email</td>
                </tr>
                <tbody>
                <c:forEach var ="element" items = "${realtors}">
                    <tr>
                        <td>${element.getName()}</td>
                        <td>${element.getEmail()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </body>
    </html>
</tags:master>