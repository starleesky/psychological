define(function(require,exports,module){

    var $ = require('jquery');
    var ajax = require('plug/ajax');
    var url = require('url');

    var $view = $('.page-view-body')

    $view.on('click','.op-button-higSearch',function(){
           $view.find('.search-form').show();
    });

    $view.on('click','.op-button-return',function(){
        $view.find('.search-form').hide();
    });

    (function(){

    })();

});