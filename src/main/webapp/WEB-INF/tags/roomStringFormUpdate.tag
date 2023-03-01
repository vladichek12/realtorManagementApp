<%@ tag trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ attribute name="room" required="true" type="realtorManagementApp.entities.Room" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="possibleErrors" required="true" type="java.util.Map" %>

<p>${label}<span style="color: darkred">*</span></p>
<td>
    <c:set var="inputError" value="${possibleErrors[name]}"/>
    <input name="${name}" value="${room[name]}"/>
    <c:if test="${not empty inputError}">
        <p class="error">${inputError}</p>
    </c:if>
</td>