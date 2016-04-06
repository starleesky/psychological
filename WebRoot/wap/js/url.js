define(function (require, exports, module) {
    var baseUrl = 'http://localhost:8080/tsjx';
    //var baseUrl = 'http://192.168.8.121:8080/tsjx';
    if (!ctx == null && ctx == '') {
        baseUrl = ctx;
    }
    //baseUrl = 'http://tangsonsgroup.com';
    //baseUrl = 'http://service.tangsons.cn/';
    var URL = window.URL = this.URL = {
        domain: baseUrl,
        demoPageUrl: baseUrl + '/wap/demo/page',
        demoUploadUrl: baseUrl + '/wap/file/upload',

        loginUrl: baseUrl + '/wap/loginIn',
        loginSuccessUrl: baseUrl + '/wap/index',
        loginIndexUrl:baseUrl + '/wap/infor',//登录成功
        toRegisterUrl: baseUrl + '/toRegister',//去注册页面

        listGoodsCatagory: baseUrl + '/catagory/listCatagory', // 查询设备产品列表URL
        listBrand: baseUrl + '/brand/listBrand',//查询设备品牌URL
        listModels: baseUrl + '/models/listModels',//查询设备型号URL
        listRegion: baseUrl + '/region/listRegion',//查询省市级
        saveInfo: baseUrl + '/infomation/save',//信息发布
        saveCompany: baseUrl + '/company/save',//信息发布
        registerUrl:baseUrl + '/wap/register',//保存注册第一步
        register2Url:baseUrl + '/wap/toRegister2',//注册下一步
        saveRegister2:baseUrl + '/wap/saveRegister2', //保存注册下一步
        registerSuccess:baseUrl + '/wap/register-success',//注册成功
        infoList : baseUrl + '/infomation/infoList',	//我的信息列表
        moreInfo : baseUrl + '/infomation/moreInfo',	//信息列表下拉方法
        moreSearchInfo : baseUrl + '/infomation/moreSearchInfo', //信息列表下拉方法(带搜索条件列表)
        toForgotpwd:baseUrl + '/wap/toForgotpwd',//找回密码
        toForgotpwdSuccess:baseUrl + '/wap/toForgotpwdSuccess',//找回密码成功
        userInfoUpdate:baseUrl + '/user/update',//修改个人信息
        
        searchInfoList : baseUrl + '/infomation/search',	//高级检索查询列表
        collectionSave:baseUrl+'/collection/save', //收藏
        reUp:baseUrl+'/infomation/reUp' //重新上架
    }
    module.exports = URL;
});