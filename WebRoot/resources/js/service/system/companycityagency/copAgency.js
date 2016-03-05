define(function (require) {

    var http = require('bower_components/angularext/angularExt').http;

    angular.module('App').
        service('copAgency', ['$http', '$q', function ($http, $q) {

            return {
                // ��ȡ���չ�˾�������˾��Ϣ  7-2
                listRelateFunc:function(){
                    return http($http).post(angular.path+'/system/companycityagency/list/getpage');
                },
                // ��ȡ������˾����by cityId   7-2
                gtPayCompanyByCityId:function(param){
                    return http($http).post(angular.path+'/system/agencycompany/list/getdata',param)
                },
                //��ȡ�˻���Ϣ  7-2
                accountInfoFunc:function(param){
                    return http($http).post(angular.path+'/system/companycityagency/getBankCardInfo',param);
                },
                //�޸ı����˻���Ϣ 7-3 xz
                saveAccount:function(param){
                    return http($http).post(angular.path+'/system/agencycompany/updateBankCard',param);
                },
                //����ѡ�����˾�����Ϣ 7-3 xz
                saveSelectedPayInfoFunc:function(param){
                    return http($http).post(angular.path+'/system/agencycompany/updateBankCard',param);
                }
            }
        }])



});