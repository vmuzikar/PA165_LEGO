<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<l:template title="Kits">
    
    <div class="controls clearfix">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="pull-left mobile-full">
                <a href="<c:url value="/kit/create" />" class="btn btn-primary mobile-full"><span
                        class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create</a>
                <a href="<c:url value="/kit/converter" />" class="btn btn-primary mobile-full"><span
                        class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create from 3D model</a>
            </div>
        </sec:authorize>
        <div class="pull-right mobile-full">
            <a href="<c:url value="/kit" />"
               class="btn btn-primary mobile-full
                    <c:if test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/kit')}">
                        disabled
                    </c:if>">
                All
            </a>
            <a href="<c:url value="/kit/kids" />"
               class="btn btn-primary mobile-full
                    <c:if test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/kids')}">
                        disabled
                    </c:if>">
                For kids
            </a>
            <a href="<c:url value="/kit/teenage" />"
               class="btn btn-primary mobile-full
                    <c:if test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/teenage')}">
                        disabled
                    </c:if>">
                For teenagers
            </a>
            <a href="<c:url value="/kit/adults" />"
               class="btn btn-primary mobile-full
                    <c:if test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/adults')}">
                        disabled
                    </c:if>">
                For adults
            </a>
        </div>
    </div>

    <c:choose>
        <c:when test="${not empty kits}">
            <table id="list" class="display table table-striped" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Age range</th>
                        <th> </th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Age range</th>
                        <th> </th>
                    </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${kits}" var="kit">
                    <tr>
                        <td><c:out value="${kit.name}" /></td>
                        <td>${kit.categoryDto.name}</td>
                        <td>${kit.minAge}-${kit.maxAge}</td>
                        <td class="right-align">
                            <a href="<c:url value="/kit/${kit.id}" />"><span 
                                    class="glyphicon glyphicon-folder-open" 
                                    aria-hidden="true"></span><span 
                                    class="sr-only">details</span></a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="<c:url value="/kit/${kit.id}/edit" />"><span
                                        class="glyphicon glyphicon-pencil"
                                        aria-hidden="true"></span><span
                                        class="sr-only">edit</span></a>

                                <a href="<c:url value="/kit/${kit.id}/delete" />" class="link-del"><span
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
            <p><strong>No kits found!</strong></p>
        </c:otherwise>
    </c:choose>

</l:template>
