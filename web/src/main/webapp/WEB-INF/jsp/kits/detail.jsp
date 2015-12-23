<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<l:template title="${kit.name}">

    <table class="table">
        <tr><td>Category:</td><td class="right-align">
            <a href="<c:url value="/category/${kit.categoryDto.id}" />"><c:out value="${kit.categoryDto.name}" /></a>
        </td></tr>
        <tr><td>Brick types:</td><td class="right-align">
            <c:forEach items="${kit.bricksDtos}" var="brick">
                <a href="<c:url value="/brick/${brick.id}" />"><c:out value="${brick.name}" /></a><br />
            </c:forEach>
        </td></tr>
        <tr><td>Price:</td><td class="right-align">
            <c:out value="${kit.price}" /> <c:out value="${kit.currency}" />
        </td></tr>
        <tr><td>Age range:</td><td class="right-align">
            <c:out value="${kit.minAge}" />-<c:out value="${kit.maxAge}" />
        </td></tr>
    </table>

    <a href="#" onclick="window.history.go(-1); return false;"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back</a>

</l:template>
