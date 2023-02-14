<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Realtor management app">
    <div class="books">
        <table id = "sortable">
            <tr>
                <td >Name</td>
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
</tags:master>>
