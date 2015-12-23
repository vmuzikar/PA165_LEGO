<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<l:template title="Categories">
    
    <div class="controls clearfix">
        <div class="pull-left mobile-full">
            <a href="<c:url value="/category/create" />" class="btn btn-primary mobile-full"><span 
                    class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create</a>
            <a href="<c:url value="/category/merge" />" class="btn btn-primary mobile-full"><span
                    class="glyphicon glyphicon-link" aria-hidden="true"></span> Merge</a>
        </div>
        <div class="pull-right mobile-full">
            <a href="<c:url value="/category" />"
               class="btn btn-primary mobile-full<c:if 
                   test="${fn:endsWith(pageContext.request.contextPath, '/category')}"> disabled</c:if>">All</a><a 
               href="<c:url value="/category/unused" />" 
               class="btn btn-primary spaced mobile-full<c:if 
                   test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/unused')}"> disabled</c:if>">Unused</a>
            <div class="input-group pull-right mobile-full">
                <input type="number" class="form-control inline" id="amount" value="3" min="1" max="99">
                <span class="input-group-btn">
                    <a href="<c:url value="/category/most-used" />/" 
                       class="btn btn-primary amount-dependent">with most kits</a>
                </span>
            </div>
        </div>
    </div>
    
    <c:choose>
        <c:when test="${not empty categories}">
            <table id="list" class="display table table-striped" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td><c:out value="${category.name}" /></td>
                        <td class="right-align"><a href="<c:url value="/category/${category.id}" />" >
                            <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
                            <span class="sr-only">details</span>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="<c:url value="/category/${category.id}/edit" />"><span
                                        class="glyphicon glyphicon-pencil"
                                        aria-hidden="true"></span><span
                                        class="sr-only">edit</span></a>

                                <a href="<c:url value="/category/${category.id}/delete" />" class="link-del"><span
                                        class="glyphicon glyphicon-trash"
                                        aria-hidden="true"></span><span
                                        class="sr-only">delete</span></a>
                            </sec:authorize>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p><strong>No categories found!</strong></p>
        </c:otherwise>
    </c:choose>

</l:template>
