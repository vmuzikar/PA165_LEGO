<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<l:template title="${create ? 'Create' : 'Edit'} kit">

    <c:if test="${create}"><c:url value="/kit/create" var="action" /></c:if>
    <form:form method="post" modelAttribute="kit" action="${action}">
        <table class="table">
            <tr>
                <td><label for="name">Name:</label></td>
                <td><form:input path="name" class="form-control" /><form:errors path="name" element="p" /></td>
            </tr>
            <tr>
                <td><label for="categoryId">Category:</label></td>
                <td>
                    <form:select path="categoryId" class="form-control" items="${categoriesMap}" />
                    <form:errors path="categoryId" element="p" />
                </td>
            </tr>
            <tr>
                <td><label for="bricksIds">Brick types:</label></td>
                <td>
                    <form:checkboxes path="bricksIds" items="${bricksMap}" delimiter="<br />" />
                    <form:errors path="bricksIds" element="p" />
                </td>
            </tr>
            <tr>
                <td><label for="price">Price:</label></td>
                <td>
                    <form:input path="price" />
                    <form:select path="currency" items="${currenciesMap}" />
                    <form:errors path="price" element="p" />
                    <form:errors path="currency" element="p" />
                </td>
            </tr>
            <tr>
                <td><label for="minAge">Age range:</label></td>
                <td>
                    <form:input path="minAge" /> -
                    <form:input path="maxAge" />
                    <form:errors path="minAge" element="p" />
                    <form:errors path="maxAge" element="p" />
                </td>
            </tr>
        </table>
        <div class="right-align">
            <button type="submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span> Save
            </button>
        </div>
        <form:errors />
    </form:form>
    <a href="<c:url value="/kit" />"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to all</a>

</l:template>
