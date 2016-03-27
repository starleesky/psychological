define(['jquery', 'url', 'plug/ajax','plug/box','plug/load/lazyload','plug/load/lazystream'], function ($, url, ajax,box,Lazyload,LazyStream) {

	//根据当前选中的信息状态，设定class=current
	var curStatus = $("#curStatus").val();
	var curClass = function() {
		
		$("a[status='" + curStatus + "']").attr("class","current");
	}
	
	curClass();
	
	$('body').on('click','.jNewUp',function(){
        var url = $(this).attr('data-url');
        box.confirm('是否确认重新上架',function(){
            window.location.href = url;
        },function(){},this);
    });

    //var Lazyload = require(['plug/load/lazyload']);
    //var LazyStream = require(['plug/load/lazystream']);

    var $view = $('.page-view-body')

    $view.on('click','.op-button-higSearch',function(){
        $view.find('.search-form').show();
    });

    $view.on('click','.op-button-return',function(){
        $view.find('.search-form').hide();
    });

    var lazyMore = new LazyStream('.jPage', {
        plUrl: url.moreInfo ,
        paramFormater: function(n) {
            var data = {};
            data.pageNo = n;
            data.isAjax = '1';
            data.groupsType = $("input[name='group-type']").val();
            data.status = curStatus;
            return data;
        },
        
        page:1,
        
        errorText: '<div class="loading">网络错误，点击重试</div>',
        loadingClass: 'loading',
        loadingText: '<div class="loading"><img src="' +ctx + '/wap/images/loading2.gif" class="load-gif" />正在加载，请稍后...</div>',
        //
        load: function(el) {
            Lazyload.load($(el).find('.jImg'));
        },
        noAnyMore:'<div class="loading">sorry,已经没有下一页了...</div>'
    });
});