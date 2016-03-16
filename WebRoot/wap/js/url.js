define(function(require,exports,module){
	var baseUrl = 'http://tangsonsgroup.com';
        baseUrl = 'http://service.tangsons.cn/';
	var URL = window.URL = this.URL = {
        domain:baseUrl,
        loginUrl:baseUrl+'/Login.asmx/UserLogin',
        registerUrl:baseUrl+'/Login.asmx/Register'
	}
    module.exports = URL;
});