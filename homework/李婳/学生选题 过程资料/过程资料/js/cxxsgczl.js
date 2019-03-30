jQuery(function($){
	
	var TempGrid = $.extend({},BaseJqGrid,{  

		postData:paramMap(),
		rownumbers : true,
		pager: null, //分页工具栏  
		resizeHandle:"#resizeHandle",
		multiselect:false,
		multiboxonly:true,
		beforeSelectRow:function(){
			$("#tabGrid").jqGrid('resetSelection');
			return(true);
		},
		
		
	    url: _path + '/xsbysjgl/xsgczl_cxXsgczlIndex.html?doType=query', //这是Action的请求地址  
	    colModel:[
	        {label:'选题id',name:'xtb_id',index:'xtb_id',align:'left',width:'120px',key:true,hidden:true},
			{label:'学号',name:'xh_id',index:'xh_id',align:'left',width:'120px',hidden:true},
			{label:'学号',name:'xs_xh',index:'xs_xh',align:'left',width:'120px'},
			{label:'课题基本信息id',name:'kt_jbxx_id',index:'kt_jbxx_id',hidden:true},
			{label:'学年',name:'xn_mc', index: 'xn_mc',align:'left',width:'120px'},
			{label:'学期',name:'xq_mc', index: 'xq_mc',align:'left',width:'120px'},
			{label:'题目名称',name:'ktmc', index: 'ktmc',align:'left',width:'360px'},
			{label:'任务书下载',name:'xz', width:'240px',index:'',align:'center',
				formatter:function(cellvalue, options, rowObject){
					if(rowObject.rws_lj == '' || rowObject.rws_lj == null) {
						return "<span class='label label-default'>未上传</span>";
					}else {
						return "<a href='javascript:void(0)' name='xzrws-main' data1='"+rowObject.rws_lj+"' data2='"+rowObject.rws_mc+"' >"+rowObject.rws_mc+"</a>";
					}
		      	}
			},
			{label:'指导教师',name:'kt_jg_xm', index: 'kt_jg_xm',align:'left',width:'120px'},
			{label:'职称',name:'kt_zc_mc', index: 'kt_zc_mc',align:'left',width:'120px'},
			{label:'手机号',name:'sjhm', index: 'sjhm',align:'left',width:'120px'},
			{label:'课题主管学院',name:'kt_zgjg_mc', index: 'kt_zgjg_mc',align:'left',width:'150px'},
			{label:'任务书路径',name:'rws_lj', index: 'rws_lj',align:'center',hidden: true},
			{label:'任务书路径',name:'rws_mc', index: 'rws_mc',align:'center',hidden: true}
	        
			
		],
		//选中行时
		onSelectRow:function(){debugger;
			var ids =  $("#tabGrid").getKeys();
			var id=$("#tabGrid").jqGrid('getGridParam','selrow');//获取选中行id
			var rowData=$("#tabGrid").jqGrid('getRowData',id);//通过选中行id获取选中行数据
			var ktmc=rowData.ktmc;//获取某个字段值
			var xtb_id=rowData.xtb_id;
			var kt_jbxx_id=rowData.kt_jbxx_id;
			$("#jqt").load( _path + '/xsbysjgl/xsgczl_cxXsgczlInformation.html',{"xtb_id":id,"kt_jbxx_id":kt_jbxx_id},function(data){
				$('#xzkt_mc').html(ktmc);
				$("#hid_ktjbxx_id").val(kt_jbxx_id);
				$("#hid_xtb_id").val(xtb_id);
				$("#jqt").show();
				//默认显示日志页面
				$("#zllx00").click();
			});
			
		},
		
		gridComplete:function(){
			if($("#tabGrid").getDataIDs().length>0){
				$("#tabGrid").jqGrid('setSelection',$("#tabGrid").getDataIDs()[0]);//默认选中第一行
				/*init();*/
			}
		}
	});
	
	
	
	var init=function(){
		var ids =  $("#tabGrid").getKeys();
		var id=$("#tabGrid").jqGrid('getGridParam','selrow');//获取选中行id
		var rowData=$("#tabGrid").jqGrid('getRowData',id);//通过选中行id获取选中行数据
		var ktmc=rowData.ktmc;//获取某个字段值
		var xtb_id=rowData.xtb_id;
		var kt_jbxx_id=rowData.kt_jbxx_id;
		$("#jqt").load( _path + '/xsbysjgl/xsgczl_cxXsgczlInformation.html?xtb_id='+ids+'&&kt_jbxx_id='+kt_jbxx_id,{},function(data){
			$("#hid_ktjbxx_id").val(kt_jbxx_id);
			$("#hid_xtb_id").val(xtb_id);
			$("#jqt").show();
			//默认显示日志页面
			$("#zllx00").click();
		});
		
	}
	
	
	$("#tabGrid").loadJqGrid(TempGrid);
	
	
	//下载
	$("#tabGrid").on("click","a[name='xzrws-main']",function(){
		//构建form
		$.buildForm("downForm",_path+"/xsbysjgl/xsgczl_cxDownRws.html", {rws_lj: $(this).attr("data1"),rws_mc: $(this).attr("data2")}).submit();
	});
	
	//下载过程记录
	$("#btn_xzgcjl").click(function(){
		$.post(_path +'/xsbysjgl/xsgczl_xzgcjlXsgczl.html',function(data){
			if(data.indexOf("成功") > -1){
				window.open(_path+"/js/plugins/pdfjs/generic/web/viewer.htm?file="+data.split("#")[0]);
			}else{
				$.alert(data);
			}
		})
	})
	
	//过程记录记录表(周志的）
	$("#btn_gcgljlb").click(function(){
		$.post(_path +'/xsbysjgl/xsgczl_gcgljlbXsgczl.html',function(data){
			if(data.indexOf("成功") > -1){
				window.open(_path+"/js/plugins/pdfjs/generic/web/viewer.htm?file="+data.split("#")[0]);
			}else{
				$.alert(data);
			}
		})
	})
	
	
	
	//查询条件
	function paramMap(){
		var map = {
			"mc" : jQuery("#mc").val(),
			"sfyx" : jQuery("#sfyx").val()
		};
		return map;
	}
	
	
});

