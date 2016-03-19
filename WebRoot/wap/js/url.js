define(function (require, exports, module) {
    var baseUrl = 'http://192.168.10.71:8080/tsjx';
    if (!ctx == null && ctx == '') {
        baseUrl = ctx;
    }
    //baseUrl = 'http://tangsonsgroup.com';
    //baseUrl = 'http://service.tangsons.cn/';
    var URL = window.URL = this.URL = {
        domain: baseUrl,
        loginUrl: baseUrl + '/Login.asmx/UserLogin',
        registerUrl: baseUrl + '/Login.asmx/Register',

        listGoodsCatagory: baseUrl + '/goodsCatagory/getGoodsCatagory', // 查询设备产品列表URL
        listBrand: baseUrl + '/brand/listBrand',//查询设备品牌URL
        listModels: baseUrl + '/models/listModels',//查询设备型号URL
        saveInfo: baseUrl + '/infomation/save',//信息发布


        loginUrl: baseUrl + '/Login.asmx/UserLogin'
    }
    module.exports = URL;
});