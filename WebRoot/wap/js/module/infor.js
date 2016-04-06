define(
		[ 'jquery', 'url', 'plug/ajax', 'plug/box', 'plug/load/lazyload',
				'plug/load/lazystream', 'plug/selectPro' ],
		function($, url, ajax, box, Lazyload, LazyStream) {
			$body = $('body');
			$body.on('click', '.jIsHide', function() {
				if ($(this).hasClass('open')) {
					$(this).removeClass('open').html('隐藏');
					$(this).parents('.hd').next('.bd').show();
				} else {
					$(this).addClass('open').html('显示');
					$(this).parents('.hd').next('.bd').hide();
				}
			});

			var oBigGoodsCatagory = $("#catagoryBigId");
			var oMiddleGoodsCatagory = $("#catagoryMidId");
			var oSmallGoodsCatagory = $("#catagoryId");

			var oBrand = $("#brandId");
			var oModels = $("#modelId");
			//产品大类
			var getBig = function() {
				$.getJSON(url.listGoodsCatagory, {
					id : '0'
				}, function(data) {
					var oBig_html = "<option  value=''>请选择大类</option>";
					$.each(data.object, function(i, data) {
						oBig_html += "<option value='" + data.id + "'>"
								+ data.catagoryName + "</option>";
					});
					oBigGoodsCatagory.html(oBig_html);
					getMiddle();
				});
			}

			oBigGoodsCatagory.change(function() {
				getMiddle();
			});

			//产品组
			var getMiddle = function() {
				var n = oBigGoodsCatagory.val();
				$.getJSON(url.listGoodsCatagory, {
					id : n
				}, function(data) {
					var oMiddle_html = "<option value=''>请选择产品组</option>";
					$.each(data.object, function(i, data) {
						oMiddle_html += "<option value='" + data.id + "'>"
								+ data.catagoryName + "</option>";
					});
					oMiddleGoodsCatagory.html(oMiddle_html);
					getSmall();
				});
			}

			oMiddleGoodsCatagory.change(function() {
				getSmall();
			});

			//产品类型
			var getSmall = function() {
				var n = oMiddleGoodsCatagory.val();
				$.getJSON(url.listGoodsCatagory, {
					id : n
				}, function(data) {
					var oSamll_html = "<option  value=''>请选择类型</option>";
					$.each(data.object, function(i, data) {
						oSamll_html += "<option value='" + data.id + "'>"
								+ data.catagoryName + "</option>";
					});
					oSmallGoodsCatagory.html(oSamll_html);
					getBrand();
				});
			}

			oSmallGoodsCatagory.change(function() {
				getBrand();
			});

			//品牌
			var getBrand = function() {
				var n = oSmallGoodsCatagory.val();
				$.getJSON(url.listBrand, {
					catagoryId : n
				}, function(data) {
					var oBrand_html = "<option  value=''>请选择品牌</option>";
					$.each(data.object, function(i, data) {
						oBrand_html += "<option value='" + data.id + "'>"
								+ data.brandName + "</option>";
					});
					oBrand.html(oBrand_html);
					getModels();
				});
			}

			oBrand.change(function() {
				getModels();
			});

			//型号
			var getModels = function() {
				var n = oBrand.val();
				$.getJSON(url.listModels, {
					brandId : n
				}, function(data) {
					var oModels_html = "<option  value=''>请选择型号</option>";
					$.each(data.object, function(i, data) {
						oModels_html += "<option value='" + data.id + "'>"
								+ data.modelsName + "</option>";
					});
					oModels.html(oModels_html);
				});
			}

			//初始化产品大类
			getBig();

			//根据当前选中的信息状态，设定class=current
			var curStatus = $("#curStatus").val();
			var curClass = function() {
				$("a[status='" + curStatus + "']").attr("class", "current");
			}

			curClass();

			var initOtherSel = function() {
				$("select[name='orderSel']").val($("#order").val());
			}

			//初始化其他下拉框
			initOtherSel();

			$('body').on('click', '.jNewUp', function() {
				var id = $(this).attr('infoId');
				box.confirm('是否确认重新上架', function() {
					$.post(url.reUp, {id: id}, function (data) {
						//box.alert(data.message,function() {
							window.location.href = url.infoList + "?order=" + $("#order").val() + "&status=" + $("#curStatus").val();
						//});
					});
				}, function() {
				}, this);
			});

			var $view = $('.page-view-body')

			$view.on('click', '.op-button-higSearch', function() {
				$view.find('.search-form').show();
			});

			$view.on('click', '.op-button-return', function() {
				$view.find('.search-form').hide();
			});

			var order = $("#order").val();
			var lazyMore = new LazyStream(
					'.jPage',
					{
						plUrl : url.moreInfo,
						paramFormater : function(n) {
							var data = {};
							data.pageNo = n;
							data.isAjax = '1';
							data.groupsType = $("input[name='group-type']")
									.val();
							data.status = curStatus;
							data.order = order;
							return data;
						},

						page : 1,

						errorText : '<div class="loading">网络错误，点击重试</div>',
						loadingClass : 'loading',
						loadingText : '<div class="loading"><img src="'
								+ ctx
								+ '/wap/images/loading2.gif" class="load-gif" />正在加载，请稍后...</div>',
						//
						load : function(el) {
							Lazyload.load($(el).find('.jImg'));
						},
						noAnyMore : '<div class="loading">sorry,已经没有下一页了...</div>'
					});

			$("select[name='orderSel']").change(
					function() {
						$("#order").val(this.value);
						window.location.href = url.infoList + "?order="
								+ this.value + "&status="
								+ $("#curStatus").val();
					});
		});