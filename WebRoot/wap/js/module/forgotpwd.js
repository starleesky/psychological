define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');

    Validator.validate('#forgotForm', {
        rules: {
            email: {
                required: true,
                email:true
            },
            captchaCode: {
                required: true
            }
        },
        messages: {
        	email: {
                required: '此项不能为空',
                email:"请输入正确的邮箱"
                
            },
            captchaCode: {
                required: '此项不能为空'
            }
        },
        submitHandler: function (form) {          
            $.post( url.toForgotpwd,
              {
            		email: $(form).find('input[name=email]').val(),
                    captchaCode: $(form).find('input[name=captchaCode]').val()
            }
        	, function (data) {
        		if(data.success){
        			window.location.href =url.toForgotpwdSuccess;
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

    $("#captchaImage").click(function(){
		$(this).attr("src",$(this).attr("src")+"?"+Math.random());
	});
    
    $('#jSubmit').on('click',function(){
       $('#forgotForm').submit();
    });

});
