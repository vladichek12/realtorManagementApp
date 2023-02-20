<%@ tag trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ attribute name="room" required="true" type="realtorManagementApp.entities.Room" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="possibleErrors" required="true" type="java.util.Map" %>

<td>${label}<span style="color: darkred">*</span></td>
<td>
    <c:set var="inputError" value="${possibleErrors[name]}"/>
    <input name="${name}" value="${room.address[name]}"/>
    <c:if test="${not empty inputError}">
        <div class="error">${inputError}</div>
    </c:if>
</td>