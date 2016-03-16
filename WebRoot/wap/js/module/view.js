define(function(require,exports,module) {

    var $ = require('jquery');
    var ajax = require('plug/ajax');
    var url = require('url');
    var $jImg = $('#jImgBox'),$bigBox = $('.img-big-box');
    $jImg.on('click','a',function(){
        var src = $(this).attr('data-url');
        $jImg.find('.big-img img').attr('src',src);
    });

    $jImg.on('click','.big-img img',function(){
        $bigBox.show();
        $bigBox.find('img').attr('src',this.src);
    });

    $bigBox.on('click','.jClose',function(){
        $bigBox.hide();
    });

});