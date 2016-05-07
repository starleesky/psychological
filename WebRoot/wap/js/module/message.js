define(['jquery','plug/imgLoading'],function($){
	/* function show() {
			var box = document.getElementById("box");
			var text = box.innerHTML;
			var newBox = document.createElement("div");
			var btn = document.createElement("a");
			//去首尾空?
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
		show();*/

	var txtList = $('.msg-txt');
	txtList.each(function(){
		var $parent = $(this).parent(),text = $(this).text(),strTxt = "",$btn = $('<a  href="javascript:;" class="isHide">...显示全部</a>');
		if(text.length>0){
			text = text.replace(/(^\s*)|(\s*$)/g, "");
			if(text.length > 64){
				$btn.on('click',function(){
					if($(this).hasClass('isHide')){
						$(this).text("收起").removeClass('isHide');
						$(this).prev(".msg-txt").css("max-height","none");
					}else{
						$(this).text("...显示全部").addClass('isHide');
						$(this).prev(".msg-txt").css("max-height","44px");
					}
				}).appendTo($parent);
			}
		}
	});
});