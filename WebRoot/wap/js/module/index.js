define(function(require,exports,module){

    var $ = require('jquery');
    var ajax = require('plug/ajax');
    var url = require('url');
    var _ = require('plug/imgLoading');
    var template = require('plug/template');

    require('plug/scrollTop')();

    var opts = {
        //获取产品数量
        GetSalesSumUrl:"/php/index/GetSalesSum.php",
        GetSalesTypeUrl:"/php/index/GetSalesType.php",
        GetRecommendProductUrl:"/php/index/GetRecommendProduct.php"
    };

    opts = $.extend(opts,window.TS);


});