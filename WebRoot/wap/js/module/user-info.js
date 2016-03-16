define(['jquery','plug/box','plug/imgLoading','plug/selectPro'],function ($,box){
    var $body = $('body');
    $body.on('click','input[name=upPwd]',function(){
       this.checked?$('.pwd').show():$('.pwd').hide();
    });
});