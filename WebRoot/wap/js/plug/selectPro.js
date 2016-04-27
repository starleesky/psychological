define(function(require, exports, module) {
    'use strict';

    var $ = require('jquery');
    var box = require('plug/box');
    var $body = $('.page-view-body');
    var url = require('url');


    $body.on('click','.jProSelect',function(){
        var num = 0;
        if($(this).hasClass('isSelect')){
            $(this).attr('src',ctx+'/wap/images/ok-1.png').removeClass('isSelect');
            $(this).siblings('input').val(0);
        }else{
            $(this).attr('src',ctx+'/wap/images/ok-2.png').addClass('isSelect');
            $(this).siblings('input').val(1);
            /*
            var id = $(this).attr("infoId");
        	var status = $("#curStatus").val();
        	var infomation = new Object();
        	if(status == '2') {
        		infomation = {id:id,status:'3'};
        	}else {
        		infomation = {id:id,deleted:'T'};
        	}
        	*/
        	/*
        	$.getJSON(url.updateInfo, infomation, 
        	function(data) {
			});
			*/
        	
        }
        num = $body.find('.isSelect').length;
        num == 0 ? $body.find('.pro-select-info').hide():$body.find('.pro-select-info').show();
        $body.find('.jProSelMsg span').html(num);
        
        var infoIds = "";
        $.each($body.find('.isSelect'),function(i,data) {
        	infoIds += $(data).attr("infoid") + ",";
        });
        if(infoIds != "") {
        	infoIds = infoIds.substring(0,infoIds.length-1);
        }
        $("#infoIds").val(infoIds);
    });

    $body.on('click','.jCloseProSel',function(){
        $body.find('.pro-select-info').hide();
        $body.find('.pro-select').hide();
    });

    $body.on('click','.jSelPro',function(){
         $body.find('.pro-select').show();
        $body.find('.pro-select-info').show();

    });
    
    $body.on('click','.jProSelMsg',function(){
    	var opt = {};
    	if(status == '2') {
    		opt = {oper:"ys",infoIds : $("#infoIds").val()};	//oper : ys 改为已售	
    	}else {
    		opt = {oper:"sc",infoIds : $("#infoIds").val()};	//oper : sc 改为删除
    	}
    	
    	$.getJSON(url.updateInfo, opt, 
            	function(data) {
		    		var order = $("select[name='orderSel']").val();
		        	window.location.href = url.infoList + "?order="
					+ order + "&status="
					+ $("#curStatus").val();
    			}
    	);
    });
});