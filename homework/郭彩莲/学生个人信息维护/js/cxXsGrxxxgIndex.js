jQuery(function($) {
	var XgsqjlTabGrid = $.extend({},BaseJqGrid,{
		pager:"#xgsqjlPager",
		height : 300,
		resizeHandle:"#searchBox",
		multiselect	:true,
		viewrecords : true, // 是否显示行数
		autowidth 	: false,
		url:_path+"/xsxxxggl/xsxxwh_cxXsxgsqList.html?pkey="+$("#pkey").val(),		
		colModel:[
		    {label:'表ID',name:'xsxxxgsq_id', index:'xsxxxgsq_id',align:'center',key:true,hidden:true,width:'120px'},
		    {label:'学号ID',name:'xh_id', index:'xh_id',align:'center',hidden:true,width:'100px'},
		    {label:'sqzt',name:'sqzt', index:'sqzt',align:'center',hidden:true,width:'120px'},      
		    {label:'shzt',name:'shzt', index:'shzt',align:'center',hidden:true,width:'120px'},      
		    {label:'shzt',name:'shzt', index:'shzt',align:'center',hidden:true,width:'120px'},
		    {label:'流程跟踪',name:'', index: '',align:'center',width:'100px',
				formatter:function(cellvalue,options,rowObject){
					var res = "";
					if(rowObject["xsxxxgsq_id"] != ''){
						res = "<a class='clj' href='javascript:void(0);' onclick='ckWorkFlow(\""+rowObject["xsxxxgsq_id"]+"\")'>流程跟踪</a>";
					}
					return res;
				}
			},
		    {label:'学年',name:'xnmc', index:'xnmc',align:'center',width:'100px'},
		    {label:'学期',name:'xqmc', index:'xqmc',align:'center',width:'80px'},
		    {label:'申请状态',name:'sqztmc', index:'sqztmc',align:'center',width:'100px'},
		    {label:'申请时间',name:'sqsj', index:'sqsj',align:'center',width:'200px'},
		    {label:'审核状态',name:'shztmc', index:'shztmc',align:'center',width:'100px'},
		    {label:'最终审核时间',name:'zzshsj', index:'zzshsj',align:'center',width:'200px'},
		    {label:'附件',name:'fjmc',index:'fjmc',align:'center',width:'200px',
		    	formatter:function(cellvalue, options, rowObject){
			 	if(rowObject["fjFlg"]=="0"){
			 		return "无";
			 	}else{
			 		var res = "";
			 	 	res =res + '<a class="clj" onclick=ckScfj("' + rowObject["xsxxxgsq_id"] + '")>'+rowObject["fjmc"]+'</a>&nbsp;';
			 	 	res =res + '<a class="clj" onclick=xzScfj("' + rowObject["xsxxxgsq_id"] + '")>下载</a>';
				 	return res;
			 	}
			}},
		    {label:'操作',name:'', index:'',align:'center',width:'200px',
		    	formatter:function(cellvalue, options, rowObject){
				 	var res = "";
				 	//未提交或者被退回 允许修改
				 	if(rowObject["sqzt"]=="0" || rowObject["shzt"]=="4"){
				 		res = '<a class="clj" onclick=xgXsXgXxxx("' + rowObject["xsxxxgsq_id"] + '")>修改</a> ';
				 		res = res + '<a class="clj" onclick=scXsXgXxxx("' + rowObject["xsxxxgsq_id"] + '")>删除</a> ';
				 	}
				 	res =res + '<a class="clj" onclick=ckXsXgXxxx("' + rowObject["xsxxxgsq_id"] + '")>查看</a>';
	
				 	return res;
				}
		    }
		 ]
	});	
	$("#lsxgjlTabGrid").loadJqGrid(XgsqjlTabGrid);
	
	//绑定
	jQuery("#btn_sq").click(function () {
		$.showDialog(_path + '/xsxxxggl/xsgrxxwh_xgXsgrxx.html','申请修改个人信息',{
			width		: $("#yhgnPage").innerWidth()+"px",
			modalName	: "addModal",
			//是否需要进度条
			"progress"	: true,
//			"btnlock" 	: true,
			buttons		: {
				success : {
					label : "保存草稿",
					className : "btn-primary",
					callback : function() {
					    var $this = this;
					    $("[role='tab']").addClass("blue");
						jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_xgBcXsgrxx.html?sqzt=0");
						if(!$("#ajaxForm").valid() ){
							if($('input').hasClass('error')){
								$(".error").each(function(){
									var value=$(this).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().attr("id");
									
									if(value == undefined || value == "func_fields"){
										value=$(this).parent().parent().parent().parent().parent().parent().parent().attr("id");
									}
									if(value == undefined || value == "ajaxForm"){
										value=$(this).parent().parent().parent().parent().parent().parent().attr("id");
									}
									if(value == undefined || value == "ajaxForm"){
										value=$(this).parent().parent().parent().parent().parent().attr("id");
									}
									$("[href='#"+value+"']").removeClass("blue");
									$("[href='#"+value+"']").addClass("red");
								});
								var click=$(".error:first").parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().attr("id");
								
								if(click == undefined || click == "func_fields"){
									click=$(".error:first").parent().parent().parent().parent().parent().parent().parent().attr("id");
								}
								if(click == undefined || click == "ajaxForm"){
									click=$(".error:first").parent().parent().parent().parent().parent().parent().attr("id");
								}
								if(click == undefined || click == "ajaxForm"){
									click=$(".error:first").parent().parent().parent().parent().parent().attr("id");
								}
								$("[href='#"+click+"']").click();
							}
							return false;
						}
						submitForm("ajaxForm",function(responseText){
							$this.reset();
							if($.founded(responseText)){
								if($.founded(responseText.FLG)){
									if(responseText.FLG=="1"){
										$.success(responseText.MSG,function() {
											$.closeModal("addModal");
						    				refershGrid("lsxgjlTabGrid");				
										});
									}else{
										$.alert(responseText.MSG);
									}
								}else if(responseText.indexOf("失败")>-1){
									$.error(responseText,function(){
									});
								}else{
									$.alert(responseText,function(){
									});
								}
							}					
						});
						return false;
					}
				},
				submit : {
					label : "提交申请",
					className : "btn-primary",
					callback : function() {
						var $this = this;
						$("[role='tab']").addClass("blue");
						jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_xgBcXsgrxx.html?sqzt=1");
						if(!$("#ajaxForm").valid()){
							if($('input').hasClass('error')){
								$(".error").each(function(){
									var value=$(this).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().attr("id");
									
									if(value == undefined || value == "func_fields"){
										value=$(this).parent().parent().parent().parent().parent().parent().parent().attr("id");
									}
									if(value == undefined || value == "ajaxForm"){
										value=$(this).parent().parent().parent().parent().parent().parent().attr("id");
									}
									if(value == undefined || value == "ajaxForm"){
										value=$(this).parent().parent().parent().parent().parent().attr("id");
									}
									$("[href='#"+value+"']").removeClass("blue");
									$("[href='#"+value+"']").addClass("red");
								});
								var click=$(".error:first").parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().attr("id");
								
								if(click == undefined || click == "func_fields"){
									click=$(".error:first").parent().parent().parent().parent().parent().parent().parent().attr("id");
								}
								if(click == undefined || click == "ajaxForm"){
									click=$(".error:first").parent().parent().parent().parent().parent().parent().attr("id");
								}
								if(click == undefined || click == "ajaxForm"){
									click=$(".error:first").parent().parent().parent().parent().parent().attr("id");
								}
								$("[href='#"+click+"']").click();
							}
							return false;
						}
						submitForm("ajaxForm",function(responseText){
							$this.reset();
							if($.founded(responseText)){
								if($.founded(responseText.FLG)){
									if(responseText.FLG=="1"){
										$.success(responseText.MSG,function() {
											if($("#dlzt").val() == "yes"){
												window.location.href=_path;
											}
											$.closeModal("addModal");
						    				refershGrid("lsxgjlTabGrid");				
										});
									}else{
										$.alert(responseText.MSG);
									}
								}else if(responseText.indexOf("失败")>-1){
									$.error(responseText,function(){
									});
								}else{
									$.alert(responseText,function(){
									});
								}
							}
						});
						return false;
					}
				},
				cancel : {
					label : "关 闭",
					className : "btn-default"
				}
			}
		});
	});	

	
	//撤销申请
	$("#btn_cxsq").click(function(){
		var keys = $("#lsxgjlTabGrid").getKeys();
		if (keys.length != 1) {
			$.alert('请选定一条记录!');
			return;
		}
		
		var row = $("#lsxgjlTabGrid").jqGrid('getRowData', keys[0]);
		if (row["sqzt"] != "1" ||row["shzt"]!="1"){
			$.alert("当前流程数据非待审核状态，不能撤销！");
			return false;
		}
		var url = _path + '/xsxxxggl/xsgrxxwh_cxTjXsgrxx.html';
		$.confirm('您确定要撤销该申请吗？',function(result){		
			if(result){
				$.ajaxSetup({async:false});
				$.post(url,{"xsxxxgsq_id":row["xsxxxgsq_id"]},function(data){
					$.success(data,function() {
						refershGrid("lsxgjlTabGrid");
					});
				},'json');
				$.ajaxSetup({async:true});
			}		
		});
	});
	

});


 // 查看上传附件
