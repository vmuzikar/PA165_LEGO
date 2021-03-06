<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<l:template title="Bricks">
    
    <div class="controls clearfix">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="pull-left mobile-full">
                <a href="<c:url value="/brick/create" />" class="btn btn-primary mobile-full"><span
                        class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create</a>
            </div>
        </sec:authorize>
        <div class="pull-right mobile-full">
            <a href="<c:url value="/brick" />"
               class="btn btn-primary mobile-full<c:if 
                   test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/brick')}"> disabled</c:if>">All</a><a
               href="<c:url value="/brick/unused" />" 
               class="btn btn-primary spaced mobile-full<c:if 
                   test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/unused')}"> disabled</c:if>">Unused</a>
            <div class="input-group pull-right mobile-full">
                <input type="number" class="form-control inline" id="amount" value="3" min="1" max="99">
                <span class="input-group-btn">
                    <a href="<c:url value="/brick/most-used" />/" 
                       class="btn btn-primary amount-dependent">most used</a>
                </span>
            </div>
        </div>
    </div>

    <c:choose>
        <c:when test="${not empty bricks}">
            <table id="list" class="display table table-striped" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Size</th>
                        <th> </th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Name</th>
                        <th>Size</th>
                        <th> </th>
                    </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${bricks}" var="brick">
                    <tr>
                        <td><c:out value="${brick.name}" /></td>
                        <td>${brick.width} &times; ${brick.height}</td>
                        <td class="right-align">
                            <a href="<c:url value="/brick/${brick.id}" />"><span 
                                    class="glyphicon glyphicon-folder-open" 
                                    aria-hidden="true"></span><span 
                                    class="sr-only">details</span></a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="<c:url value="/brick/${brick.id}/edit" />"><span
                                        class="glyphicon glyphicon-pencil"
                                        aria-hidden="true"></span><span
                                        class="sr-only">edit</span></a>

                                <a href="<c:url value="/brick/${brick.id}/delete" />" class="link-del"><span
                                        class="glyphicon glyphicon-trash"
                                        aria-hidden="true"></span><span
                                        class="sr-only">delete</span></a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p><strong>No bricks found!</strong></p>
        </c:otherwise>
    </c:choose>

</l:template>
