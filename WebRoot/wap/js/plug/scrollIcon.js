/**
 *
 */
define(function(require,exports,module){
    function scrollIcon(opts){
        this.init(opts);
    }

    scrollIcon.prototype = {

        init:function(opts){
            var self = this;
            var defaults = {
                iconNum:1
            };
            self.opts = $.extend(defaults,opts);

            this.creatDom(self.opts.iconNum);
            this.bind();
        },
        creatDom:function(num){

            var strHtml = '<div style="position: fixed;bottom:20px;right: 30px;width:50px;">'+((num>1)?'<a class="icon iconfont scrollBt" style="display: block;width:50px;height:60px;color: #A0A0A0;font-size: 40px;text-align: center;">&#xe610;</a>':"");
                strHtml +='<a class="icon iconfont scrollTop" style="display: block;width:50px;height:50px;color: #A0A0A0;font-size: 40px;text-align: center;">&#xe611;</a></div>';
            $('body').append(strHtml);
        },
        bind:function(){
            $('.scrollTop').on('click',function(e){
                e.preventDefault();
                window.scrollTo(0,0);
            });
            $('.scrollBt').on('click',function(e){
                var height = $(document).height();
                e.preventDefault();
                window.scrollTo(0,height);
            })
        }
    };

    //对外提供接口
    module.exports = function (opts){
        return new scrollIcon(opts);
    };
});