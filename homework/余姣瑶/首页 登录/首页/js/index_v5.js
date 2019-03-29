//点击菜单：参数（功能模块代码,功能页面地址,功能模块名称,是否功能页面）
function clickMenu(gnmkdm, dyym, gnmkmc, sfgnym) {
	// openBlank('../xtgl/init_cxGnPage.html',{'gnmkdm':gnmkdm,'dyym':dyym,'gnmkmc':encodeURIComponent(gnmkmc),'sfgnym':sfgnym},true);
	/*openBlank('..' + dyym, {
		'gnmkdm': gnmkdm,
		'dyym'	: dyym,
		'gnmkmc': encodeURIComponent(gnmkmc),
		'sfgnym': sfgnym
	}, true);*/
	openWin('..' + dyym, { 
		'gnmkdm': gnmkdm
	}, true);
	/*
	 * openBlank('../xtgl/index_cxFuncIndex.html?u=' +
	 * jQuery("#sessionUserKey").val() + "&_t=" + jQuery.now(),{
	 * 'gnmkdm':gnmkdm,'dyym':dyym,'gnmkmc':encodeURIComponent(gnmkmc),'sfgnym':sfgnym
	 * },true);
	 */
}

function openWin(action, data, n) {
	var html = [];
	var gnmkdm="";

	jQuery.each(jQuery.extend(data || {}, {
		// 添加默认请求参数
		"layout"		: "default"
	}), function(key, val) {
		if(key=='gnmkdm'){
			gnmkdm = val;
		}
		if($.isArray(val)) {
			$.each(val, function(i, item) {
				
				html.push(key + '=' + encodeURIComponent(item));
			});
		} else if($.isFunction(val)) {} else {
			html.push(key + '=' + encodeURIComponent(val));
		}
	});
//	alert(action.indexOf("?") ? (action + "&" + html.join("&")) : (action + "?" + html.join("&")));
	$.openWin(action.indexOf("?") > -1 ? (action + "&" + html.join("&")) : (action + "?" + html.join("&")));
//	alert(gnmkdm);
	$.post(_path + "/xtgl/index_cxBczjsygnmk.html",{"gndm":gnmkdm},function(data){
		
	});
}

// 键盘快捷键监听函数
function initQuickEvent() {
	// 绑定快捷键
	/*
	 * $(document).bind('keydown', "Ctrl+Q" ,function (event){
	 * event.preventDefault(); event.stopPropagation(); $.openWin("../sql.jsp");
	 * return false; });
	 */
}

