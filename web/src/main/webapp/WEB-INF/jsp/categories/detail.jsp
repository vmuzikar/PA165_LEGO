<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<l:template title="${category.name}">

    <table class="table">
        <tr><td>Name:</td><td class="right-align"><c:out value="${category.name}" /></td></tr>
    </table>

    <a href="#" onclick="window.history.go(-1); return false;"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back</a>

</l:template>
