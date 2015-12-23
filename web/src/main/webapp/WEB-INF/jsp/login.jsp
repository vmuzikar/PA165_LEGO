<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>

<body>

<div id="login-box">

    <c:if test="${param.error ne null}">
        <div class="error">Invalid username or password!</div>
    </c:if>

    <c:if test="${param.logout ne null}">
        <div class="msg">You've been successfully logged out.</div>
    </c:if>

    <form name='loginForm' action="" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='user' /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='pass' /></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value="submit" />
                </td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>

</body>
</html>