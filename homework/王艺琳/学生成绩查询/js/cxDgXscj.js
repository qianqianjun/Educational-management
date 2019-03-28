jQuery(function($){
	//查询参数
	function paramMap(){
		return {
			"xnm"	: $("#xnm").val(),
			"xqm"	: $("#xqm").val()
		};
	}
	
	//验证表单
	$("#searchForm").validateForm({
		beforeValidated:function(){
			return true;
		}		
	});	
	
	
	
	function getGridColModel(){
		var colModelArr = [];
		if($("#jsxx").val() == "xs"){
			colModelArr = [
			   			{label:'查看',name:'', index: '',align:'center',width:'50px',
			   				formatter:	function(cellvalue, options, rowObject) {
			   				return "<a href='#' class='clj' onclick=\"ckCjxqLink('"+rowObject.xh_id+"','"+rowObject.jxb_id+"','"+rowObject.xnm+"','"+rowObject.xqm+"','"+rowObject.kcmc+"')\">查看</a>";
			   			}},
			   			{label:'学年',name:'xnmmc', index: 'xnmmc',align:'center',width:'90px'},
			   			{label:'学期',name:'xqmmc', index: 'xqmmc',align:'center',width:'50px'},
			   			{label:'课程代码',name:'kch', index: 'kch',align:'left',width:'120px'},
			   			{label:'课程名称',name:'kcmc', index: 'kcmc',align:'left',width:'120px'},
			   			{label:'课程性质',name:'kcxzmc', index: 'kcxzmc',align:'left',width:'100px'},
			   			{label:'学分',name:'xf', index: 'xf',align:'center',width:'50px'},
			   			{label:'成绩',name:'cj', index: 'cj',align:'center',width:'50px'},
			   			{label:'成绩备注',name:'cjbz', index: 'cjbz',align:'left',width:'100px'},
			   			{label:'绩点',name:'jd', index: 'jd',align:'center',width:'50px'},
			   			{label:'成绩性质',name:'ksxz', index: 'ksxz',align:'left',width:'100px'},
			   			{label:'是否成绩作废',name:'cjsfzf', index: 'cjsfzf',align:'left',width:'80px'}
			   		];
					
					if($("#sxxdm").val() == '13613') {
			   			colModelArr.push({label:'主要课程',align:'center',width:'150px',name:'sfxwkc',index:'sfxwkc'});
			   		}else {
			   			colModelArr.push({label:'是否学位课程',align:'center',width:'150px',name:'sfxwkc',index:'sfxwkc'});
			   		}
			
			colModelArr.push(
			   			{label:$.i18n.jwglxt["kkxymc"],name:'kkbmmc', index: 'kkbmmc',align:'left',width:'120px'},
			   			{label:'课程标记',name:'kcbj', index: 'kcbj',align:'left',width:'100px'},
			   			{label:'课程类别',name:'kclbmc', index: 'kclbmc',align:'left',width:'100px'},
			   			{label:'课程归属',name:'kcgsmc', index: 'kcgsmc',align:'left',width:'100px'},
			   			{label:'教学班',name:'jxbmc', index: 'jxbmc',align:'left',width:'120px'},
			   			{label:'任课教师',name:'jsxm', index: 'jsxm',align:'left',width:'120px'},
			   			{label:'学号',name:'xh', index: 'xh',align:'left',width:'120px'},
			   			{label:'姓名',name:'xm', index: 'xm',align:'left',width:'80px'},
			   			{label:'性别',name:'xb', index: 'XB',align:'center',width:'50px'},				
			   			{label:'学生类别',name:'xslb', index: 'xslb',align:'left',width:'100px'},	
			   			{label:'学院',name:'jgmc', index: 'jgmc',align:'left',width:'120px'},
			   			{label:'专业',name:'zymc', index: 'zymc',align:'left',width:'120px'},
			   			{label:'年级',name:'njmc', index: 'njmc',align:'left',width:'50px'},
			   			{label:'班级',name:'bj', index: 'bj',align:'left',width:'120px'}
          
					);
			   		
			   		if($("#sxxdm").val() == '10868') {
			   			colModelArr.push(
			   				{label:'卷一成绩',name:'jycj', index: 'jycj',align:'center',width:'60px'},
			   				{label:'卷一补考成绩',name:'jybkcj', index: 'jybkcj',align:'center',width:'60px'});
			   		}
			   		
			   		colModelArr.push(
			   			
			   			{label:'百分制成绩',name:'bfzcj',index:'bfzcj',align:'center',width:'50px',hidden:true},
			   			{label:'',name:'xh_id', index: 'xh_id', hidden: true},
			   			{label:'',name:'xnm', index: 'xnm', hidden: true},
			   			{label:'',name:'xqm', index: 'xqm', hidden: true},
			   			{label:'',name:'jxb_id', index: 'jxb_id', hidden: true} 
			   		);

			   		return colModelArr;
			   		
			   		
		}else{	
			colModelArr = [
			               {label:'查看',name:'', index: '',align:'center',width:'50px',
			            	   formatter:	function(cellvalue, options, rowObject) {
			            		   return "<a href='#' class='clj' onclick=\"ckCjxqLink('"+rowObject.xh_id+"','"+rowObject.jxb_id+"','"+rowObject.xnm+"','"+rowObject.xqm+"','"+rowObject.kcmc+"')\">查看</a>";
			            	   }},
			            	   {label:'学年',name:'xnmmc', index: 'xnmmc',align:'center',width:'90px'},
			            	   {label:'学期',name:'xqmmc', index: 'xqmmc',align:'center',width:'50px'},
			            	   {label:'学生类别',name:'xslb', index: 'xslb',align:'left',width:'100px'},	
			            	   {label:'学号',name:'xh', index: 'xh',align:'left',width:'120px'},
			            	   {label:'姓名',name:'xm', index: 'xm',align:'left',width:'80px'},
			            	   {label:'性别',name:'xb', index: 'XB',align:'center',width:'50px'},				
			            	   {label:'学院',name:'jgmc', index: 'jgmc',align:'left',width:'120px'},
			            	   {label:'专业',name:'zymc', index: 'zymc',align:'left',width:'120px'},
			            	   {label:'年级',name:'njmc', index: 'njmc',align:'left',width:'50px'},
			            	   {label:'班级',name:'bj', index: 'bj',align:'left',width:'120px'},	
			            	   {label:$.i18n.jwglxt["kkxymc"],name:'kkbmmc', index: 'kkbmmc',align:'left',width:'120px'},
			            	   {label:'课程代码',name:'kch', index: 'kch',align:'left',width:'120px'},
			            	   {label:'课程名称',name:'kcmc', index: 'kcmc',align:'left',width:'120px'},
			            	   {label:'教学班',name:'jxbmc', index: 'jxbmc',align:'left',width:'120px'},
			            	   {label:'任课教师',name:'jsxm', index: 'jsxm',align:'left',width:'120px'},
			            	   {label:'学分',name:'xf', index: 'xf',align:'center',width:'50px'}            
			            	   ];
			
					if($("#sxxdm").val() == '10868') {
						colModelArr.push(
								{label:'卷一成绩',name:'jycj', index: 'jycj',align:'center',width:'60px'},
								{label:'卷一补考成绩',name:'jybkcj', index: 'jybkcj',align:'center',width:'60px'});
					}
					
					colModelArr.push(
							{label:'成绩',name:'cj', index: 'cj',align:'center',width:'50px'},
							{label:'百分制成绩',name:'bfzcj',index:'bfzcj',align:'center',width:'50px',hidden:true},
							{label:'成绩备注',name:'cjbz', index: 'cjbz',align:'left',width:'100px'},
							{label:'考试性质',name:'ksxz', index: 'ksxz',align:'left',width:'100px'},
							{label:'考试性质代码',name:'ksxzdm', index: 'ksxzdm',align:'left',width:'100px',hidden:true},
							{label:'绩点',name:'jd', index: 'jd',align:'center',width:'50px'},
							{label:'课程标记',name:'kcbj', index: 'kcbj',align:'left',width:'100px'},
							{label:'课程类别',name:'kclbmc', index: 'kclbmc',align:'left',width:'100px'},
							{label:'课程归属',name:'kcgsmc', index: 'kcgsmc',align:'left',width:'100px'},
							{label:'课程性质',name:'kcxzmc', index: 'kcxzmc',align:'left',width:'100px'},
							{label:'是否成绩作废',name:'cjsfzf', index: 'cjsfzf',align:'left',width:'80px'},
							{label:'',name:'xh_id', index: 'xh_id', hidden: true},
							{label:'',name:'xnm', index: 'xnm', hidden: true},
							{label:'',name:'xqm', index: 'xqm', hidden: true},
							{label:'',name:'jxb_id', index: 'jxb_id', hidden: true} 
					);
					
					if($("#sxxdm").val() == '13613') {
						colModelArr.push({label:'主要课程',align:'center',width:'150px',name:'sfxwkc',index:'sfxwkc'});
					}else {
						colModelArr.push({label:'是否学位课程',align:'center',width:'150px',name:'sfxwkc',index:'sfxwkc'});
					}
					return colModelArr;
			
			}
		}
	
		
	
	
	var TempGrid = $.extend({},BaseJqGrid,{  
		postData	:paramMap(),
		multiselect : false,
		pager		: "#pager", //分页工具栏  
		datatype 	: "local",
		resizeHandle:"#searchBox",
	    shrinkToFit	: false,
	    url			: _path + '/cjcx/cjcx_cxDgXscj.html?doType='+'query', //这是Action的请求地址  
	    colModel:getGridColModel(),
	    loadComplete: function () {
			setTimeout(function(){
				var ids = $("#tabGrid").jqGrid("getDataIDs");//获取所有行的id
				var rowDatas = $("#tabGrid").jqGrid("getRowData");//获取所有行的数据
				for(var ii=0;ii < rowDatas.length;ii++){
				    var rowData = rowDatas[ii];	
			        if(rowData.bfzcj < 60 ){//如果某一行中的“tax”为0，那就把这一整行的背景颜色设为红色
			            $("#"+ids[ii]).has("td").attr("style","color:red");
			        }else if(rowData.bfzcj >= 60 && (rowData.ksxzdm =="11" || rowData.ksxzdm =="16"|| rowData.ksxzdm =="17")){
			        	 $("#"+ids[ii]).has("td").attr("style","color:blue");
			        }
				}
			}, 0);
		}
	});
	
	$('#tabGrid').loadJqGrid(TempGrid);

	//绑定导出操作
	$("#btn_dc").click(function () {
		//导出处理编号 规则:JW_模块编号_业务名称 如：JW_N305005_XSCXCJ
		var dcclbh = 'JW_N305005_XSCXCJ';
		//页面高级查询条件 若不是高级查询 则把查询条件组装成map传递
//		var requestMap = $("#searchBox").searchBox("getConditions");
		var requestMap = paramMap();
		if ($("#jsxx").val() != "xs") {
			requestMap["xhxm"]   = $('#xh_id').val();
			requestMap["kkbm_id"] = $('#kkbm_id_cx').val();
			requestMap["kclbdm"]  = $('#kclbdm_cx').val();
			requestMap["kch"] 	  = $('#kch_cx').val();
			requestMap["cjbzdm"]  = $('#cjbzdm_cx').val();
			requestMap["xqh_id"]  = $('#xq_cx').val();
			requestMap["njdm"]    = $('#njdm_cx').val();
			requestMap["zyh_id"]  = $('#zyh_id_cx').val();
			requestMap["bjdm"]    = $('#bj_cx').val();
			requestMap["kcxzdm"]  = $('#kcxzdm_cx').val();
			requestMap["kcgsdm"]  = $('#kcgsdm_cx').val();
			requestMap["jg_id_cx"]   = $('#jg_id_cx').val();
			requestMap["zt"]      = $('#zt_cx').val();
			requestMap["ccdm"]    = $('#ccdm_cx').val();
			requestMap["xslb"]  =$('#xslb').val();
			requestMap["cjxzm"] = $("#ksxz").val();
		}
		//用于查询要导出的数据
		var url = _path + '/cjcx/cjcx_dcListByXs.html';			
		//TempGrid必须是全局对象
		$.exportDialog(url,dcclbh,requestMap ,TempGrid.colModel,'#tabGrid');
	});
	
	//=================================自定义组合排序实现逻辑代码 -begin===============================================
	$("#btn_sortSetting").attr("title","点击设置记录排序优先级!").tooltip({container:'body'});
	//绑定设置排序点击事件
	$("#btn_sortSetting").click(function(event){
		//点击之后自动收缩提示框
		$("#btn_sortSetting").tooltip("hide");
		//调用公共排序弹窗
		$.showSortDialog('N305005','N305005',function(sortArr){
		});
	});
	
	//绑定查询操作
	$("#search_go").click(function () {
		var map = paramMap();
		map["xhxm"]   = $('#xh_id').val();
		map["kkbm_id"] = $('#kkbm_id_cx').val();
		map["kclbdm"]  = $('#kclbdm_cx').val();
		map["kch"] 	   = $('#kch_cx').val();
		map["cjbzdm"]  = $('#cjbzdm_cx').val();
		map["xqh_id"]  = $('#xq_cx').val();
		map["njdm"]    = $('#njdm_cx').val();
		map["zyh_id"]  = $('#zyh_id_cx').val();
		map["bjdm"]    = $('#bj_cx').val();
		map["kcxzdm"]  = $('#kcxzdm_cx').val();
		map["kcgsdm"]  = $('#kcgsdm_cx').val();
		map["jg_id_cx"]   = $('#jg_id_cx').val();
		map["zt"]      = $("#zt_cx").val();
		map["ccdm"]    = $("#ccdm_cx").val();
		map["xslb"] = $("#xslb").val();
		map["cjxzm"] = $("#ksxz").val();
		search('tabGrid',map);
	});
	
});

//链接
function ckCjxqLink(xh_id,jxb_id,xnm,xqm,kcmc) {
	$.showDialog(_path +"/cjcx/cjcx_cxCjxq.html",'查看成绩详情',$.extend(true,{},viewConfig,{width:'1086px',
		data:{
	 		"xh_id"	: xh_id,
	 		"jxb_id": jxb_id,
	 	  	"xnm"	: xnm,
	 	  	"xqm"	: xqm,
	 	  	"kcmc"	: kcmc
	 	  }	
	}));
};

/**级联方法开始*/
var map = {};
map["jg_id"] = jQuery('#jg_id_cx').val();
map["zyh_id"]=jQuery("#zyh_id_cx").val();
map["bh_id"]=jQuery("#bj_cx").val();
map["njdm_id"]=jQuery("#njdm_cx").val();
map.mapper = {"jg_id_cx":"jg_id","zyh_id_cx":"zyh_id","bj_cx":"bh_id","njdm_cx":"njdm_id"};
$.bindChangeEvent("#jg_id_cx","","#zyh_id_cx","","#bj_cx","#njdm_cx", map,function(){},"全部",true);
/**级联方法结束*/
 