function ckScfj(xsxxxgsq_id){
	$.openWin(_path + "/xsxxxggl/xsxxwh_ckScfj.html?xsxxxgsq_id=" + xsxxxgsq_id);
}

//下载上传附件
function xzScfj(xsxxxgsq_id){
	$.openWin(_path + "/xsxxxggl/xsxxwh_xzScfj.html?xsxxxgsq_id=" + xsxxxgsq_id);
}


//查看，查看学生修改信息
function ckXsXgXxxx(xsxxxgsq_id,xh_id){
	var obj = $("#lsxgjlTabGrid").jqGrid("getRowData", xsxxxgsq_id);
	$.showDialog(_path + '/xsxxxggl/xsxxwh_cxXsxxxgzd.html','查看修改详情',$.extend({},viewConfig,{
		"width":($("#yhgnPage").innerWidth() - 100)+"px",
		"data" :{"xsxxxgsq_id":xsxxxgsq_id,"xh_id":obj.xh_id}
	}));
}

//删除，删除学生修改需审核信息
function scXsXgXxxx(xsxxxgsq_id,xh_id){
	$.confirm('您确定要删除该条记录吗？',function(result){		
		if(result){
			jQuery.ajaxSetup({async:false});
			jQuery.post(_path + "/xsxxxggl/xsgrxxwh_scXsxxXgsq.html?xsxxxgsq_id=" + xsxxxgsq_id,{},function(data){
				setTimeout(function(){
					if (data.indexOf("成功") > 0) {
						$.success(data,function() {
							refershGrid("lsxgjlTabGrid");
						});
					} else if (data.indexOf("失败") > 0) {
						$.error(data);
					} else {
						$.alert(data);
					}
				},1);
			},'json');
			jQuery.ajaxSetup({async:true});
		}		
	});
}

