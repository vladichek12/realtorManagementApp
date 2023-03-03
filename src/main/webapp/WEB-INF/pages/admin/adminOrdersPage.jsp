<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
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
        <form method="get" action="${pageContext.servletContext.contextPath}/adminAddOrder">
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;
            <button class="table-button-add">
                <img class="table-img" src="${pageContext.servletContext.contextPath}/images/Plus.svg"/>
            </button>
        </form>
        <table class="main-table">
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
        </table>

    </div>
    </div>
    </body>
    </html>
</tags:master>