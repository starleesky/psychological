define(['jquery','plug/box','plug/imgLoading','plug/selectPro'],function ($,box){
    var box = require('../plug/box');
    $('body').on('click','.jUpPro',function(){
        var url = $(this).attr('data-url');
        box.confirm('是否确认修改商品！',function(){
            window.location.href = url;
        },function(){},this);
    });
});