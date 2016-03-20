define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var url = require('url');

    Validator.validate('#register', {
        rules: {
            email: {
                required: true,
                email:true
            },
            mobile: {
            	required: true,
            	mobile:true
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
            email: {
                required: '此项不能为空',
                email:'邮箱格式不正确'
            },
            mobile: {
            	required: '此项不能为空',
            	mobile:'手机号格式不正确'
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
            $.post( url.registerUrl,
              {
            	email: $(form).find('input[name=email]').val(),
                mobile: $(form).find('input[name=mobile]').val(),
                password: $(form).find('input[name=password]').val()
              }
        	, function (data) {
        		if(data.result){
        			window.location.href =url.loginSuccessUrl;
        		}else{
        			console.log(data);
        		}
            },'json');
        
        }
    });
    $('#jSubmit').on('click',function(){
    	$('#register').submit();
    });
});