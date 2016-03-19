define(function (require, exports, module) {
    var baseUrl = 'http://localhost:8082/tsjx';
    var ctx = 'http://localhost:8082/tsjx';
    if (!ctx == null && ctx == '') {
        baseUrl = ctx;
    }
    //baseUrl = 'http://tangsonsgroup.com';
    //baseUrl = 'http://service.tangsons.cn/';
    var URL = window.URL = this.URL = {
        domain: baseUrl,
        loginUrl: baseUrl + '/wap/loginIn',
        loginSuccessUrl: baseUrl + '/wap/index',
        registerUrl: baseUrl + '/wap/toRegister',

        listGoodsCatagory: baseUrl + '/goodsCatagory/getGoodsCatagory', // 查询设备产品列表URL
        listBrand: baseUrl + '/brand/listBrand',//查询设备品牌URL
        listModels: baseUrl + '/models/listModels',//查询设备型号URL
        saveInfo: baseUrl + '/infomation/save',//信息发布
        registerUrl:baseUrl + '/wap/register',//注册经一步
    }
    module.exports = URL;
});