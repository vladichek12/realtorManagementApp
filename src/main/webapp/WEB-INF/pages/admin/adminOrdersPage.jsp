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
    <ul>
        <li><a href="adminCustomers">Customers</a></li>
        <li><a href="adminRealtors">Realtors</a></li>
        <li><a class="active">Orders</a></li>
    </ul>
    <div>
        <div>Orders</div>
        <form method="get" action="${pageContext.servletContext.contextPath}/adminAddOrder">
            <button>
                Add new
            </button>
        </form>
        <table>
            <tr>
                <td>Realtor</td>
                <td>Room</td>
                <td>Action</td>
            </tr>
            <tbody>
            <c:forEach var ="element" items = "${orders}">
                <tr>
                    <td>${element.getRealtor().getName()}</td>
                    <td>${element.getRoom().getAddress().toString()}</td>
                    <td><form method="post" action="">
                        <button type="submit" name="orderId" value="${element.getId()}">delete</button>
                    </form>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    </div>
    </body>
    </html>
</tags:master>
