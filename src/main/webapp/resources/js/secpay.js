// javascript 模块化
var secpay = {

    URL : {
        now : function () {
            return getRootPath()+"/secpay/time/now";
        },
        exposer : function (secpayId) {
            return getRootPath()+"/secpay/"+secpayId+"/exposer";
        },
        execute : function (secpayId, md5) {
            return getRootPath()+"/secpay/"+secpayId+"/"+md5+"/execute";
        }
    },

    handleSecpay : function(secpayId, node){
        //得到秒地址、控制显示逻辑、执行秒
        node.hide().html('<button class="btn btn-primary btn-lg" id="payBtn">开始秒</button>');
        $.post(secpay.URL.exposer(secpayId), {}, function (result) {
            // 在回调函数中，执行交互流程
            if(result && result['result']){
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开启秒
                    var md5 = exposer['md5'];
                    var payUrl = secpay.URL.execute(secpayId, md5);
                    $('#payBtn').one('click', function () {
                        // 1.先禁用按钮
                        $(this).addClass('disabled');
                        // 2.发送秒请求
                        $.post(payUrl, {}, function (result) {
                            console.log('result=' + result);
                            if (result && result['result']) {
                                var payResult = result['data'];
                                var state = payResult['state'];
                                var stateInfo = payResult['stateInfo'];
                                // 3.显示秒结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                }else{
                    //未开启秒
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新计算计时逻辑
                    secpay.countdown(secpayId, now, start, end);
                }
            }else{
                console.log('result=' + result);
            }
        });
    },

    // 验证手机号
    validatePhone : function(phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    //倒计时
    countdown : function(secpayId, nowTime, startTime, endTime){
        //时间判断
        var secpayBox = $('#secpayBox');
        if(nowTime > endTime){
            secpayBox.html("秒结束");
        }else if(nowTime < startTime){
            //秒未开始、计时事件绑定
            var payTime = new Date(startTime+1000);
            secpayBox.countdown(payTime, function (event) {
                //时间格式
                var format = event.strftime("秒倒计时: %D天 %H时 %M分 %S秒");
                secpayBox.html(format);
                //时间完成、回调事件
            }).on('finish.countdown', function () {
                //秒开始
                secpay.handleSecpay(secpayId ,secpayBox);
            });
        }else{
            //秒开始
            secpay.handleSecpay(secpayId ,secpayBox);
        }
     },

    detail : {
        // 详情页初始化
        init : function (params) {
            // 用户手机验证和登录，计时交互
            // 规划我们的交互流程
            // 在cookie中查找手机号
            var payPhone = $.cookie('payPhone');
            // 验证手机号
            if (!secpay.validatePhone(payPhone)) {
                // 绑定phone
                // 控制输出
                var payPhoneModal = $('#payPhoneModal');
                payPhoneModal.modal({
                    show : true,// 显示弹出层
                    backdrop : 'static',// 禁止位置关闭
                    keyboard : false// 关闭键盘事件
                })
                $('#payPhoneBtn').click(function() {
                    var inputPhone = $('#payphoneKey').val();
                    if (secpay.validatePhone(inputPhone)) {
                        // 电话写入cookie
                        $.cookie('payPhone', inputPhone, {
                            expires : 7,
                            path : '/secpay'
                        });
                        // 刷新页面
                        window.location.reload();
                    } else {
                        $('#payphoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            //已经登录 计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var secpayId = params['secpayId'];
            $.get(secpay.URL.now(), {}, function (result) {
                if(result && result['result']){
                    var nowTime = result['data'];
                    //计时交互
                    secpay.countdown(secpayId, nowTime, startTime, endTime);
                }else{
                    console.log('result=' + result);
                }
            });
        }
    }

}

function getRootPath(){
    //获取当前网址，如： http://localhost:8088/projectName/index.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： projectName/index.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8088
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/projectName
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);
    return(localhostPath + projectName);
}