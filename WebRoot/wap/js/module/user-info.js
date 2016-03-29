define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var $body = $('body');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');

    
    var oRegionProvice = $(".regionProvice");
    var oRegionCity = $(".regionCity");
    
    
    Validator.validate('#userInfo', {
        rules: {
            mobile: {
                required: true
            },
            realName: {
                required: true,
                minlength: 6
            }
        },
        messages: {
        	mobile: {
                required: '此项不能为空'
            },
            realName: {
                required: '此项不能为空',
                minlength: '密码不少于6位'
            }
        },
        submitHandler: function (form) { 
        	console.log($("#userInfo").serialize());
            $.post( url.userInfoUpdate,
            $("#userInfo").serialize(),
            function (data) {
        		if(data.success){
        			if(confirm(data.message))
        			 {
        				window.location.href =url.loginIndexUrl;
        			 }
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

    $body.on('click','input[name=upPwd]',function(){
       this.checked?$('.pwd').show():$('.pwd').hide();
    });
    
    $('#jSubmit').on('click',function(){
        $('#userInfo').submit();
     });
    
    //初始化
    var getProvice = function () {
        $.getJSON(url.listRegion, {id: '0'}, function (data) {
            var oProvice_html;
            $.each(data.object, function (i, data) {
                oProvice_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            });
            oRegionProvice.html(oProvice_html);
            getCity();
        });
    }
    
    oRegionProvice.change(function () {
        getCity();
    });
    getProvice();
    var getCity = function () {
        var n = oRegionProvice.val();
        $.getJSON(url.listRegion, {id: n}, function (data) {
            var oCity_html;
            $.each(data.object, function (i, data) {
                oCity_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            });
            oRegionCity.html(oCity_html);
        });
    };
});