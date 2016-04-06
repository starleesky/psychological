define(['jquery','plug/box','plug/uploader/uploader','url','plug/validate/validateMethod','plug/imgLoading','plug/selectPro'],function ($,box,Uploader,url,Validator){
    var $body = $('body');
    $body.on('click','.jDelImg',function(){
        var $img = $(this).parents('.info').find('img');
        box.confirm('是否确认删除图片',function(){
            $img.attr('src','');
        },function(){},$img[0]);

    });




    //file uploader buton installations  失败：-1 初始值0 正在上传1 成功2
    var uploadArray = [];
    $('[node-type="uploadButton"]').each(function(i, el) {
        uploadArray[i] = 0;
        var $el = $(el),
            fieldName = $el.data('name'),
            fieldInput = $('<input type="hidden" name="_UPLOAD_' + i + '" />');

        $el.append(fieldInput);

        // initialize file upload component
        var uploader = Uploader(el, {
            endpoint: url.demoUploadUrl
        });

        uploader.on('fileselect', function(e) {
            $el.find('.upload-txt').text('正在等待上传...');
            var html = '<div class="progressbar"><div></div></div>';
            $el.append(html);
            uploadArray[i] = 1;
        });

        uploader.on('uploadprogress', function(e) {
            var percent = Math.min(e.percentLoaded, 99) + '%';
            $el.find('.progressbar div').css('width', percent).text(percent);
            $el.find('.upload-txt').text('选择图片');
        });

        uploader.on('uploadcomplete', function(e) {
            var res = e.data || {};
            if (res.code == "1") {
                uploadArray[i] = 2;
                fieldInput.val(res.url);
                $el.find('.progressbar').remove();
            } else {
                var isFirst = true;
                var timer = setInterval(function() {
                    var eImg = $el.removeClass('file-uploaded').find('img');
                    var eDel = $el.find('.icon-delete');
                    if (isFirst && eImg.length > 0) {
                        box.tips(res.msg || '图片上传失败');
                        eImg.remove();
                        eDel.remove();
                        isFirst = false;
                        clearInterval(timer);
                    }
                }, 100);
            }
        });
        uploader.on('uploaderror', function(e) {
            uploadArray[i] = -1;
            box.tips('图片上传失败!');
        });

    });

    var clickHandlers = {
        deleteImgBtn: function(e) {
            var $this = $(this);
            $this.parents(".upload-img").toggleClass("edit");
        }
    };
    for (var k in clickHandlers) {
        var handle = clickHandlers[k];
        var key = "[node-type=" + k + "]";
        if (handle) {
            $("body").on("click", key, handle);
        }
    }

    var oRegionProvice = $(".regionProvice");
    var oRegionCity = $(".regionCity");

    //产品大类
    var getProvice = function () {
        $.getJSON(url.listRegion, {id: '0'}, function (data) {
            var oProvice_html;
            $.each(data.object, function (i, data) {
                oProvice_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            });
            oRegionProvice.html(oProvice_html);
            getCity();
        });
    }

    oRegionProvice.change(function () {
        getCity();
    });

    //产品组
    var getCity = function () {
        var n = oRegionProvice.val();
        $.getJSON(url.listRegion, {id: n}, function (data) {
            var oCity_html;
            $.each(data.object, function (i, data) {
                oCity_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            });
            oRegionCity.html(oCity_html);
        });
    };


    if(status==""){
        //初始省市
        getProvice();
    }

    //隐藏域设置值


    $("#jSubmit").click(function () {

        if(status=="1"){
            box.error("认证成功，不能修改！");
            return;
        }
        if(status!="2"&&status!=""){
            box.error("不能修改");
            return;
        }

        if($('input[name=_UPLOAD_0]').val()==""){
            box.error("公司logo不能为空");
            return;
        }
        if($('input[name=_UPLOAD_1]').val()==""){
            box.error("营业执照副本不能为空");
            return;
        }
        if($('input[name=_UPLOAD_2]').val()==""){
            box.error("组织机构代码证不能为空");
            return;
        }
        $('#companyForm').submit();

    });

    Validator.validate('#companyForm', {
        rules: {
            companyName: {
                required: true
            },
            createBy: {
                required: true
            },
            businessLicenseImageUrl: {
                required: true
            },
            organizationCodeImageUrl: {
                required: true
            }
        },
        messages: {
            companyName: {
                required: '公司名称不能为空'
            },
            createBy: {
                required: '公司logo不能为空'
            },
            businessLicenseImageUrl: {
                required: '营业机构不能为空'
            },
            organizationCodeImageUrl: {
                required: '组织机构不能为空'
            }
        },
        submitHandler: function (form) {
            $.post(
                url.saveCompany,
                {
                    companyName: $(form).find('input[name=companyName]').val(),
                    createBy: $(form).find('input[name=_UPLOAD_0]').val(),
                    businessLicenseImageUrl: $(form).find('input[name=_UPLOAD_1]').val(),
                    organizationCodeImageUrl: $(form).find('input[name=_UPLOAD_2]').val(),
                    telephone: $(form).find('input[name=telephone]').val(),
                    fax: $(form).find('input[name=fax]').val(),
                    address: $(form).find('input[name=address]').val(),
                    introduction: $(form).find('textarea[name=introduction]').val(),
                    provinceId: $(form).find('select[name=regionProvice]').val(),
                    provinceName: $(form).find('select[name=regionProvice]').find("option:selected").text(),
                    cityId: $(form).find('select[name=regionCity]').val(),
                    cityName: $(form).find('select[name=regionCity]').find("option:selected").text()
                },
                function (data) {
                    if (data.result) {
                        box.ok(data.message);
                        window.location.href = ctx+"/wap/infor";
                    } else {
                        box.error(data.message);
                        window.location.href = ctx+"/wap/infor";
                    }

                });
        },
        showError: function (elem, msg) {
            box.error(msg, elem);
        },
        success: null
    });
});