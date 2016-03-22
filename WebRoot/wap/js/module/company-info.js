define(['jquery','plug/box','plug/uploader/uploader','url','plug/imgLoading','plug/selectPro'],function ($,box,Uploader,url){
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
});