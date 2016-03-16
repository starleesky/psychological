define(function(require,exports,module){
    
   var $ = require('jquery');
   
   var ajax = {
       init: function(options,okFn,errFn,btn){
         var self = this;
			 if (btn) {
	            btn = $(btn);
	            if (btn.hasClass('btn-disabled')) {
	                return;
	            }
	            btn.addClass('btn-disabled');
	            btn.prop('disabled', true);
	        }
	
	        function _removeBtnDisabled() {
	            if (btn) {
	                btn.removeClass('btn-disabled');
	                btn.prop('disabled', false);
	            }
	        }
	
	        function _getJson(xhr) {
	            var resJson = xhr;
	            if (xhr && xhr.responseJSON) {
	                resJson = resJson.responseJSON;
	            } else if (xhr && xhr.responseText) {
	                try {
	                    resJson = $.parseJSON(xhr.responseText);
	                } catch (e) {
	                    resJson = null;
	                }
	            }
	            return resJson;
	        }
	
	        var defaluts = {
	            url: '',
	            type: 'GET',
	            data: {},
	            dataType: 'json',
	            timeout: 10000,
	            cache: false,
	            success: function(xhr) {
	                var resJson = _getJson(xhr);
	                if (resJson && resJson._error) {
	                    errFn && errFn(resJson);
	                } else {
	                    okFn && okFn(resJson);
	                }
	            },
	            error: function(xhr) {
	                //正确
	                if (xhr.status == '200') {
	                    okFn && okFn();
	                } else {
	                    var resJson = _getJson(xhr);
	                    if (!(resJson && resJson._error)) {
	                        resJson = {
	                            _error: {
	                                code: '-1',
	                                msg: '网络错误，请重试！'
	                            }
	                        };
	                    }
	                    errFn && errFn(resJson);
	                }
	            },
	            complete: function() {
	                _removeBtnDisabled();
	            }
	        };
	        var opt = $.extend(defaluts, options || {});
            $.ajax(opt);
       },
        get: function(options,okFn,errFn,btn) {
			var defaluts = {
	                type: 'GET'
            },opt = $.extend(defaluts, options || {});
            ajax.init(opt,okFn,errFn,btn);
        },
        post: function(options,okFn,errFn,btn) {
            var defaluts = {
                type: 'POST'
            },opt = $.extend(defaluts, options || {});
            ajax.init(opt,okFn,errFn,btn);
        },
        jsonp: function(options,okFn,errFn,btn) {
            var defaluts = {
                dataType: 'jsonp',
                jsonp: "callback"
            },opt = $.extend(defaluts, options || {});
            ajax.init(opt,okFn,errFn,btn);
        }
   }
   
   module.exports = ajax;
});