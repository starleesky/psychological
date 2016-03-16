define(function(require,exports,module){
    
   var $ = require('jquery');
   	 

    function Slide(object, options) {
        this.$object = $(object);
        this.options = $.extend({}, Slide.data.def, options);
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
    Slide.data = {
        def: {
            time: 3000,
            bool: true,
            page: 1,
            isTransverse:false,
            isItemClick:false, 
            beforeCallback: null
        }
    };

    /**
     * 取值
     * @param {String} key
     */
    Slide.prototype.get = function(key) {
        return this.options[key];
    };

    /**
     * 赋值
     * @param {String} key
     * @param {String | Number} val
     */
    Slide.prototype.set = function(key, val) {
        this.options[key] = val;
    };

    /**
     * 初始化
     */
    Slide.prototype.init = function() {
        var self    = this,
            $object = self.$object,
            $items = $object.find("li"),
            buffer = "";
            
            $object.addClass("slide-player");
            
             $items.each(function(i) {
                buffer += '<a href="javascript:;" title="">' + (i + 1) + '</a>';
            });
        if(self.options.isTransverse){
            self.range = $items.width();
            $object.parent().css({
                "width" : self.get("width")  || $object.width,
                "height": self.get("height") || $object.height, 
                "overflow":"hidden"
            });
            $object.css({
                "width":$items.width()*$items.length*2,
                "height":self.get("height") || $items.height(),
                "position":"relative"
            });
            $object.append($object.html()); 
            $items = $object.find("li");
        }else{ 
            $object.css({
                "width" : self.get("width")  || $object.width,
                "height": self.get("height") || $object.height
            });
            
        } 
            
        $items.addClass("slide-item");
       
        
        this.$page  = $('<div class="slide-page">' + buffer + '</div>').insertAfter($object);
        this.$pages = this.$page.find("a");
        this.$items = $items;
        
        if(self.options.isAddBtn){
           this.$addBtn = $('<a class="arrow-l jImgLeft" href="javascript:;"><em></em></a><a class="arrow-r jImgRight" href="javascript:;"><em></em></a>').insertAfter($object);
        }
        
    };

    /**
     * 事件绑定
     */
    Slide.prototype.bind = function() {
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
        
        $addBtn &&  $addBtn.hover(function(){
            self.auto(false);
        },function(){
            if(self.options.bool)
                self.auto(true);
        });
        
        $addBtn && $addBtn.on('click',function(){
             
            page  = self.get("page"); 
            if($(this).hasClass('arrow-l')){
                (page == 1) ? page = $pages.length+1 : ""; 
                self.fade(page-1);                
            }else{
                self.fade(page+1);
            }   
        });
        
        if(self.options.isItemClick){
            $items.on('click',function(){
                self.fade($(this).index());
            });
        }
        
        self.fade(page);
        
        // 开启自动播放
        if(self.options.bool)
            self.auto(true); 
    }; 
        
    /**
     * 滚动效果
     * @param {Number} current
     */
    Slide.prototype.fade = function(current) {
        var self   = this,
            page   = self.get("page"),
            $pages = self.$pages,
            $items = self.$items,
            beforeCallback = self.options.beforeCallback ? self.options.beforeCallback : null,
            afterCallback = self.options.afterCallback ? self.options.afterCallback : null;
         
        //轮播前回调事件
        beforeCallback && beforeCallback(self); 
        
        if(self.options.isTransverse){
            if(current > $pages.length){ 
               self.$object.css({
                    left:'0px'
                });
                current = 1;
            }else if(current==1){
                self.$object.css({
                    left:-(($pages.length+1)*parseInt(self.$items.width()))+'px'
               });
               current = $pages.length+1;
            }            
            $items.eq(current).addClass('on').siblings().removeClass('on');
           // console.log(current+' this_page:'+page+' pages:'+$pages.length);
             self.$object.stop(true, true).animate({
                 left : -(current*parseInt(self.$items.width()))+'px'
             },300,function(){ 
                
                afterCallback && afterCallback(self); 
             });
             
        }else{
             // 超出置零
            (current > $pages.length) ? current = 1 : "";
            // 重置幻灯
            $items.eq(page - 1).stop(true, true);
            $items.eq(page - 1).fadeOut(function() {
                $(this).css("zIndex", 1);
            });
            $items.eq(current - 1).css("zIndex", 3).fadeIn(function(){
                afterCallback && afterCallback(self);
            });
        }
        // 重置翻页
        $pages.eq(page - 1).removeClass("trigger");
        $pages.eq(current - 1).addClass("trigger");
        
        // 记录页数
        self.set("page", current);
    };

    /**
     * 自动播放
     * @param {Boolean} toggle
     */
    Slide.prototype.auto = function(toggle) {
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
    
    return Slide;
});