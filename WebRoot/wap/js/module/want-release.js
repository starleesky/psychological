define(['jquery'], function ($) {

    var oBigGoodsCatagory = $(".bigGoodsCatagory");
    var oMiddleGoodsCatagory = $(".middleGoodsCatagory");
    //初始化产品大类
    $.getJSON(ctx + '/goodsCatagory/getGoodsCatagory', function (data) {
        var temp_html;
        $.each(data.object, function (i, data) {
            temp_html += "<option value='" + data.code + "'>" + data.name + "</option>";
        });
        oBigGoodsCatagory.html(temp_html);
    });

    oBigGoodsCatagory.change(function () {
        $.getJSON(ctx + '/goodsCatagory/getGoodsCatagory?', function (data) {
            var temp_html;
            $.each(data.object, function (i, data) {
                temp_html += "<option value='" + data.code + "'>" + data.name + "</option>";
            });
            oMiddleGoodsCatagory.html(temp_html);
        });
    });
});