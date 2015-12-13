<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<l:template title="Add brick">

    <form:form method="post" modelAttribute="brick">
        <table>
            <tr><td>Name:</td><td><form:input path="name" /><form:errors path="name" /></td></tr>
            <tr><td>Width:</td><td><form:input path="width" /><form:errors path="width" /></td></tr>
            <tr><td>Height:</td><td><form:input path="height" /><form:errors path="height" /></td></tr>
        </table>
        <input type="submit" value="Save!" />
        <form:errors />
    </form:form>

</l:template>
