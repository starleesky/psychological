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
			},
			isAgree : {
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
			},
			isAgree : {
				required : "您还未接受同意条款"
			}
        },
        submitHandler: function (form) {
            $.post( url.saveRegister2,
              {
            	provinceId: $(form).find('select[name=provinceId]').val(),
            	provinceName: $(form).find('select[name=provinceId]').find("option:selected").text(),
            	cityId: $(form).find('select[name=cityId]').val(),
            	cityName: $(form).find('select[name=cityId]').find("option:selected").text(),
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

    var oRegionProvice = $(".regionProvice");
    var oRegionCity = $(".regionCity");
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

    //初始省市
    getProvice();
});