jQuery(function($) {
	
	// 处理菜单点击实际
	$(document).off("click touchend", ".bs-navbar-collapse a.nav-target").on("click touchend", ".bs-navbar-collapse a.nav-target", function(e) {
		var gnmkdm = $(this).data("gnmkdm");
		var gnmkmc = $(this).data("gnmkmc");
		var sfgnym = $(this).data("sfgnym");
		var dyym = $(this).data("dyym");
		if(gnmkdm.indexOf("N0199")>-1){
			openWin(dyym, {
				'gnmkdm': gnmkdm
			}, true);
		}else{
			openWin('..' + dyym, {
				'gnmkdm': gnmkdm
			}, true);
			
		}
		e.stopPropagation();
	}).off("click touchend", ".container a.nav-blank").on("click touchend", ".container a.nav-blank", function(e) {
		var gnmkdm = $(this).data("gnmkdm");
		var gnmkmc = $(this).data("gnmkmc");
		var sfgnym = $(this).data("sfgnym");
		var dyym = $(this).data("dyym");
		if(gnmkdm.indexOf("N0199")>-1){
			openWin(dyym, {
				'gnmkdm': gnmkdm
			}, true);
		}else{
			openWin('..' + dyym, {
				'gnmkdm': gnmkdm
			}, true);
			
		}
		e.stopPropagation();
	})
	// 绑定退出按钮
	.off("click touchend", "#exit").on("click touchend", "#exit", function(e) {
		//window.location = _systemPath + "/xtgl/dl_logout.html?t=" + jQuery.now() + "&login_type=" + $("#login_type").val();
		//改为统一的shiro注销地址
		window.location = _systemPath + "/logout?t=" + jQuery.now() + "&login_type=" + $("#login_type").val();
	})
	// 绑定修改密码
	.off("click touchend", "#updatePassword").on("click touchend", "#updatePassword", function(e) {
		$.showDialog(_path + "/xtgl/mmgl_xgMm.html", $.i18n.zftal["titles"]["pwd"], $.extend({}, addConfig, {
			"width": "600px"
		}));
	})
	// 绑定修改每页显示条数
	.off("click touchend", "#updatePageNum").on("click touchend", "#updatePageNum", function(e) {
		$.showDialog(_path + "/xtgl/index_xgPageNumIndex.html",'修改',$.extend(true,{},addConfig,{
			width:"600px",
			modalName:"xgPageCountModal",
			buttons:{
				success : {
					label : "保存",
					className : "btn-primary",
					callback : function() {
						$("#bz").val($("#pageCount").val());
							submitForm("xgPageForm",function(msg,statusText){
								if(msg.indexOf("成功") > -1){
									$.success(msg,function() {
										$.closeModal("xgPageCountModal");
									});
								}else if(msg.indexOf("失败") > -1){
									$.error(msg,function() {
									});
								} else{
									$.alert(msg,function() {
									});
								}
							});
						return false;
						
					}}
					
				},
				cancel : {
					label : "关 闭",
					className : "btn-default"
				}
			}
		));
	    return false;
	})
	// 绑定修改每页显示条数
	.off("click touchend", "#updateGrxx").on("click touchend", "#updateGrxx", function(e) {
		$.showDialog(_path + "/xtgl/index_xgGrxxIndex.html",'修改',$.extend(true,{},addConfig,{
			width:"600px",
			modalName:"xgGrxxModal",
			buttons:{
				success : {
					label : "保存",
					className : "btn-primary",
					callback : function() {
							submitForm("xgGrxxForm",function(msg,statusText){
								if(msg.indexOf("成功") > -1){
									$.success(msg,function() {
										$.closeModal("xgGrxxModal");
									});
								}else if(msg.indexOf("失败") > -1){
									$.error(msg,function() {
									});
								} else{
									$.alert(msg,function() {
									});
								}
							});
						return false;
						
					}}
					
				},
				cancel : {
					label : "关 闭",
					className : "btn-default"
				}
			}
		));
	    return false;
	})
	// 兼容菜单超出窗口显示问题
	.off("mouseenter", 'li.dropdown-submenu').on("mouseenter", 'li.dropdown-submenu', function(e) {
		var dropdown_menu = $(this).find("ul.dropdown-menu");
		if(dropdown_menu.size() > 0){
			var dropdown_menu_second = $(dropdown_menu).outerWidth(true);
			if(($(dropdown_menu).offset().left + $(dropdown_menu).outerWidth()) > $(window).width()) {
				$(dropdown_menu).css("left", -dropdown_menu_second);
			}
		}
	});
	
	function resizeMenu(){
		var wWidth=$(window).width();
		if(wWidth<768){
			$('.dropdown-submenu').addClass('dropdown-submenu2').removeClass('dropdown-submenu');
			$(document).off("click", ".dropdown-submenu2").on("click", ".dropdown-submenu2", function(e) {
				e.stopPropagation(); 
				$(this).closest('.dropdown-menu').find('.dropdown-menu').removeClass('show');
				$(this).find('.dropdown-menu').addClass('show');
				$(".dropdown-submenu2.current").removeClass('current');
				$(this).addClass('current');
			}).off("click", '.dropdown-submenu2.current').on("click", '.dropdown-submenu2.current', function(e) {
				e.stopPropagation(); 
				console.log($(this).find('ul').attr('class'));
				if($(this).find('dropdown-menu.show')){
					$(this).find('.dropdown-menu').removeClass('show');
					$(this).removeClass('current');
				}
			});
			
		}else{
			$('.dropdown-submenu2').removeClass('dropdown-submenu2').addClass('dropdown-submenu');
		}
	}
	
	resizeMenu();
	$(window).resize(function(){
		resizeMenu();
	});

	// Ajax动态加载新闻内容
	$("#newsnotice").load(_path + "/xtgl/index_cxNews.html?localeKey="+$("#localChange").val(), {}, function() {
		// 滚动条
		$("#newsnotice").mCustomScrollbar({
			autoHideScrollbar	: false,
			autoExpandScrollbar	: false,
			horizontalScroll 	: false,
			scrollInertia	 	: 0,
			scrollButtons:{
				enable		: true,
				scrollType	: "continuous",
				scrollSpeed	: 20,
				scrollAmount: 40
			},
			advanced:{
			    updateOnBrowserResize: false,
			    updateOnContentResize: true,
			    updateOnSelectorChange: false,
			    autoExpandHorizontalScroll: true
			},
 			theme:"dark-3"
		});
		$('#newsnotice').mousewheel(function(event, delta, deltaX, deltaY) {
			event.preventDefault();
		});
	});

	// Ajax动态加载用户首页显示信息
	$("#yhxxIndex").load(_path + "/xtgl/index_cxYhxxIndex.html?xt=jw&localeKey="+$("#localChange").val());

	initQuickEvent();

	$('#localChange').trigger("chosen");

	// 滚动条
	$("#index_zjsy,#index_wdyy").mCustomScrollbar({
		autoHideScrollbar: false,
		advanced: {
			updateOnBrowserResize: true,
			updateOnContentResize: true,
			autoExpandHorizontalScroll: true
		}
	});

	/*// 左右侧导航
	$(document).off("click touchend", ".navbar a.grxx").on("click touchend", ".navbar a.grxx", function(e) {
		$('.nav-menu').removeClass('nav-menu-open');
	}).off("click touchend", ".nav-menu-switch li").on("click touchend", ".nav-menu-switch li", function(e) {
		// 切换导航模式
		var dir = 'nav-menu-' + $(this).attr('data-direction');
		var fre = 'nav-menu-' + $(this).attr('data-freely');
		var $class = $(this).attr('data-class');
		$('body').attr('data-class', $class);
		if($(this).attr('data-push').length > 0) {
			var push = 'nav-menu-' + $(this).attr('data-push') + '-to' + $(this).attr('data-direction');
			$('body').attr('class', '').addClass('nav-menu-push').addClass(push).addClass('body-container');
			$('.nav-menu-toggle').addClass('toggle-rotate');
		} else {
			$('body').attr('class', '').addClass('nav-menu-push').addClass('body-container');
			$('.nav-menu-toggle').addClass('toggle-rotate');
		}
		$('.nav-menu').attr('class', '').addClass('nav-menu');
		$('.nav-menu').addClass(dir).addClass(fre).addClass('nav-menu-open');
	}).off("click touchend", ".nav-menu-toggle,.nav-menu-catalog").on("click touchend", ".nav-menu-toggle,.nav-menu-catalog", function(e) {
		var $class = $('body').attr('data-class');
		if($('.nav-menu-open').size() > 0 && $class.length > 0) {
			$('.nav-menu').removeClass('nav-menu-open');
			$('body').removeClass($class);
			$('.nav-menu-toggle').removeClass('toggle-rotate');
			$('.fa-angle-right').removeClass('play-rotate');
			$('.nav-menu .content2').hide();
		} else if($('.nav-menu-open').size() > 0) {
			$('.nav-menu').removeClass('nav-menu-open');
			$('.nav-menu-toggle').removeClass('toggle-rotate');
		} else if($('.nav-menu-open').size() == 0 && $class.length > 0) {
			$('.nav-menu').addClass('nav-menu-open');
			$('body').addClass($class);
			$('.nav-menu-toggle').addClass('toggle-rotate');
		} else {
			$('.nav-menu').addClass('nav-menu-open');
			$('.nav-menu-toggle').addClass('toggle-rotate');
		}
	}).off("click touchend", ".nav-menu-item .nav-title").on("click touchend", ".nav-menu-item .nav-title", function(e) {
		var $content = $(this).closest('.content2');
		if($(this).next().hasClass('content2') == true) {
			$('.fa-angle-right').removeClass('play-rotate');
			$('.nav-menu-item .content2').not($content).slideUp();
		}
		if($(this).next().is('.content2') && $(this).next().is(':hidden')) {
			$(this).next().slideDown();
			$(this).find('.fa-angle-right').addClass('play-rotate');
		}
	});

	var he = $('.nav-menu-vertical').height();
	var sc = 60 - (document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop);
	if(sc < 0) {
		$('.nav-menu-vertical').css('top', '0');
	}

	$(window).scroll(function() {
		var he = $('.nav-menu-vertical').height();
		var sc = 60 - (document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop);
		if(sc < 0) {
			$('.nav-menu-vertical').css('top', '0');
		} else {
			$('.nav-menu-vertical').css('top', sc);
		}
	});

	// 页面选项卡
	$('#tabs').tabs({
		monitor: '.nav-menu-item',
	});*/
});