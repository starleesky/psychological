/**
 * @author : 叶阳
 * @created : 2015/08/05
 * @version : v1.0
 * @desc : 文件上传模块
 *
 * 使用方法
 * html部分
 * <div class="button" ng-file-select multiple ng-file-change="upload($files)">上传</div>
 * 
 * js部分
 * var uploadFileHandler = new uploadFileHandler($upload);
 * 参数说明
 *     $upload 上传组件服务
 *
 * 上传方法
 *     uploadFileHandler.upload(files,opts);
 * 参数说明
 *     files 选择的文件对象
 *     opts 
 *         {
 *             success:function(resp,config){},//resp 接口返回对象，config 文件信息
 *             progress:function(i,evt){} //i索引（多张图片一起上传是所用），evt 上传进度信息
 *         }
 */
define(function (require) {
    angular.module('App').factory("uploadFileHandler",["$http","$q","$timeout",function($http,$q,$timeout){


        function UploadFileHandler($upload){
            this.$upload = $upload;
            this.urls = [];
        }

        /**
         * [upload 上传]
         * @param  {[type]} files    [文件数组]
         * @param  {[type]} opts [配置]
         * opts
         * {    
         *    data://额外的参数
         *    progress:function //进度条回调
         *    success:function //成功后回调
         * }
         * @return {[type]}          [description]
         */
        UploadFileHandler.prototype.upload = function(files,opts){
            var self = this;

            files = typeof files==="object"?[files]:files;
            if (files && files.length>0) {
                for (var i = 0; i < files.length; i++) {
	                	var sendData = {file:files[i]};
	                    sendData = angular.extend(sendData,opts.data);
                        //上传文件
                        self.$upload.upload({
                        	url: opts.url,
                            data: sendData,
                        }).progress(function (evt) {
                            //var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            //console.log(progressPercentage);
                           if(typeof opts.progress == "function"){
                                opts.progress(i,evt);
                           }
                        }).success(function (resp, status, headers, config) {
                            if(resp!=null){
                                if(resp.success){
                                    opts.success(resp,config);
                                }else{
                                    console.log(resp);
                                    if (typeof opts.error == 'function') {
                                        opts.error(resp,config);
                                    } else {
                                        if(resp.message){
                                            alert(resp.message);
                                        }
                                    }
                                }
                            }
                        });
                    }
            }
        }

        return UploadFileHandler;
    }]);
});