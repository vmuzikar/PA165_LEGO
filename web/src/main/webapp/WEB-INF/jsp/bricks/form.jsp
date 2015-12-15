<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<l:template title="Add brick">

    <form:form method="post" modelAttribute="brick">
        <table class="table">
            <tr><td><label for="name">Name:</label></td><td><form:input path="name" class="form-control" /><form:errors path="name" /></td></tr>
            <tr><td><label for="width">Width:</label></td><td><form:input path="width" class="form-control" /><form:errors path="width" /></td></tr>
            <tr><td><label for="height">Height:</label></td><td><form:input path="height" class="form-control" /><form:errors path="height" /></td></tr>
        </table>
        <div class="right-align">
            <button type="submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span> Save
            </button>
        </div>
        <form:errors />
    </form:form>
    <a href="<c:url value="/brick" />"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to all</a>

</l:template>
