
//@ sourceURL=cxXsxtIndex.js


jQuery(function($){
	
	//*********加载已选题数
	setYxts();
	//*********加载具体的选题信息
	loadXsxtList();
	
	var sfakdnjzykzrs = $("#sfakdnjzykzrs").val();//是否按可带年级专业控制人数
	
	var TempGrid = $.extend({},BaseJqGrid,{
		postData:paramMap(),
		pager: "#pager", //分页工具栏  
		resizeHandle:"#tabGrid_handle",
		multiselect:false,//不显示复选框
		autowidth: true,
	    shrinkToFit: true,
	    url: _path + '/xsbysjgl/xsxt_cxXsxtIndex.html?doType=query', //这是Action的请求地址  
	    colModel:[
			{label:'课题申报信息ID',name:'sbxx_id',index:'sbxx_id',hidden:true,key:true},
			{label:'课题主管学院',name:'zgjg_mc', index: 'zgjg_mc',align:'left',width:'150px'},
			{label:'题目名称',name:'ktmc', index: 'ktmc',align:'left',width:'300px',sortable:false,
    	 		formatter:function(cellvalue, options, rowObject){
					return "<a href='javascript:void(0)' name='showKtxx-main' data='"+rowObject.sbxx_id+"' >"+rowObject.ktmc+"</a>";
		      	}
			},
			{label:'指导教师工号',name:'jgh', index: 'jgh',align:'left',width:'120px'},
			{label:'指导教师姓名',name:'jg_xm',index:'jg_xm',align:'left',width:'120px',sortable:false,
    	 		formatter:function(cellvalue, options, rowObject){
					return "<a href='javascript:void(0)' name='showTea-main' data='"+rowObject.jgh_id+"' data1='"+rowObject.kt_jbxx_id+"'>"+rowObject.jg_xm+"</a>";
		      	}
			},
			{label:'指导教师职称',name:'zc_mc', index: 'zc_mc',align:'left',width:'120px'},
			{label:'该课题可接收总人数',name:'zdrs', index: 'zdrs',align:'left',width:'120px'},
			{label:'该课题已接收人数',name:'yjsrs', index: 'yjsrs',align:'left',width:'120px'},
			{label:'该课题待确认人数',name:'dqrrs', index: 'dqrrs',align:'left',width:'120px'},
			{label:'本专业所有课题可带人数',name:'bzykdrs', index: 'bzykdrs',align:'left',width:'170px'},
			{label:'本专业所有课题待审核人数',name:'bzydshrs', index: 'bzydshrs',align:'left',width:'170px'},
			{label:'本专业所有课题已选上人数',name:'bzyyxsrs', index: 'bzyyxsrs',align:'left',width:'170px'},
			{label:'操作',name:'cz', width:'80px',index:'cz',align:'center',formatter:function(cellvalue, options, rowObject){
				var html = [];
			 	html.push("<div class='an align-center'>");
			 	if(rowObject.gryxts!="0"){
			 			//判断：是否允许改选及是否允许退选，如果都允许，则可以在主界面进行退选操作，否则不允许
			 			if($("#sfkxt").val()=="1"&&((rowObject.xtb_shzt!="3"&&$("#sfktx").val()=="1")||(rowObject.xtb_shzt=="3"&&$("#sfkgx").val()=="1"))){   
				 			html.push("<button class='btn btn-danger btn-sm' name='tx-main' data='"+rowObject.kt_jbxx_id+"' type='button'>退选</button>");
				 		}else{
				 			html.push("已选");
				 		}
			 	}else{
			 		if($("#sfkxt").val()=="1"){
			 			html.push("<button class='btn btn-primary btn-sm' name='xt-main' data='"+rowObject.kt_jbxx_id+"' type='button'>选题</button>");
			 		}
			 	}
			 	html.push("</div>");
				return html.join("");
	      	 }}
		],
		sortname: 'xn_mc', //首次加载要进行排序的字段 
	 	sortorder: 'desc',
 		gridComplete:function(){//如果不按可带年级专业控制人数隐藏一些列
 			if("0".equals(sfakdnjzykzrs)){
 				$("#tabGrid").hideCol("bzykdrs");
 				$("#tabGrid").hideCol("bzydshrs");
 				$("#tabGrid").hideCol("bzyyxsrs");
 			}
 		}		
	});
	
	$("#tabGrid").loadJqGrid(TempGrid);
	
	//查询操作
	$("#search_go").click(function(){
		search('tabGrid',paramMap());
	});
	
	$("#captionXtxxId").text("毕业设计选题列表");
	
	
	//验证手机号码
	function checkSubmitMobil() { 
		if ($("#sjhm").val() == "") { 
			$.alert("手机号码不能为空！"); 
		    $("#sjhm").focus(); 
		    return false; 
		} 
		if (!(/^((\d{3}-\d{8}|\d{4}-\d{7,8})|(1[3|5|7|8][0-9]{9}))$/.test($.trim($('#sjhm').val())))) {	
			$.alert("请输入有效的11位手机号码！"); 
			$("#sjhm").focus(); 
			return false; 
			} 
		return true; 
	}
	
	//保存学生联系方式
	$("#save_lxfs").click(function(){
		$.ajaxSetup({async:false});
		if(checkSubmitMobil()){
			$.post(_path + '/xsbysjgl/xsxt_bcXslxfs.html',
				   {sjhm:$("#sjhm").val()},
				function(data){
					   if(data.indexOf("成功") > -1) {
							$.success(data);
						}else if(data.indexOf("失败") > -1) {
							$.error(data);
						} else {
							$.alert(data);
						}
				},'json');
		}
		$.ajaxSetup({async:true});
	});
	
	
	
	
	//加载选题列表
	function loadXsxtList(){
		var html = [];
			$.ajaxSetup({async:false});
			$.post(_path + '/xsbysjgl/xsxt_cxXsyxxtIndex.html',
				   paramMap(),
				function(data){
						if(data!=null){
							$("#right_ul_xsxt").empty();
							for(var i=0; i<data.length; i++){
								html.push("<li id='right_"+data[i].xtb_id+"' class='list-group-item' data-itemidx='0' style='cursor: pointer;'>");
								html.push("<input type='hidden' name='hid_xtb_id' value='"+data[i].xtb_id+"'>");
								html.push("<div class='item' style='cursor: pointer;'>");
								html.push("<table width='100%'>");
								html.push("<tbody>");
								html.push("<tr>");
								html.push("<td><p class='num'>"+data[i].zy+"</p></td>");
								html.push("<td class='arraw-px' style='display: none;'><a class='fa fa-arrow-up padding-lr10' href='javascript:void(0);'></a><br><a class='fa fa-arrow-down padding-lr10' href='javascript:void(0);'></a></td>");
								html.push("<td><p class='sxbj'><font color='"+(data[i].shzt=="3"?"blue":"red")+"'><i>"+data[i].shztmc+"</i></font></p></td>");
								html.push("<td><p class='tmmc' title='"+data[i].ktmc+"'>"+data[i].ktmc+"</p></td>");
								html.push("<td><p class='teachers popover-demo' title='"+data[i].kt_jg_xm+"("+data[i].kt_jgh+")'><span>"+data[i].kt_jg_xm+"("+data[i].kt_jgh+")</span></p></td>");
								html.push("<td><p class='jsrssx'>"+data[i].kt_zdrs+"</p></td>");
								html.push("<td><p class='but'>");
								//当为下列两种情况汇报时，可进行退改选操作：(1)如果选题已经被确认，并且允许改选  (2)如果选题还没有被确认，并且允许退选
								if($("#sfkxt").val()=="1"&&((data[i].shzt=="3"&&$("#sfkgx").val()=="1")||(data[i].shzt!="3"&&$("#sfktx").val()=="1"))){
									html.push("<button class='btn btn-danger btn-sm btn-tk' data-wz='rightpage' name='tx-left' data='"+data[i].kt_jbxx_id+"' type='button'>退选</button>");		
								}
								html.push("</tr>");
								html.push("</tbody>");
								html.push("</table>");
								html.push("</div>");
								html.push("</li>");
							}
							 $("#right_ul_xsxt").append(html.join(""));
						}
				},'json');
			$.ajaxSetup({async:true});
			myDragsort();        //加载拖拉效果
	}
	
	
	//*********查看教师信息
	$("#tabGrid").off("click","a[name='showTea-main']").on("click","a[name='showTea-main']",function(){
		var jgh_id = $(this).attr("data");
		var kt_jbxx_id = $(this).attr("data1");
		if(jgh_id!='--'&&jgh_id!=null&&jgh_id!=''){
			$.showDialog(_path+'/xsbysjgl/xsxt_ckJsxxView.html','教师简介',$.extend({},viewConfig,
					{width: ($("#yhgnPage").innerWidth()-600)+"px",data: {'jgh_id':jgh_id,'kt_jbxx_id':kt_jbxx_id}}
			));
		}else{
			$.error("无教师信息！");
			return false;
		}
	});
	
	
	//*********查看课题信息
	$("#tabGrid").off("click","a[name='showKtxx-main']").on("click","a[name='showKtxx-main']",function(){
		$.showDialog(_path + "/xsbysjgl/xsxt_cxKtxxView.html?sbxx_id=" + $(this).attr("data"),"查看",{
			width:"1000px",
			modalName:"xgModal",
			buttons:{
				cancel : {
					label : "关 闭",
					className : "btn-default"
				}
			}
		}
		);
	});
	
	
	
	
	
	//*********学生选题(主页面)
	function setYxts(){
		$.ajaxSetup({async:false});
		$.post(_path+"/xsbysjgl/xsxt_getYxts.html",{},
			function(data){
				$("#yxkts").text(data);
		},'json');
		$.ajaxSetup({async:true});
	}
	
	
	//*********学生选题(主页面)
	function setYxts(){
		$.ajaxSetup({async:false});
		$.post(_path+"/xsbysjgl/xsxt_getYxts.html",{},
			function(data){
				$("#yxkts").text(data);
		},'json');
		$.ajaxSetup({async:true});
	}
	
	
	//*********联系方式更新提醒(主页面)
	function lxfsGxtx(){
		if($("#sfkxt").val()=="1"&&$("#sjhm").val()==null||$("#sjhm").val()==""){
			if($("#sfyxjStatus").val()=='1'&&$("#sfzxStatus").val()=='1'){
				$.alert("为方便指导教师联系您，请在当前界面上维护手机号!");
			}
		}
	}
	
	
	
	//*********进行志愿排序
	function myDragsort(){
		$(".list-group").each(function(){
			$(this).dragsort("destroy");
			$(this).dragsort({
				dragSelector: "li", 
				dragBetween: false, 
				dragEnd: saveOrder, 
				placeHolderTemplate: "<li class='list-group-item'><div></div></li>",
				scrollSpeed: 5
			});
		});	
	}
	
	
	
	//*********保存排序结果
	function saveOrder(){
		var xtb_zys = [];
		var xtb_ids = [];
		$(".list-group").each(function(index,item){
			$(item).find("li").each(function(index1,item1){
				if((index1+1)!=parseInt($(item1).find(".num").text())){
					xtb_zys.push(index1+1);
					xtb_ids.push($(item1).find("input[name='hid_xtb_id']").val());
				}
			});
		});
		$.ajaxSetup({async:false});
		$.post(_path+"/xsbysjgl/xsxt_bcXtZypx.html",
			{xtb_zys:xtb_zys.join(","),xtb_ids:xtb_ids.join(",")},
			function(data){
				setTimeout(function(){
					if(data=="success"){
						reSort();
					}else{
						$.error("排志愿失败！",function(){});
					}
				},1); 
			},'json');
		$.ajaxSetup({async:true});
	}
	
	
	
	function reSort(){
		$(".list-group").each(function(index,item){
			$(item).find("li").each(function(index1,item1){
				if((index1+1)!=parseInt($(item1).find(".num").text())){
					$(item1).find(".num").text(index1+1);
				}
			});
		});
	}
	

		//查询参数
		function paramMap(){
			return {
				"zgjg_id"   : $("#zgjg_id").val(),
				"ktmc"   : $("#ktmc").val(),
				"kt_sqr" : $("#kt_sqr").val()
			};
		}

		//*********学生选题(主页面)
		$("#tabGrid").off("click","button[name='xt-main']").on("click","button[name='xt-main']",function(){
			var kt_jbxx_id = $(this).attr("data");
			$(this).attr("disabled","disabled");         //先把按钮设置成不可点，防止像360浏览器，可以重复点击多次，一个题目选上多次
		$.post(_path + "/xsbysjgl/xsxt_isNotPdbyysjg.html",{},function(data){
		  if(data.indexOf("继续") > -1){
			$.post(_path + "/xsbysjgl/xsxt_isNotXt.html",{},function(data){
				if(data.indexOf("继续") > -1){
					$.ajaxSetup({async:false});
					$.post(_path+"/xsbysjgl/xsxt_bcXsxt.html",
						{kt_jbxx_id:kt_jbxx_id},
						function(data){
							if(data.indexOf("成功")!=-1){
								$.success(data,function(){
									refershGrid("tabGrid");
									loadXsxtList();   //重新加载已选题列表
									setYxts();        //重新加载已选题数
									saveOrder();      //重新进行志愿排序
								});
							}else if(data.indexOf("失败")!=-1){
								$.error(data,function(){});
							}else{
								$.alert(data,function(){});
							}
					},'json');
					$.ajaxSetup({async:true});
				}else{
					$.alert(data);
				}
				});
				  }else{
						$.alert(data,function(){
							refershGrid("tabGrid");
							loadXsxtList();   //重新加载已选题列表
							setYxts();        //重新加载已选题数
							saveOrder();      //重新进行志愿排序
							$(this).removeAttr("disabled");  
						});
					}
					});
			
		});


		//*********学生退选(主页面)
		$("#tabGrid").off("click","button[name='tx-main']").on("click","button[name='tx-main']",function(){
			var kt_jbxx_id = $(this).attr("data");
			$.confirm('您确定要退掉该课题吗？',function(isBoolean){
				if(isBoolean){
					$.ajaxSetup({async:false});
					$.post(_path+"/xsbysjgl/xsxt_scXsxt.html",
						{kt_jbxx_id:kt_jbxx_id},
						function(data){
							if(data.indexOf("成功")!=-1){
								$.success(data,function(){
									refershGrid("tabGrid");
									loadXsxtList();    //重新加载已选题列表
									setYxts();         //重新加载已选题数
									saveOrder();       //重新进行志愿排序
								});
							}else if(data.indexOf("失败")!=-1){
								$.error(data,function(){
								});
							}else{
								$.alert(data,function(){});
							}
					},'json');
					$.ajaxSetup({async:true});
				}
			});
		});

		//*********学生退选(右侧)
		$("#right_ul_xsxt").off("click","button[name='tx-left']").on("click","button[name='tx-left']",function(){
			var kt_jbxx_id = $(this).attr("data");
			$.confirm('您确定要退掉该课题吗？',function(isBoolean){
				if(isBoolean){
					$.ajaxSetup({async:false});
					$.post(_path+"/xsbysjgl/xsxt_scXsxt.html",
						{kt_jbxx_id:kt_jbxx_id},
						function(data){
							if(data.indexOf("成功")!=-1){
								$.success(data,function(){
									refershGrid("tabGrid");
									loadXsxtList();   //重新加载已选题列表
									setYxts();        //重新加载已选题数
									saveOrder();      //重新进行志愿排序
								});
							}else if(data.indexOf("失败")!=-1){
								$.error(data,function(){});
							}else{
								$.alert(data,function(){});
							}
					},'json');
					$.ajaxSetup({async:true});
				}
			});
		});
		
		
		//*********加载已选题数
		function setYxts(){
			$.ajaxSetup({async:false});
			$.post(_path+"/xsbysjgl/xsxt_getYxts.html",{},
				function(data){
					$("#yxkts").text(data);
			},'json');
			$.ajaxSetup({async:true});
		}
		
		//*********联系方式更新提醒
		lxfsGxtx();
});





