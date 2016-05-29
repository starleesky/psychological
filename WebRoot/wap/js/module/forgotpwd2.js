define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');

    Validator.validate('#formMobile', {
        rules: {
            mobile: {
                required: true,
                mobile:true
            },
            captchaCode: {
                required: true
            }
        },
        messages: {
        	mobile: {
                required: '此项不能为空',
                mobile:"请输入正确的手机号"
                
            },
            captchaCode: {
                required: '此项不能为空'
            }
        },
        submitHandler: function (form) {          
            $.post( url.domain+'/wap/toForgotpwdBySms.htm',
              {
            		mobile: $(form).find('input[name=mobile]').val(),
                    captchaCode: $(form).find('input[name=captchaCode]').val()
              }, function (data) {
        		if(data.success){
        			window.location.href =url.domain+'/wap/login.htm';
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

    $('#jSubmitMobile').on('click',function(){
       $('#formMobile').submit();
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
