<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<l:template title="${brick.name}">

    <table>
        <tr><td>Width:</td><td><c:out value="${brick.width}" /></td></tr>
        <tr><td>Height:</td><td><c:out value="${brick.height}" /></td></tr>
    </table>

</l:template>
