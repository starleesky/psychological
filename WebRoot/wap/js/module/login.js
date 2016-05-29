
define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');
//    var cache = require('plug/cache');
//    var userName = cache.getCookie('userName'),pwd = cache.getCookie('password');

//    if(userName!=''){
//        $(form).find('input[name=UserName]').val(userName);
//        $(form).find('input[name=Pwd]').val(pwd);
//    }
    Validator.validate('#loginForm', {
        rules: {
            UserName: {
                required: true
            },
            Pwd: {
                required: true,
                minlength: 6
            }
        },
        messages: {
            UserName: {
                required: '此项不能为空'
            },
            Pwd: {
                required: '此项不能为空',
                minlength: '密码不少于6位'
            }
        },
        submitHandler: function (form) {          
            $.post( url.loginUrl,
              {
                    userName: $(form).find('input[name=UserName]').val(),
                    password: $(form).find('input[name=Pwd]').val()
            }
        	, function (data) {
        		if(data.result){
//        			cache.setCookie('userName',$(form).find('input[name=UserName]').val());
//                    cache.setCookie('password',$(form).find('input[name=Pwd]').val());
        			window.location.href =url.loginIndexUrl;
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
       $(this).parents('form').submit();
    });



});
