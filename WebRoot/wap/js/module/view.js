define(['jquery','plug/ajax','url','plug/box','plug/touch','plug/scrollIcon'],function($,ajax,url,box,touch,iconScroll) {
	iconScroll({"iconNum":2});
    var $jImg = $('#jImgBox'),$bigBox = $('.img-big-box'),$imgList = $('.img-list a');
    $jImg.on('click','a',function(){
		var index = $(this).index();
        var src = $(this).attr('data-url');
        $jImg.find('.big-img img').attr({'src':src,'data-index':index});
    });

    $jImg.on('click','.big-img img',function(){
		var index = $(this).attr('data-index');
        $bigBox.show();
        $bigBox.find('img').attr({'src':this.src,'data-index':index});
    });

    $bigBox.on('click','.jClose',function(){
        $bigBox.hide();
    })/*.on("swipeLeft",function(){
		var index = parseInt($(this).find("img").attr('data-index'));
		var eq = ((index+1)==$imgList.length)?0:(index+1);
		$(this).find('img').attr({'src':$imgList.eq(eq).attr('data-url'),"data-index":eq});
	}).on("swipeRight",function(){
		var index =parseInt($(this).find("img").attr('data-index'));
		var eq = ((index-1)< 0)?parseInt($imgList.length-1):(index-1);
		$(this).find('img').attr({'src':$imgList.eq(eq).attr('data-url'),"data-index":eq});
	}).on("singleTap ",function(){
		$bigBox.hide();
	});*/
	var bigBox = touch($bigBox[0]);
	bigBox.on("swipeLeft",function(){
		var index = parseInt($(this).find("img").attr('data-index'));
		var eq = ((index+1)==$imgList.length)?0:(index+1);
		$(this).find('img').attr({'src':$imgList.eq(eq).attr('data-url'),"data-index":eq});
	}).on("swipeRight",function(){
		var index =parseInt($(this).find("img").attr('data-index'));
		var eq = ((index-1)< 0)?parseInt($imgList.length-1):(index-1);
		$(this).find('img').attr({'src':$imgList.eq(eq).attr('data-url'),"data-index":eq});
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
    });
    $("#linkuser").click(function(){box.tips("先登录")});
    

	   /* function show() {
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
	
	 function showCompany() {
			var box = document.getElementById("boxCompany");
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
	 showCompany();*/
		
		



    	

});