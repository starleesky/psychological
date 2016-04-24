define(['jquery','plug/box','plug/uploader/uploader-list','url','plug/imgLoading','plug/selectPro'],function ($,box,Uploader,url){


    var clickHandlers = {
        deleteImgBtn: function(e) {
            clickHandlers.changeInputName($('.ui-button-upload'));
        },
        changeInputName:function($obj){
            $.each($obj.find('input[type=hidden]'),function(i){
                $(this).attr('name','_UPLOAD_'+i);
            });
        }
    };

    //file uploader buton installations  失败：-1 初始值0 正在上传1 成功2
    var uploadArray = [];
    $('[node-type="uploadButton"]').each(function(i, el) {
        uploadArray[i] = 0;
        var $el = $(el),
            fieldName = $el.data('name');

        /*$el.append(fieldInput);*/

        // initialize file upload component
        var uploader = Uploader(el, {
            endpoint:  url.demoUploadUrl
        });



        uploader.on('fileselect', function(e) {
            var html = '<div class="progressbar"><div></div></div>';
            $el.append(html);
            uploadArray[i] = 1;
        });

        uploader.on('uploadprogress', function(e) {
            var percent = Math.min(e.percentLoaded, 99) + '%';
            $el.find('.progressbar div').css('width', percent).text(percent);
        });

        uploader.on('uploadcomplete', function(e) {
            var res = e.data || {},fieldInput = $('<input type="hidden" />');
            if (res.code == "1") {
                uploadArray[i] = 2;
                fieldInput.val(res.url);
                $el.append(fieldInput);
                clickHandlers.changeInputName($el);
            } else {
                var isFirst = true;
                var timer = setInterval(function() {
                    var eImg = $el.next().find('.up-img-box ').last();
                    if (isFirst && eImg.length > 0) {
                        box.tips(res.msg || '图片上传失败');
                        eImg.remove();
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


    for (var k in clickHandlers) {
        var handle = clickHandlers[k];
        var key = "[node-type=" + k + "]";
        if (handle) {
            $("body").on("click", key, handle);
        }
    }

    $('body').on('click','.jAddProType',function(){
        if($(this).hasClass('open')){
            $('.desc-child').addClass('isHide');
            $(this).removeClass('open');
        }else{
            $('.desc-child').removeClass('isHide');
            $(this).addClass('open');
        }
    });

    $("#jSubmit").click(function () {
        //alert('提交之前请先保存');
        var array = [],
            _text = $("input[name^='_UPLOAD_']");
        for(var i=0;i<_text.length;i++){
            alert(_text[i].name);
            alert(_text[i].value);
        }
    });

    //计算有效期时间
    var GetDateStr = function (AddDayCount){
        var dd = new Date();
        dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
        var y = dd.getYear();
        var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
        var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); //获取当前几号，不足10补0
        return y+"-"+m+"-"+d;
    }
    $body.on('change','input[name=validTime]',function(){
        var $date = $('.jDate'),val = $(this).val(),currentDate = new Date().getDay();
        console.log(GetDateStr(val));

    })

});