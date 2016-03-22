define(function(require,exports,module){

    var $ = require('jquery');
    var ajax = require('plug/ajax');
    var url = require('url');
    var Lazyload = require('plug/load/lazyload');
    var LazyStream = require('plug/load/lazystream');

    var $view = $('.page-view-body')

    $view.on('click','.op-button-higSearch',function(){
        $view.find('.search-form').show();
    });

    $view.on('click','.op-button-return',function(){
        $view.find('.search-form').hide();
    });

    var lazyMore = new LazyStream('.jPage', {
        plUrl: url.demoPageUrl,
        paramFormater: function(n) {
            var data = {};
            data.pageNo = n;
            data.isAjax = '1';
            data.groupsType = $('input[name=group-type]').val();
            return data;
        },
        page:2,
        errorText: '<div class="loading">网络错误，点击重试</div>',
        loadingClass: 'loading',
        loadingText: '<div class="loading"><img src="../images/loading2.gif" class="load-gif" />正在加载，请稍后...</div>',
        load: function(el) {
            Lazyload.load($(el).find('.jImg'));
        },
        noAnyMore:'<div class="loading">sorry,已经没有下一页了...</div>'
    });


});