//修改，查看学生修改信息
function xgXsXgXxxx(xsxxxgsq_id,xh_id){
	$.showDialog(_path + '/xsxxxggl/xsgrxxwh_xgXsgrxx.html?xsxxxgsq_id='+ xsxxxgsq_id,'学生个人信息修改',$.extend({},addConfig,{
		width	 : $("#yhgnPage").innerWidth()+"px",
		//是否需要进度条
		"progress"	: true,
		buttons	 : {
			success : {
				label : "保存草稿",
				className : "btn-primary",
				callback : function() {
							jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_cxXsgrxxNum.html");
							submitForm("ajaxForm",function(rst){
								if($.founded(rst)&&!isNaN(rst)){
									if(rst==0){
										$.confirm('操作后无数据变化,删除申请记录,是否继续',function(result){
											if(result){
												jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_xgBcXsgrxxTwo.html?sqzt=0");
												submitForm("ajaxForm",function(responseText){
													if($.founded(responseText)){
														if($.founded(responseText.FLG)){
															if(responseText.FLG=="1"){
																$.success(responseText.MSG,function() {
																	$.closeModal("addModal");
												    				refershGrid("lsxgjlTabGrid");				
																});
															}else{
																$.alert(responseText.MSG);
															}
														}else{
															$.error(responseText);
														}
													}
													
												});
											}
										});
									}else{
										jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_xgBcXsgrxxTwo.html?sqzt=0");
										submitForm("ajaxForm",function(responseText){
											if($.founded(responseText)){
												if($.founded(responseText.FLG)){
													if(responseText.FLG=="1"){
														$.success(responseText.MSG,function() {
															$.closeModal("addModal");
										    				refershGrid("lsxgjlTabGrid");				
														});
													}else{
														$.alert(responseText.MSG);
													}
												}else{
													$.error(responseText);
												}
											}
										});
							        }
						        }
					        });
						return false;
						}
					},
			submit : {
				label : "提交申请",
				className : "btn-primary",
				callback : function() {
						jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_cxXsgrxxNum.html");
						submitForm("ajaxForm",function(rst){
							if($.founded(rst)&&!isNaN(rst)){
								if(rst==0){
									$.confirm('操作后无数据变化,删除申请记录,是否继续',function(result){
										if(result){
											jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_xgBcXsgrxxTwo.html?sqzt=1");
											submitForm("ajaxForm",function(responseText){
												if($.founded(responseText)){
													if($.founded(responseText.FLG)){
														if(responseText.FLG=="1"){
															$.success(responseText.MSG,function() {
																$.closeModal("addModal");
											    				refershGrid("lsxgjlTabGrid");				
															});
														}else{
															$.alert(responseText.MSG);
														}
													}else{
														$.error(responseText);
													}
												}
												
											});
										}
									});
								}else{
									jQuery("#ajaxForm").attr("action",_path + "/xsxxxggl/xsgrxxwh_xgBcXsgrxxTwo.html?sqzt=1");
									submitForm("ajaxForm",function(responseText){
										if($.founded(responseText)){
											if($.founded(responseText.FLG)){
												if(responseText.FLG=="1"){
													$.success(responseText.MSG,function() {
														$.closeModal("addModal");
									    				refershGrid("lsxgjlTabGrid");				
													});
												}else{
													$.alert(responseText.MSG);
												}
											}else{
												$.error(responseText);
											}
										}
									});
						        }
					        }
				        });
					return false;
					
				}
			},
			cancel : {
				label : "关 闭",
				className : "btn-default"
			}
		}
	}));
	
} 
//判断是否存在时盒信息（当前学年学期以后的本学生的信息）
function getShxxCount() {
	var shCount = 0;
	$.ajax({
		url:_path+"/xsxxxggl/xsgrxxwh_cxShxxCount.html",
		async: false,
		type:"post",
		datatype:"json",				
		success:function(data){
			shCount=data*1;	
		}
	});
	return shCount;
}