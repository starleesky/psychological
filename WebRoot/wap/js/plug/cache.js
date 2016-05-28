// tab switch
define(function(require, exports, module) {
    'use strict';

    var $ = require('jquery');
    var dialog = require('plug/dialog');

    module.exports = {
        valid:function(id){
            var bool = false;
            for(var i=0;i<sessionStorage.length;i++){
                var key = sessionStorage.key(i);
                if(key==this.key(id)){
                    bool = true;
                    break;
                }
            }
            return bool;
        },
        key: function (id) {
            return "cache[" + id + "]";
        },
        get: function (id) {
            var value = sessionStorage.getItem(this.key(id));
            return value && JSON.parse(value);
        },
        set: function (id, data) {
            // storage有容量上限，超出限额会报错
            try {
                sessionStorage.setItem(this.key(id), JSON.stringify(data));
            } catch (e) {
                dialog.error("超出本地存储容量上线，本次操作将不使用本地缓存");
            }
        },
        //写cookies
        setCookie:function(name,value,time){
            var strsec = this.getsec(time || "");
            var exp = new Date();
            exp.setTime(exp.getTime() + strsec*1);
            document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
        },
        getsec:function (str){
            var str1=str.substring(1,str.length)*1;
            var str2=str.substring(0,1);
            if (str2=="s"){
                return str1*1000;
            }else if (str2=="h"){
                return str1*60*60*1000;
            }else if (str2=="d"){
                return str1*24*60*60*1000;
            }
        },
        //读取cookies
        getCookie:function (name){
            var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
            if(arr=document.cookie.match(reg)) return unescape(arr[2]);
            else return null;
        },
        //删除cookies
        delCookie:function (name){
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cval=this.getCookie(name);
            if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
        }
    };
});
