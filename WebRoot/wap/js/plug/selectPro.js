define(function(require, exports, module) {
    'use strict';

    var $ = require('jquery');
    var box = require('plug/box');
    var $body = $('.page-view-body');


    $body.on('click','.jProSelect',function(){
        var num = 0;
        if($(this).hasClass('isSelect')){
            $(this).attr('src','../images/ok-1.png').removeClass('isSelect');
            $(this).siblings('input').val(0);
        }else{
            $(this).attr('src','../images/ok-2.png').addClass('isSelect');
            $(this).siblings('input').val(1);
        }
        num = $body.find('.isSelect').length;
        num == 0 ? $body.find('.pro-select-info').hide():$body.find('.pro-select-info').show();
        $body.find('.jProSelMsg span').html(num);
    });

    $body.on('click','.jCloseProSel',function(){
        $body.find('.pro-select-info').hide();
        $body.find('.pro-select').hide();
    });

    $body.on('click','.jSelPro',function(){
         $body.find('.pro-select').show();
        $body.find('.pro-select-info').show();

    });
});