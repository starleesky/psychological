define(['jquery','plug/box','plug/imgLoading','plug/selectPro'],function ($,box){

    $('body').on('click','.jNewUp',function(){
        var url = $(this).attr('data-url');
        box.confirm('是否确认重新上架',function(){
            window.location.href = url;
        },function(){},this);
    });
});