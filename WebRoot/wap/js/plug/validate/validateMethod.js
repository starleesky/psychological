/**
 * jQuery validation extends for application implements.
 * - http://jqueryvalidation.org/validate
 *
 * Note:
 *
 * `$().validate()` has been deprecated, Please use `Validator.validate(form, opts)` instead.
 *
 */
define(function(require, exports, module) {
    'use strict';

    var Validator = require('./validator');

    var box = require('../box');
    
    var $ = require('jquery');

    var methods =[
        //验证移动电话和固定电话
        {
            name:'phone',
            text:'请输入正确的号码',
            func: function( value, element ) {
                var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                var tel = /^\d{3,4}-?\d{7,9}$/;
                return this.optional(element) || (tel.test(value) || mobile.test(value));
            }

        },{
            name:'mobile',
            text:'手机号码格式不正确',
            func: function( value, element ) {
                var mobile = /^0?(13[0-9]|15[0-9]|17[678]|18[0-9]|14[57])[0-9]{8}$/g;
                return this.optional(element) || mobile.test(value);
            }
        },{
            name:'tel',
            text:'固定电话格式不正确',
            func: function( value, element ) {
                var tel = /^(\d{3,4}\-?)?\d{7,8}$/;
                return this.optional(element) || tel.test(value);
            }
        },{
            name:'money',
            text:'小数位不能超过二位',
            func: function (value, element) {
                return this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);
            }
        },{
            name:'kg',
            text:'小数位不能超过三位',
            func: function (value, element) {
                return this.optional(element) || /^\d+(\.\d{1,3})?$/.test(value);
            }
        },{
            name:'email',
            text:'邮箱格式不正确',
            func: function(value,element){
                var email = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                return this.optional(element) || email.test(value);
            }
        },{
            name:'equalToPri',
            text:'扣款金额不能大于退款金额',
            func: function( value, element, param ) {
                var target = $( '#jRefPri' );
                if ( this.settings.onfocusout) {
                    target.unbind( ".validate-equalToPri" ).bind( "blur.validate-equalToPri", function() {
                        $( element ).valid();
                    });
                }
                var $jDed = $('#jDed');
                if($jDed.val()=='')
                    $jDed.val('0');
                if($('#jDed').length>0)
                    return parseFloat(value?value:0) < parseFloat(target.text());
                else
                    return true;
            }
        },{
            name:'special',
            text:'由数字和字母组成，不能输入特殊字符',
            func: function(value, element){
                return this.optional(element) || /^(([a-z])|([0-9]))[a-z0-9]*$/i.test(value);
            }
        },{
            name:'equalToDate',
            text:'结束时间不能小于或者等于开始时间',
            func: function( value, element, param ){
                var $startDate = $(param), $endDate = $(element), v1 = $startDate.val(), v2 = $endDate.val();
                return v1 < v2;
            }
        },{
            name:'isNoneMalformed',
            text:'请不要输入特殊字符',
            func: function(value, element) {
                return this.optional(element) || !/[\[\]`~!@#$^&*()=|{}'":;,.<>?￥…（）—|【】‘；：“”。，、？%+ 　\\]/.test(value);
            }
        },{
            name:'isFinanceBankCardno',
            text:'银行卡号格式不正确，请重新输入(不包含空格或者-等连接符号)',
            func: function(value, element) {
                return this.optional(element) || !/^\d{5,20}$/.test(value);
            }
        },{
            name:'isIdCardNo',
            text:'请正确输入您的身份证号码',
            func: function(value){
                var pass = true;
                var city = {11: 1, 12: 1, 13: 1, 14: 1, 15: 1, 21: 1, 22: 1, 23: 1, 31: 1, 32: 1, 33: 1, 34: 1, 35: 1, 36: 1, 37: 1, 41: 1, 42: 1, 43: 1, 44: 1, 45: 1, 46: 1, 50: 1, 51: 1, 52: 1, 53: 1, 54: 1, 61: 1, 62: 1, 63: 1, 64: 1, 65: 1, 71: 1, 81: 1, 82: 1, 91: 1};
                if (!value || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(value)) {
                    // 身份证号格式错误
                    pass = false;
                } else if (!city[value.substr(0, 2)]) {
                    // 地址编码错误
                    pass = false;
                } else {
                    // 18位身份证需要验证最后一位校验位
                    if (value.length == 18) {
                        value = value.split('');
                        // ∑(ai×Wi)(mod 11)
                        var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; // 加权因子
                        var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2]; // 校验位
                        var sum = 0, ai = 0, wi = 0;
                        for (var i = 0; i < 17; i++) {
                            ai = value[i];
                            wi = factor[i];
                            sum += ai * wi;
                        }
                        var last = parity[sum % 11];
                        if (parity[sum % 11] != value[17]) {
                            // 校验位错误
                            pass = false;
                        }
                    }
                }
                return pass;
            }
        }
    ];

    // Extends some usefull validate methods.
    $.each(methods, function(i, o) {
        Validator.addMethod(o.name, o.func, o.text);
    });



    // Exports some useful api.
    $.each([ 'addMethod', 'methods' ], function(i, method) {
        exports[method] = Validator[method];
    });

    exports.validate = function(form, options) {
        var showError = options.showError, isShowBox = typeof showError === 'function';
        options = $.extend({
            focusCleanup: false,
            errorElement: 'p',
            errorClass: 'ui-tiptext ui-tiptext-error',
            errorPlacement: function(error, elem) {
                if (!isShowBox) {
                    error.appendTo(elem.parent());
                }
            },
            // customize error message with icons
            customMessage: function(message, errorObj) {
                return '<i class="ui-tiptext-icon iconfont">&#xe62b;</i>' + message;
            },
            // use highlight and unhighlight
            highlight: function (elem, errorClass, validClass) {
                if (elem.type !== 'radio' ) {
                    $( elem ).addClass( 'error' ).removeClass( validClass );
                }
            },
            unhighlight: function (elem, errorClass, validClass) {
                if (elem.type !== 'radio' ) {
                    $( elem ).addClass( validClass ).removeClass( 'error' );
                }
            },
            success: function(label) {
                label.html('<i class="ui-tiptext-icon-success iconfont">&#xe634;</i>');
            }
        }, options);

        if (isShowBox) {
            options.invalidHandler = function(form, validator) {
                var errors = validator.numberOfInvalids();
                if (errors) {
                    showError(validator.errorList[0].element, validator.errorList[0].message);
                    validator.errorList[0].element.focus(); // Set Focus
                }
            }
        }

        return Validator.validate(form, options);
    };
});