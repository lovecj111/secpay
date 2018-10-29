<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒详情页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-heading">
                <h1>${secPay.name}</h1>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <!-- 显示time图标 -->
                    <span class="glyphicon glyphicon-time"></span>
                    <!-- 展示倒计时 -->
                    <span class="glyphicon" id="secpayBox"></span>
                </h2>
            </div>
        </div>
    </div>

    <!-- 登录弹出层，输入电话 -->
    <div id="payPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>秒电话：
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="payphone" id="payphoneKey"
                                   placeholder="填手机号^O^" class="form-control" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="payphoneMessage" class="glyphicon"></span>
                    <button type="button" id="payPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span> Submit
                    </button>
                </div>
            </div>
        </div>
    </div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery cookie操作插件 -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQery countDonw倒计时插件  -->
<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<!-- 开始编写交互逻辑 -->
<script src="${pageContext.request.contextPath}/resources/js/secpay.js"  type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        secpay.detail.init({
            secpayId : ${secPay.secpayId},
            startTime : ${secPay.startTime.time},//毫秒
            endTime : ${secPay.endTime.time}
        });
    });
</script>
</html>
