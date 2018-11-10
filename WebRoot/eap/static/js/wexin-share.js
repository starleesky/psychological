$(document).ready(function () {
    // 提供微信分享接口所需的参数
    var wxdata = {
        wx_account: new Array(4),
        wx_share: new Array(4),

        get_wxshare_data: function (ctx) {
            var url = ctx + "/wxShare/getWxShareData";
            $.ajax({
                type: "GET",
                url: url,
                dataType: "json",
                cache: false,
                async: false,
                success: function (msg) {
                    msg=msg.object
                    if (msg.errcode == 0) {
                        wxdata.wx_account[0] = msg.wxuser;  // appid
                        wxdata.wx_account[1] = msg.timestamp;   // timestamp
                        wxdata.wx_account[2] = msg.noncestr; // noncestr
                        wxdata.wx_account[3] = msg.signature;  // signature

                        wxdata.wx_share[0] = msg.shareimg;  // share_img 分享缩略图图片
                        wxdata.wx_share[1] = msg.shareurl;// share_link  分享页面的url地址，如果地址无效，则分享失败
                        wxdata.wx_share[2] = msg.description
                        wxdata.wx_share[3] = msg.title;// share_title
                    }
                },
                error: function (msg) {

                }
            });
        }

    }

    var host = "http://" + window.location.host;
    wxdata.get_wxshare_data(host);


    // 微信分享默认调用接口

    var $wx_account = wxdata.wx_account,
        $wx_share = wxdata.wx_share;

    //配置微信信息
    wx.config({
        debug: false,
        appId: $wx_account[0],
        timestamp: $wx_account[1],
        nonceStr: $wx_account[2],
        signature: $wx_account[3],
        jsApiList: [
            // 所有要调用的 API 都要加到这个列表中
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo'
        ]
    });
    wx.ready(function () {
        // 微信分享的数据
        var shareData = {
            "imgUrl": $wx_share[0],
            "link": $wx_share[1],
            "desc": $wx_share[2],
            "title": $wx_share[3],
            success: function () {
                // 分享成功可以做相应的数据处理
            }
        };
        wx.onMenuShareTimeline(shareData);
        wx.onMenuShareAppMessage(shareData);
        wx.onMenuShareQQ(shareData);
        wx.onMenuShareWeibo(shareData);
    });
})