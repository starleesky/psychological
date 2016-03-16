define(function(require,exports,module){

    var $ = require('jquery');


    function SlideImg(object, options) {
        this.$object = $(object);
        this.options = $.extend({}, SlideImg.data.def, options);
        this.init();
        this.bind();
    };

    /**
     * 数据
     * @type {Object}
     * @param {Number} time 倒计时
     * @param {Boolean} bool 是否自动播放
     * @param {Number} page 当前页
     * @param {Boolean} isTransverse 是否横向滚动
     * @param {Boolean} isItemClick 是否元素点击轮播
     * @param {Function} beforeCallback 每次轮播前回调事件
     * @param {Function} afterCallback 每次轮播前回调事件
     */
    SlideImg.data = {
        def: {
            time: 3000,
            bool: true,
            page: 1,
            isTransverse:false,
            btn:{next:null,prev:null},
            beforeCallback: null
        }
    };

    /**
     * 取值
     * @param {String} key
     */
    SlideImg.prototype.get = function(key) {
        return this.options[key];
    };

    /**
     * 赋值
     * @param {String} key
     * @param {String | Number} val
     */
    SlideImg.prototype.set = function(key, val) {
        this.options[key] = val;
    };

    /**
     * 初始化
     */
    SlideImg.prototype.init = function() {
        var self    = this,
            $object = self.$object,
            $items = $object.find("li"),
            buffer = "";

        $object.addClass("slide-player");

        $items.each(function(i) {
            buffer += '<a href="javascript:;" title="">' + (i + 1) + '</a>';
        });

        $object.find('ul').css({
            "width" : parseInt(self.get("width"))*$items.length  || parseInt($object.width)*$items.length,
            "height": self.get("height") || $object.height
        });

        $items.addClass("slide-item");


        this.$page  = $('<div class="slide-page">' + buffer + '</div>').insertAfter($object);
        this.$pages = this.$page.find("a");
        this.$items = $items;



    };

    /**
     * 事件绑定
     */
    SlideImg.prototype.bind = function() {
        var self  = this,
            page  = self.get("page"),
            $page = self.$page,
            $pages = self.$pages,
            $addBtn = self.$addBtn,
            $items = self.$items;

        $page.delegate("a[class!=trigger]", "mouseenter", function() {
            self.fade(parseInt(this.innerHTML));
        });

        $page.hover(function() {
            self.auto(false);
        }, function() {
            if(self.options.bool)
                self.auto(true);
        });

        function btnHover($btn){
            $btn.hover(function(){
                self.auto(false);
            },function(){
                if(self.options.bool)
                    self.auto(true);
            });
        }

        function btnClick($btn){
            $btn.on('click',function(){
                page  = self.get("page");
                if($(this).hasClass('arrow-l')){
                    (page == 1) ? page = $pages.length+1 : "";
                    self.fade(page-1);
                }else{
                    self.fade(page+1);
                }
            });
        }

        self.options.btn.next && btnHover($('.'+self.options.btn.next));
        self.options.btn.prev && btnHover($('.'+self.options.btn.prev));

        self.options.btn.next && btnClick($('.'+self.options.btn.next));
        self.options.btn.prev && btnClick($('.'+self.options.btn.prev));


        btnHover(self.$object);

        $items.on('click',function(){
            self.fade($(this).index()+1);
        });

        self.fade(page);

        // 开启自动播放
        if(self.options.bool)
            self.auto(true);
    };

    /**
     * 滚动效果
     * @param {Number} current
     */
    SlideImg.prototype.fade = function(current) {
        var self   = this,
            page   = self.get("page"),
            $pages = self.$pages,
            $items = self.$items,
            beforeCallback = self.options.beforeCallback ? self.options.beforeCallback : null,
            afterCallback = self.options.afterCallback ? self.options.afterCallback : null;

        // 超出置零
        if(current > $items.length){
            current = 1;
        }

        //轮播前回调事件
        beforeCallback && beforeCallback(self);

        $items.eq(current-1).addClass('on').siblings().removeClass('on');

        // 记录页数
        self.set("page", current);

        afterCallback && afterCallback(self);




    };

    /**
     * 自动播放
     * @param {Boolean} toggle
     */
    SlideImg.prototype.auto = function(toggle) {
        var self = this,
            time = self.get("time");

        if (toggle) {
            self.t = setInterval(function() {
                self.fade(self.get("page") + 1);
            }, time);
        } else {
            clearInterval(self.t);
        }

    };

    return SlideImg;
});