define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');

    Validator.validate('#registerMobile', {
        rules: {
            mobile: {
                required: true,
                mobile:true
            },
            smsCode: {
                required: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength :16,
            },
            confirmPwd:{
                required : true,
                minlength : 6,
                equalTo : "#password"
            }
        },
        messages: {
            mobile: {
                required: '此项不能为空',
                mobile:'手机号格式不正确'
            },
            smsCode: {
                required: "请输入验证码"
            },
            password: {
                required: '此项不能为空',
                minlength: '密码不少于6位',
                maxlength: '密码不大于16位'
            },
            confirmPwd:{
                required :  '此项不能为空',
                minlength : "密码不少于6位",
                equalTo : "两次输入密码不一致"
            }
        },
        submitHandler: function (form) {
            $.post( url.registerMobile,
              {
                realName: $(form).find('input[name=realName]').val(),
                mobile: $(form).find('input[name=mobile]').val(),
                password: $(form).find('input[name=password]').val(),
                smsCode: $(form).find('input[name=smsCode]').val()

              }
            , function (data) {
                if(data.result){
                    window.location.href =url.register2Url;
                }else{
                    box.ok(data.message);
                }
            },'json');
        
        },
        showError:function(elem,msg){
            box.error(msg,elem);
        },
        success:null
    });
    $('#jSubmit').on('click',function(){
        $('#register').submit();
    });

    $('#jSubmitMobile').on('click',function(){
        console.log(33)
        $('#registerMobile').submit();
    });
    
    //短信发送
    function sendMsg($obj,time){
        $obj.text(time+'秒后重新发送');
        if(time==0){
            $obj.addClass('ui-button-blue').removeClass('ui-button-disabled').text('发送短信');
            return false;
        }
        setTimeout(function(){
            time--;
            sendMsg($obj,time);
        },1000);
    }
    $('body').on('click','.jSendMsg',function(){
        var mobile = $("#mobile").val();
        var smsCode = $("#smsCode").val();
        if(!mobile){
            box.ok("请填入手机号");
            return false;
        }
        if(!$(this).hasClass('ui-button-disabled')){
            $(this).addClass('ui-button-disabled').removeClass('ui-button-blue').text('60秒后重新发送');
            sendMsg($(this),60);
            //发送短信接口请求
            $.get(url.smsCodeUrl,{mobile:$("#mobile").val()},function(data){
                console.log(data);
            })
        }
    });
});