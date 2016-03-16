/**
 * path    : login
 * create  :  2016/03
 * author  :  muxing
 *
 */

define(function(require,exports,module) {
    //var Cookie = require('/resources/adminlib/utils/Cookie');
    //var password = require('ups/js/core/PasswordGenUtil');

    var C = function($scope, $http, target){
        this.$scope = $scope;
        this.$http = $http;
        this.formData = {username:'', password:'', target: target};
        //this.cookieName = Cookie.getCookie("UPS_user_name");
        //if(this.cookieName && this.cookieName.length > 0){
        //    this.formData.name = this.cookieName;
        //}
    };

    var p = C.prototype;
    /**
     * 密码加密的公式：random(4) + md5(md5(psw) + random(8)) + random(4)
     */
    p.loginSubmit = function(){
        //var originalPsw = this.formData.password;
        //this.formData.password = password.generate(originalPsw);
        var self = this;
        this.$http.post(angular.path+"/user/login", this.formData).success(function(resp) {
            if(resp.success){
                //Cookie.setCookie("UPS_user_name", self.formData.name, 720, "/");
                window.location.href =angular.path+ '/admin/main';
            }else{
                if(resp.code == 2){//用户密码是默认的初始密码，进入密码修改页
                    //window.location.href = '/ups/password?entryKey=' + resp.data + '&target=' + encodeURIComponent(self.formData.target);
                }else{
                    //self.formData.password = originalPsw;
                    alert(resp);
                }
            }
        });
    }

    module.exports = C;
});


