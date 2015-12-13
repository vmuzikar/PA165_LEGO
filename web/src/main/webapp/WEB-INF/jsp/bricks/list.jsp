<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<l:template title="Bricks">

    <c:choose>
        <c:when test="${not empty bricks}">
            <table>
                <tr><td>Name</td><td>Size</td><td></td></tr>
                <c:forEach items="${bricks}" var="brick">
                    <tr>
                        <td><a href="<c:url value="/brick/${brick.id}" />" ><c:out value="${brick.name}" /></a></td>
                        <td>${brick.width} × ${brick.height}</td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p><strong>No bricks found!</strong></p>
        </c:otherwise>
    </c:choose>

    <p><a href="<c:url value="/brick/create" />">Create</a></p>

</l:template>
