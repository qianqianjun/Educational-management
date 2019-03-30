jQuery(function($){
	function getGridColModel(){
		var colModelArr = [
            {label:'教学班id',name:'jxb_id', index: 'jxb_id',hidden:true,key:true},
			{label:'学年',name:'xnmc', index: 'xnmc',align:'center',width:'80px'},
			{label:'学期',name:'xqmmc', index: 'xqmmc',align:'center',width:'80px'},
			{label:'学号',name:'xh', index: 'xh',align:'center',width:'140px'},
			{label:'姓名',name:'xm', index: 'xm',align:'center',width:'100px'},
			{label:'性别',name:'xb', index: 'xb',align:'center',width:'60px'},
			{label:'班级',name:'bj', index: 'bj',align:'center',width:'100px'},
			{label:'课程代码',name:'kch', index: 'kch',align:'center',width:'100px'},
			{label:'课程名称',name:'kcmc', index: 'kcmc',align:'center',width:'100px'},
			{label:'教师信息',name:'jsxx', index: 'jsxx',align:'left',width:'100px'},
			{label:'重修标记',name:'cxbj', index: 'cxbj',align:'left',width:'100px'},
			{label:'自修标记',name:'zxbj', index: 'zxbj',align:'left',width:'100px'},
			{label:'考试名称',name:'ksmc', index: 'ksmc',align:'left',width:'100px'},
			{label:'试卷编号',name:'sjbh', index: 'sjbh',align:'left',width:'140px'},
			{label:'考试时间',name:'kssj', index: 'kssj',align:'center',width:'140px'},
			{label:'考试地点',name:'cdmc', index: 'cdmc',align:'center',width:'100px'},
			{label:'考试校区',name:'cdxqmc', index: 'cdxqmc',align:'center',width:'100px'}
	    ];
		if($("#xskszh").val() == '1') {
			colModelArr.push({label:'考试座号',name:'zwh', index: 'zwh',align:'center',width:'70px'});
		}
		colModelArr.push(
			{label:'考试备注',name:'ksbz', index: 'ksbz',align:'left',width:'100px'},
			{label:'教学班名称',name:'jxbmc', index: 'jxbmc',align:'center',width:'140px'},
			{label:'教学班组成',name:'jxbzc', index: 'jxbzc',align:'center',width:'140px'},
			{label:'校区名称',name:'xqmc', index: 'xqmc',align:'left',width:'100px'},
			{label:'上课时间',name:'sksj', index: 'sksj',align:'center',width:'140px'},
			{label:'上课地点',name:'jxdd', index: 'jxdd',align:'center',width:'100px'},
			//{label:'考试方式',name:'ksfs', index: 'ksfs',align:'center',width:'100px'},
			{label:'年级',name:'njmc', index: 'njmc',align:'center',width:'60px'},
			{label:'学院',name:'jgmc', index: 'jgmc',align:'center',width:'100px'},
			{label:'专业',name:'zymc', index: 'zymc',align:'center',width:'100px'}
		);
		return colModelArr;
 	}
	
	var TempGrid = $.extend(true,{},BaseJqGrid,{  
		pager: "pager", //分页工具栏  
		resizeHandle:"#searchForm",
	    shrinkToFit: false,
	    postData:paramMap(),
	    url: _path + '/kwgl/kscx_cxXsksxxIndex.html?doType=query', //这是Action的请求地址  
	    colModel: getGridColModel()
	});
	$("#tabGrid").loadJqGrid(TempGrid);
	
	$("#btn_dc").click(function(){
		//导出处理编号 规则:JW_模块编号_业务名称 如：JW_N358105_KSCX
		var dcclbh = 'JW_N358105_KSCX';
		//页面高级查询条件 若不是高级查询 则把查询条件组装成map传递
		var requestMap = paramMap();
		//用于查询要导出的数据
		var url = _path + '/kwgl/kscx_dcXsksxxList.html';			
		//TempGrid必须是全局对象
		$.exportDialog(url, dcclbh, requestMap, TempGrid.colModel, '#tabGrid');
	});
	
	$("#search_go").click(function(){
		search('tabGrid',paramMap());
	});
	//查询
	function paramMap(){
		var map ={};
		
			map['xnm']	= $("#cx_xnm").val();
			map['xqm'] 	= $("#cx_xqm").val();
			map['ksfsdm'] 	= $("#ksfsdm").val();
			map['ksmcdmb_id']= $("#cx_ksmcdmb_id").val();
			map['kch'] = $("#cx_kch").val();
			map['kc']	 = $("#kc_cx").val();
			map['ksrq'] = $("#cx_ksrq").val();
			if($("#isXs").val() == "false"){
				map["njdm_id"]	= $("#cx_njdm_id").val();
				map["jg_id"]	= $("#cx_jg_id").val();
				if($.founded($("#cx_zyh_id").val())){
					map["zyh_id"] = $("#cx_zyh_id").val();
				}
				if($.founded($("#cx_bh_id").val())){
					map["bh_id"] = $("#cx_bh_id").val();
				}
				map['xh'] = $("#cx_xh").val();
			}    
			var ids = $("#tabGrid").getKeys();
			if(ids != null && ids != ''){
				map['jxb_idsList'] = ids;
			}
		return map;
	}
	//级联
	$.bindChangeEvent("#cx_jg_id","","#cx_zyh_id","","#cx_bh_id","#cx_njdm_id");
	//根据学年学期级联考试名称
	$("#cx_xnm,#cx_xqm").change(function(e){	
		//保留原来的值
		
		//先删除
		$("#cx_ksmcdmb_id").find("option:gt(0)").remove();			
		//再查询
		$.post(_path + '/ksglcommon/common_cxKsmcByXnxq.html',{'xnm':$("#cx_xnm").val(),'xqm':$("#cx_xqm").val()},function(data){
			if($.founded(data)){
				$.each(data,function(index,item){
					$("#cx_ksmcdmb_id").append('<option value="'+ item.KSMCDMB_ID + '">' + item.KSMC + '</option>');
				});
			}
			$("#cx_ksmcdmb_id").trigger("chosen:updated");
		});
	});	
});