/**
 * path    : ups/js/core/PasswordGenUtil
 * create  :  2015/04
 * author  :  Winner
 * 
 */
define(function(require,exports,module) {
	var md5 = require('lib/utils/encrypt/MD5');
	
	var C = function($scope, $http, target){
		this.$scope = $scope;
		this.$http = $http;
		this.formData = {name:'', password:'', target: target};
	};
	
	/**
	 * 密码加密的公式：random(4) + md5(md5(psw) + random(8)) + random(4)
	 */
	C.generate = function(password){
		var randomA = generateRandom(4);;
		var randomB = generateRandom(4);
		return randomA + md5(md5(password) + randomA + randomB).toUpperCase() + randomB;
	}
	
	var RANDOM_CHARS = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J',
		'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
	//随机数生成
	function generateRandom(n) {
	     var res = "";
	     for(var i = 0; i < n ; i ++) {
	         var id = Math.ceil(Math.random()*35);
	         res += RANDOM_CHARS[id];
	     }
	     return res;
	}
	module.exports = C;
});


