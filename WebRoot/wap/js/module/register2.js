define(function(require,exports,module) {

    var $ = require('plug/jquery2');
    var ajax = require('plug/ajax');
    var Validator = require('plug/validate/validateMethod');
    var box = require('plug/box');
    var url = require('url');

    Validator.validate('#register2', {
        rules: {
        	provinceId: {
                required: true
            },
            cityId: {
            	required: true
            },
            businessScope: {
                required: true
            },
            businessNature:{
				required : true
			}
        },
        messages: {
        	provinceId: {
                required: '此项不能为空'
            },
            cityId: {
            	required: '此项不能为空'
            },
            businessScope: {
                required: '此项不能为空'
            },
            businessNature:{
				required :  '此项不能为空'
			}
        },
        submitHandler: function (form) {
            $.post( url.saveRegister2,
              {
            	provinceId: $(form).find('select[name=provinceId]').val(),
            	cityId: $(form).find('select[name=cityId]').val(),
            	businessScope: $(form).find('select[name=businessScope]').val(),
            	businessNature: $(form).find('select[name=businessNature]').val(),
            	id: $(form).find('input[name=id]').val()
              }
        	, function (data) {
        		if(data.result){
        			window.location.href =url.registerSuccess;
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
    	$('#register2').submit();
    });
});