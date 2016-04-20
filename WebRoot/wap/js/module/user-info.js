define(['jquery','plug/box','plug/uploader/uploader','url','plug/validate/validateMethod','plug/imgLoading','plug/selectPro'],function ($,box,Uploader,url,Validator){

    var $ = require('plug/jquery2');
    var $body = $('body');
//    var ajax = require('plug/ajax');
//    var Validator = require('plug/validate/validateMethod');
//    var box = require('plug/box');
//    var img = require('plug/imgLoading');
//    var url = require('url');

    
    var oRegionProvice = $(".regionProvice");
    var oRegionCity = $(".regionCity");
    
  //file uploader buton installations  失败：-1 初始值0 正在上传1 成功2
    var uploadArray = [];
    $('[node-type="uploadButton"]').each(function(i, el) {
        uploadArray[i] = 0;
        var $el = $(el),
            fieldName = $el.data('name'),
            fieldInput = $('<input type="hidden" id = "headIcon" name="headIcon" />');

        $el.append(fieldInput);

        // initialize file upload component
        var uploader = Uploader(el, {
            endpoint: url.demoUploadUrl
        });

        uploader.on('fileselect', function(e) {
            $el.find('.upload-txt').text('正在等待上传...');
            var html = '<div class="progressbar"><div></div></div>';
            $el.append(html);
            uploadArray[i] = 1;
        });

        uploader.on('uploadprogress', function(e) {
           // var percent = Math.min(e.percentLoaded, 99) + '%';
           //$el.find('.progressbar div').css('width', percent).text(percent);
            $el.find('.upload-txt').text('选择图片');
        });

        uploader.on('uploadcomplete', function(e) {
            var res = e.data || {};
            if (res.code == "1") {
                uploadArray[i] = 2;
                fieldInput.val(res.url);
                $el.find('.progressbar').remove();
            } else {
                var isFirst = true;
                var timer = setInterval(function() {
                    var eImg = $el.removeClass('file-uploaded').find('img');
                    var eDel = $el.find('.icon-delete');
                    if (isFirst && eImg.length > 0) {
                        box.tips(res.msg || '图片上传失败');
                        eImg.remove();
                        eDel.remove();
                        isFirst = false;
                        clearInterval(timer);
                    }
                }, 100);
            }
        });
        uploader.on('uploaderror', function(e) {
            uploadArray[i] = -1;
            box.tips('图片上传失败!');
        });

    });

    Validator.validate('#userInfo', {
        rules: {
            mobile: {
                required: true
            },
    		realName:{
    			required:true,
    			minlength:2
    		}
        },
        messages: {
        	mobile: {
                required: '此项不能为空'
            },
            realName:{
            	required:'此项不能为空',
            	minlength:'最少不能少于2位'
            }
        },
        submitHandler: function (form) { 
        	var hasChk = $('#checkbox').is(':checked');
        	if(!$("#realName").val()){
    	    	alert("真实姓名不能为空")
    	    	return false;
    	    }
    	    if($("#realName").val().length<2){
    	    	alert("真实姓名不能少于2位")
    	    	return false;
    	    }
        	if(hasChk){
        	    if(!$("#oldPassword").val()){
        	    	alert("旧密码不能为空")
        	    	return false;
        	    }
        	    if($("#oldPassword").val().length<6){
        	    	alert("旧密码不能少于6位")
        	    	return false;
        	    }
        	    if(!$("#password").val()){
        	    	alert("新密码密码不能为空")
        	    	return false;
        	    }
        	    if($("#password").val().length<6){
        	    	alert("新密码密码不能少于6位")
        	    	return false;
        	    }
        	    if(!$("#rePassword").val()){
        	    	alert("请再次输入新密码")
        	    	return false;
        	    }
        	    if($("#password").val() != $("#rePassword").val()){
        	    	alert("两次密码不一致")
        	    	return false;
        	    }
        	}
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
    
    var oEquipmentLocation = $("#equipmentLocation");

    //初始化
    var getProvice = function () {
        $.getJSON(url.listRegion, {id: '0'}, function (data) {
            var oProvice_html;
            var oldProvince = (oEquipmentLocation.val()).split("|")[0]
            $.each(data.object, function (i, data) {
            	console.log(data.regionName);
            	if(oldProvince == data.regionName) {
            		$("#provinceName").val(data.regionName)
            		oProvice_html += "<option value='" + data.id + "' selected>" + data.regionName + "</option>";
            	}else {
            		oProvice_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            	}
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
            var oldCity = (oEquipmentLocation.val()).split("|")[1];
            $.each(data.object, function (i, data) {
            	$("#cityName").val(data.regionName)
            	if(oldCity == data.regionName) {
            		oCity_html += "<option value='" + data.id + "' selected>" + data.regionName + "</option>";
            	}else {
            		oCity_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            	}
            });
            oRegionCity.html(oCity_html);
        });
    };
});