<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<l:template title="${brick.name}">

    <table class="table">
        <tr><td>Width:</td><td class="right-align"><c:out value="${brick.width}" /></td></tr>
        <tr><td>Height:</td><td class="right-align"><c:out value="${brick.height}" /></td></tr>
    </table>

    <a href="#" onclick="window.history.go(-1); return false;"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back</a>

</l:template>
