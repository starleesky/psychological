define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');

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
            ajax.jsonp({
                url: url.loginUrl,
                data: {
                    op: 'UserLogin',
                    UserName: $(form).find('input[name=UserName]').val(),
                    Pwd: $(form).find('input[name=Pwd]').val()
                }
            }, function (data) {
                box.ok(data);
            }, function (data) {
                box.error(data);
            });
        },
        showError:function(elem,msg){
            box.error(msg,elem);
        },
        success:null
    });

    $('#jSubmit').on('click',function(){
       $('#loginForm').submit();
    });

});
