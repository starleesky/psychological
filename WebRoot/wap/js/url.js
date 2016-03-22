define(function (require, exports, module) {
    var baseUrl = 'http://localhost:8082/tsjx';
    if (!ctx == null && ctx == '') {
        baseUrl = ctx;
    }
    //baseUrl = 'http://tangsonsgroup.com';
    //baseUrl = 'http://service.tangsons.cn/';
    var URL = window.URL = this.URL = {
        domain: baseUrl,
        loginUrl: baseUrl + '/wap/loginIn',
        loginSuccessUrl: baseUrl + '/wap/index',
        toRegisterUrl: baseUrl + '/wap/toRegister',//去注册页面

        listGoodsCatagory: baseUrl + '/catagory/listCatagory', // 查询设备产品列表URL
        listBrand: baseUrl + '/brand/listBrand',//查询设备品牌URL
        listModels: baseUrl + '/models/listModels',//查询设备型号URL
        saveInfo: baseUrl + '/infomation/save',//信息发布
        registerUrl:baseUrl + '/wap/register',//保存注册第一步
        register2Url:baseUrl + '/wap/toRegister2',//注册下一步
        saveRegister2:baseUrl + '/wap/saveRegister2', //保存注册下一步
        registerSuccess:baseUrl + '/wap/register-success',//注册成功
    }
    module.exports = URL;
});