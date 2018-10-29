<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
<head>
    <title>秒列表页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>秒列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>创建时间</th>
                            <th>详情页</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sp" items="${list}">
                            <tr>
                                <td>${sp.name}</td>
                                <td>${sp.number}</td>
                                <td><fmt:formatDate value="${sp.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${sp.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${sp.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><a class="btn btn-info" href="${pageContext.request.contextPath}/secpay/${sp.secpayId}/detail" target="_blank">Link</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
