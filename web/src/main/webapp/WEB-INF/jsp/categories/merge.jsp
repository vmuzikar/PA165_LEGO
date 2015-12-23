<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<l:template title="Merge categories">

    <form:form method="post" modelAttribute="categoryMerge">
        <form:errors path="" />
        <table class="table">
            <tr>
                <td><label for="targetId">Target category:</label></td>
                <td><form:select path="targetId" class="form-control" items="${categoriesMap}" /><form:errors path="targetId" /></td>
            </tr>
            <tr>
                <td><label for="mergeWithIds">Merge with:</label></td>
                <td><form:checkboxes path="mergeWithIds" items="${categoriesMap}" delimiter="<br />" /><br /><form:errors path="mergeWithIds" /></td>
            </tr>
        </table>
        <div class="right-align">
            <button type="submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span> Save
            </button>
        </div>
        <form:errors />
    </form:form>
    <a href="<c:url value="/category" />"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to all</a>

</l:template>
