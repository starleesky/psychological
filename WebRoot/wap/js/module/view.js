define(['jquery','plug/ajax','url','plug/box'],function($,ajax,url,box) {

    var $jImg = $('#jImgBox'),$bigBox = $('.img-big-box');
    $jImg.on('click','a',function(){
        var src = $(this).attr('data-url');
        $jImg.find('.big-img img').attr('src',src);
    });

    $jImg.on('click','.big-img img',function(){
        $bigBox.show();
        $bigBox.find('img').attr('src',this.src);
    });

    $bigBox.on('click','.jClose',function(){
        $bigBox.hide();
    });
    $("#collection").click(function(){
    	//alert($("#id").val());
        $.post( url.collectionSave,
          {
        	informationId: $("#id").val()
          }
    	, function (data) {
    		box.tips(data.message);
        },'json');
    })
    

	    function show() {
		var box = document.getElementById("box");
		var text = box.innerHTML;
		var newBox = document.createElement("div");
		var btn = document.createElement("a");
		//去首尾空�?
		text = text.replace(/(^\s*)|(\s*$)/g, "");
		newBox.innerHTML = text.substring(0, 64);
		btn.innerHTML = text.length > 64 ? "...显示全部" : "";
		btn.href = "###";
		btn.onclick = function() {
			if (btn.innerHTML == "...显示全部") {
				btn.innerHTML = "收起";
				newBox.innerHTML = text;
			} else {
				btn.innerHTML = "...显示全部";
				newBox.innerHTML = text.substring(0, 64);
			}
		}
		box.innerHTML = "";
		box.appendChild(newBox);
		box.appendChild(btn);
	}
	show();



    	

});