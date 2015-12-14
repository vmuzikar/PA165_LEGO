<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<l:template title="Bricks">
    
    <div class="controls clearfix">
        <div class="pull-left">
            <a href="<c:url value="/brick/create" />" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create</a>
        </div>
        <div class="pull-right">
            <div class="input-group">
                <span class="input-group-btn">
                    <button class="btn btn-primary">Show</button>
                </span>
                <input type="text" class="form-control inline" id="amount" placeholder="3">
                <div class="input-group-addon">most used</div>
            </div>
        </div>
        <a href="<c:url value="/brick/unused" />" class="btn btn-primary">Show unused</a>
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
                        <td class="right-align"><a href="<c:url value="/brick/${brick.id}" />" ><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><span class="sr-only">details</span></a></td>
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
