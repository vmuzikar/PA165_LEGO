<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<l:template title="Create kit from 3D model">

    <form:form method="post" modelAttribute="model">
        <form:errors path="" element="strong" />
        <table class="table">
            <tr>
                <td><label for="width">3D model width (mm):</label></td>
                <td><form:input path="width" class="form-control" /><form:errors path="width" element="p" /></td>
            </tr>
            <tr>
                <td><label for="height">3D model height (mm):</label></td>
                <td><form:input path="height" class="form-control" /><form:errors path="height" element="p" /></td>
            </tr>
            <tr>
                <td><label for="depth">3D model depth (mm):</label></td>
                <td><form:input path="depth" class="form-control" /><form:errors path="depth" element="p" /></td>
            </tr>
        </table>
        <div class="right-align">
            <button type="submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> Next
            </button>
        </div>
    </form:form>
    <a href="<c:url value="/kit" />"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to all</a>

</l:template>
