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
        <li><a class="active">Realtors</a></li>
        <li><a href="adminOrders">Orders</a></li>
    </ul>
    <div>
        <div>Realtors</div>
        <table>
            <tr>
                <td>Name</td>
                <td>Email</td>
                <td>Action</td>
            </tr>
            <tbody>
            <c:forEach var ="element" items = "${realtors}">
                <tr>
                    <td>${element.getName()}</td>
                    <td>${element.getEmail()}</td>
                    <td><form method="post" action="">
                        <button type="submit" name="userId" value="${element.getId()}">delete</button>
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
