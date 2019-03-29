

var flag=true;      //普通标记:true-可提醒,false-不提醒

jQuery(function($){	
	
	// 绑定退出按钮
	$(document).off("click touchend", "#exit_btn").on("click touchend", "#exit_btn", function(e) {
		//window.location = _systemPath + "/xtgl/dl_logout.html?t=" + jQuery.now() + "&login_type=" + $("#login_type").val();
		//改为统一的shiro注销地址
		window.location = _systemPath + "/logout?t=" + jQuery.now() + "&login_type=" + $("#login_type").val();
	})
	$(".wbxt").click(function(){            //外部系统的打开
		var form = jQuery("<form></form>");
		form.attr({
			"id"	: "funcForm",
			'action': $(this).attr("data"),
			'method': 'get',
			'target': '_blank'
		});
		form.appendTo("body").submit();
	});
	
	$('.dropdown-toggle2').click(function(){
		$(this).parent().toggleClass('open');
		$('.jw-shade').toggle(); 
	});
	
	$('.jw-shade,.dropdown-menu2 li:not(".dropdown-submenu"),.dropdown-submenu .dropdown-menu2').click(function(){
		$('.nav-phone').find('.open').removeClass('open');
		$('.jw-shade').hide();
	});
	$('.dropdown-submenu').click(function(){
		$(this).find('.dropdown-menu2').toggle();
	});
	
	//如果是按F5刷新，则把flag置为:false，这样就不用提醒了
	 $(document).bind("keydown",function(e){
	        e=window.event||e;
	        if(e.keyCode==116){
	        	flag=false;  
	        }
	     });
	
	//如果是进行角色切换，则把flag置为:false，这样就不用提醒了
	 $(document).bind("mousedown",function(e){
	        if($(e.target).is("a")){
		        var aHref = $(e.target).attr("href");
		        if(aHref!=null&&aHref.indexOf("xtgl/index_initMenu.html")>-1){
		        	flag=false;  
		        }
	        }
	     });
	 
	if(navigator.userAgent.indexOf("Firefox")>-1){                  //判断是否火狐浏览器
		window.onunload=function(e){ 
			if(flag){        //flag为true，才刷新页面
		    	var evt = e ? e : (window.event ? window.event : null);  //此方法为了在firefox中的兼容  
		        evt.returnValue='';                 //可添加内容：如果关闭页面，将需要重新登录系统
		        return '';
			}
	     }
	}
	else{
		$(window).bind('beforeunload',function(event){
				if(flag){        //flag为true，才刷新页面
					return '';                       //可添加内容：如果关闭页面，将需要重新登录系统
				}
			});
	}
	
	
	
	
 //设置默认角色	
 $("#jsmrUl").off("click","a").on("click","a",function(){
	 var dqjsdm = $(this).attr("dqjsdm");
	 $.post(_path + "/xtgl/index_cxSzmrjs.html?localeKey="+$("#localChange").val(),{dqjsdm:dqjsdm},function(data){
		 if(data.indexOf("成功")!=-1){
			 $.success(data);
			 $("#jsmrUl").find("a").each(function(){
				 if($(this).attr("dqjsdm") == dqjsdm){
					 if($(this).find("span").length<1){
						 $(this).append("<span class=\"glyphicon glyphicon-star-empty float_r\"></span>");
					 } 
				 }else{
					 $(this).find("span").remove();
				 }
			 });
		 }else if(data.indexOf("失败")!=-1){
			 $.error(data);
		 }else{
			 $.alert(data);
		 }
	 });
	 
 });
	
	
	
	//页面全部加载完成之后，再执行以下方法
	//window.onload=function(e){ 
		// 加载第一块内容
		$("#area_one").load(_path + "/xtgl/index_cxAreaOne.html?localeKey="+$("#localChange").val(), {}, function() {
			// 滚动条
	/*		$("#area_one").mCustomScrollbar({
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
			$('#area_one').mousewheel(function(event, delta, deltaX, deltaY) {
				event.preventDefault();
			});*/
			if($("#localChange").val()=='en_US'){//切换系统名称为英文的
				$("#xskbcxgd").text("...More");
				$("#jskbcxgd").text("...More");
				}
		});
		
		// 加载第二块内容
		$("#area_two").load(_path + "/xtgl/index_cxAreaTwo.html?localeKey="+$("#localChange").val(), {}, function() {
			// 滚动条
			$("#area_two").mCustomScrollbar({
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
				    updateOnBrowserResize: true,
				    updateOnContentResize: true,
				    updateOnSelectorChange: false,
				    autoExpandHorizontalScroll: true
				},
	 			theme:"dark-3"
			});
			$('#area_two').mousewheel(function(event, delta, deltaX, deltaY) {
				event.preventDefault();
			});
			if($("#localChange").val()=='en_US'){//切换系统名称为英文的
				$("#xwgd").text("...More");
			}
		});
		
		// 加载第三块内容
		$("#area_three").load(_path + "/xtgl/index_cxAreaThree.html?localeKey="+$("#localChange").val(), {}, function() {
			// 滚动条
			/*$("#area_three").mCustomScrollbar({
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
			$('#area_three').mousewheel(function(event, delta, deltaX, deltaY) {
				event.preventDefault();
			});*/
			if($("#localChange").val()=='en_US'){//切换系统名称为英文的
				$("#dbsygd").text("...More");
			}
			
		});
		
		// 加载第四块内容
		$("#area_four").load(_path + "/xtgl/index_cxAreaFour.html?localeKey="+$("#localChange").val(), {}, function() {
			// 滚动条
			/*$("#area_four").mCustomScrollbar({
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
			$('#area_four').mousewheel(function(event, delta, deltaX, deltaY) {
				event.preventDefault();
			});*/
			if($("#localChange").val()=='en_US'){//切换系统名称为英文的
			$("#xscjcxgd").text("...More");
			$("#rwlscxgd").text("...More");
			}
		});
		
		
		// 加载第五块内容(校历)
		$("#area_five").load(_path + "/xtgl/index_cxAreaFive.html?localeKey="+$("#localChange").val(), {}, function() {
			// 滚动条
			$("#area_two").mCustomScrollbar({
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
				    updateOnBrowserResize: true,
				    updateOnContentResize: true,
				    updateOnSelectorChange: false,
				    autoExpandHorizontalScroll: true
				},
	 			theme:"dark-3"
			});
			$('#area_two').mousewheel(function(event, delta, deltaX, deltaY) {
				event.preventDefault();
			});
			if($("#localChange").val()=='en_US'){//切换系统名称为英文的
				$("#xwgd").text("...More");
			}
		});
		
		
     //}

	function notEmptyTb(obj){
		if(!$.defined(obj)){
			obj = "/assets/images/ico/ico_apps.png";
		}
		return obj;
	}
	//应用显示名称过长用省略号表示
	function showGnmc(gnmc){
		if(gnmc.length > 9){
			gnmc = gnmc.substring(0,8)+"…";
		}
		return gnmc;
	}
		
	//查询最近使用
	$("#index_zjsy").load(_path + "/xtgl/index_cxZjsy.html?localeKey="+$("#localChange").val(), {}, function(data) {
		
		$("#index_zjsy").empty();
		var tempHtml = [];
		data = JSON.parse(data);
		if($.founded(data)) {
			$.each(data, function(index, item){
				var html='';
				html+='<li class="col-lg-4 col-md-4 col-sm-6 col-xs-6">';
				html+='<a onclick="clickMenu(\''+item.id+'\',\''+item.dyym+'\',\''+item.gnmkmc+'\'); return false;" href="#" target="_blank" title="'+item.gnmkmc+'">';
				html+='<img src="'+_stylePath+notEmptyTb(item.tblj)+'" style="width: 48px; height: 48px;">';
//				if(item.zjsybj=='2'){
//					html+='<h5>'+item.gnmkjc+'('+item.sycs+'次)</h5>';
//				}else{
					html+='<h5>'+showGnmc(item.gnmkjc)+'</h5>';
//				}
				
				html+='</a>';
				html+='</li>';
				tempHtml.push(html);
			});
			if(tempHtml.length>0) $("#index_zjsy").append(tempHtml.join(""));
		}
	});	
		
		
	
	//我的应用
	$("#index_wdyy").load(_path + "/xtgl/index_cxWdyy.html?localeKey="+$("#localChange").val(), {}, function(data) {
		
		$("#index_wdyy").empty();
		var tempHtml = [];
		data = JSON.parse(data);
		if($.founded(data)) {
			$.each(data, function(index, item){
				var html='';
				if(item.tbnmc!=null){
					html+='<li class="col-md-4 col-sm-4 col-xs-6">';
					html+='<a onclick="clickMenu(\''+item.id+'\',\''+item.dyym+'\',\''+item.gnmkmc+'\'); return false;" href="#" target="_blank" title="'+item.gnmkmc+'">';
					html+='<div class="icon-div">'+item.tbnmc+'</div>';
					html+='<h5>'+showGnmc(item.tbmc==null?item.gnmkmc:item.tbmc)+'</h5>';
					html+='</a>';
					html+='</li>';
					tempHtml.push(html);
				}else{
					html+='<li class="col-md-4 col-sm-4 col-xs-6">';
					html+='<a onclick="clickMenu(\''+item.id+'\',\''+item.dyym+'\',\''+item.gnmkmc+'\'); return false;" href="#" target="_blank" title="'+item.gnmkmc+'">';
					html+='<img src="'+_stylePath+notEmptyTb(item.tblj)+'" alt='+item.gnmkmc+' >';
					html+='<h5>'+showGnmc(item.tbmc==null?item.gnmkmc:item.tbmc)+'</h5>';
					html+='</a>';
					html+='</li>';
					tempHtml.push(html);
				}
			});
			if(tempHtml.length>0) $("#index_wdyy").append(tempHtml.join(""));
		}
	});
	
	//我的应用设置按钮
	$("#wdyy_szbtn").click(function(){
		var name="管理我的应用";
		if($("#localChange").val()=='en_US'){
			name="Manage my application";
		}
		$.showDialog(_path +"/xtgl/index_cxGlwdyyView.html",name, $.extend({},viewConfig,{
			modalName:"wdyyModal",
			width : ($("#yhgnPage").innerWidth())+"px",
			height: "800px"
		}));
	});
	
	
	$("#area_three").off("click","a[name='xxdlTail']").on("click","a[name='xxdlTail']",function(){
		$.post(_path + '/xtgl/index_cxXxdlztgx.html',
				   {zjxx:$(this).attr("data-zjxx")},
				function(data){
					   
				},'json');
	});
	
	
	
	
});



