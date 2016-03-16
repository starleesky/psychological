define(['jquery','url','plug/ajax'],function($,url,ajax){

    define(function(require,exports,module) {

        var $ = require('jquery');
        var ajax = require('plug/ajax');
        var Validator = require('plug/validate/validateMethod');
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
                    url: url.registerUrl,
                    data: {
                        op: 'UserLogin',
                        UserName: $(form).find('input[name=UserName]').val(),
                        Pwd: $(form).find('input[name=Pwd]').val()
                    }
                }, function (data) {
                    console.log(data);
                }, function (data) {
                    console.log(data);
                });
            }
        });


    });

});