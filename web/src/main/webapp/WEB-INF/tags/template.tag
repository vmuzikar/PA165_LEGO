<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" type="java.lang.String" required="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
        <c:if test="${not empty title}">
            <c:out value="${title}" /> |
        </c:if>
        Lego Manager
    </title>
</head>
<body>

<p><a href="<c:url value="/" />">Home</a> | <a href="<c:url value="/brick" />">Bricks</a></p>

<c:if test="${not empty title}">
    <h1><c:out value="${title}" /></h1>
</c:if>

<jsp:doBody />

</body>
</html>
