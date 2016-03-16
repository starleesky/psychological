define(['jquery','plug/box','plug/imgLoading','plug/selectPro'],function ($,box){
       var $body = $('body');
    $body.on('click','.jDelImg',function(){
        var $img = $(this).parents('.info').find('img');
        box.confirm('是否确认删除图片',function(){
            $img.attr('src','');
        },function(){},$img[0]);

    });
});