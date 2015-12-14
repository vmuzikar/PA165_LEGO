<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="shortcut icon" href="<c:url value="/favicon.ico" />" />
    <link rel="apple-touch-icon" href="<c:url value="/apple-touch-icon.png" />" />
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/datatables/datatables.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/default.css" />" />
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mob-menu" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/" />">LEGO MANAGER</a>
    </div>
    <div class="collapse navbar-collapse" id="mob-menu">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value="/" />">Home</a></li>
        <li><a href="<c:url value="/brick" />">Bricks</a></li>
        <li><a href="<c:url value="/kit" />">Kits</a></li>
        <li><a href="<c:url value="/category" />">Categories</a></li>
      </ul>
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Username">
          <input type="text" class="form-control" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-default">Login</button>
      </form>
    </div>
  </div>
</nav>
<main>
<c:if test="${not empty title}">
    <h1><c:out value="${title}" /></h1>
</c:if>

<jsp:doBody />
</main>
<script type="text/javascript" src="<c:url value="/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/datatables/datatables.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/main.js" />"></script>
</body>
</html>
