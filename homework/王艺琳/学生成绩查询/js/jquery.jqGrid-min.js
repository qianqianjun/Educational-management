/*
 * @discretion	: 基于jquery.jqGrid组件的插件.
 * @author    	: wandalong 
 * @version		: v1.0.2
 * @email     	: hnxyhcwdl1003@163.com
 */
;(function($){
	
	$.loadColModel = function(options){
		/*在这里远程加载相应角色的当前grid的列信息；进行组合合并*/
	    if($.founded(options.remoteURL)){
	    	$.ajax({
				datatype 	:"json",
				type 		: "POST",
				url 		: options.remoteURL,
				data 		: options.remoteParams||{},
				async 		: false,
				timeout 	: 6E4,
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				success 	: function(data) {
					/*{label:'角色代码',name : 'jsdm', index : 'jsdm',hidden:true,sortAttr:"1"}
					判断相同index的进行合并
					最后进行结果排序
					*/
					if($.founded(data)){
						var colModels = [];
						/*对在排序列表中的列进行排序*/
						$.each(data||[],function(index,colRow){
							var isSet = false;
							$.each(options.colModel,function(index,colModel){
								if(colRow.index == colModel.index){
									colModels.push($.extend(true,{},colModel,colRow));
									isSet = true;
									return false;
								}
							});
						});
						
						/*把不在排序列表中的列加到colModels中*/
						$.each(options.colModel,function(index,colModel){
							var isSet = false;
							$.each(data||[],function(index,colRow){
								if(colRow.index == colModel.index){
									isSet = true;
									return false;
								}
							});
							if(isSet == false){
								colModels.push(colModel);
							}
						});
						
						/*colModels.sort(function(a,b){
							return parseInt(a.zd_number||"-1") > parseInt(b.zd_number||"-1");
						});*/
						options.initialization = true;
						delete options.colModel;
						options.colModel = colModels;
					}else{
						options.initialization = false;
					}
				}
			});
	    }
	};
	
	$.bootui = $.bootui || {};
	$.bootui.widget = $.bootui.widget || {};
	
	/*====================== JQGridWrap CLASS DEFINITION ====================== */
	
	$.bootui.widget.JQGrid = function(element,options){
		options.beforeRender.call(this,element);	//渲染前的函数回调
		try {
			this.initialize.call(this, element, options);
		} catch (e) {
			options.errorRender.call(this,e);
		}
		options.afterRender.call(this,element);	/*渲染后的函数回调*/
	};
	
	$.bootui.widget.JQGrid.prototype = {
		constructor: $.bootui.widget.JQGrid,
		/*初始化组件参数*/
		initialize 	: function(element, options) {
		
			//加载远程设置数据
			$.loadColModel(options);
			/*加载jqGrid*/
			//$(options.selector).loadJqGrid(options);
			options = jQuery(options.selector).getJqGridOptions(options,options.pager);
			jQuery(options.selector).jqGrid(options).navGrid(options.pager||"#no_pager", {
				edit: false,
				editicon : 'icon-pencil blue',
				add: false,
				addicon : 'icon-plus-sign purple',
				del: false,
				delicon : 'icon-trash red',
				search: false,
				searchicon : 'icon-search orange',
				refresh: false,
				refreshicon : 'icon-refresh green',
				view: false,
				viewicon : 'icon-zoom-in grey'
			}, {}, {}, {}, {
				multipleSearch : true
			});
			/*添加按钮*/
			if(options.customColumn){
				var buttonOptions = {
					pager:options.pager,
					id:"colChoice",/*string类型，按钮id*/ 
					caption:" ", /*按钮名称，可以为空，string类型 */
					buttonicon:"glyphicon glyphicon-cog", /*按钮的图标，string类型，必须为UI theme图标 */
					position: "first", /*first或者last，按钮位置 */
					title:"设置显示列", /*string类型，按钮的提示信息*/
					cursor: "pointer"/*string类型，光标类型，默认为pointer */
				};
				$.extend(buttonOptions,{
					onClickButton:function(){
						var paramMap = {
							"initialization":options.initialization||false,
							"colModel":options.colModel||{},
							"params":options.remoteParams||{}
						};
						options.onSettingClick.call(element,paramMap,function(){
							//加载远程设置数据
							$.loadColModel(options);
							$.extend(options,{
								 postData : $.isFunction(options.remoteMap) ? options.remoteMap.call(this) : {},
							     page	  : 1
							})
							$(options.selector).reloadJqGrid(options);
							$(options.selector).setButton(buttonOptions);
						});
					}
				});
				$(options.selector).setButton(buttonOptions);
			}
			if(!options.initialization){
				var tipHtml = '<div class="alert alert-success alert-dismissible ui-jqgrid-tips" role="alert">' +
								  '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
								  '<strong>提示：</strong> 当前功能可自定义字段显示顺序等,参见列表左下角第一个按钮!' +
							'</div>';
						  
				
				$("#gbox_"+options.selector.substring(1)).before(tipHtml);
			} 
		
		},
		setDefaults	: function(settings){
			$.extend($.fn.JQGrid.defaults, settings );
		}
	}
	
	/* JQGrid PLUGIN DEFINITION  */
	
	$.fn.jqGridWrap = function(option){
		var selector = $(this).selector;
		//处理后的参数
		var args = $.grep( arguments || [], function(n,i){
			return i >= 1;
		});
		return this.each(function () {
			var $this = $(this), data = $this.data('bootui.jqgrid');
			var options = $.extend({}, $.fn.jqGridWrap.defaults, $this.data(),  ((typeof option == 'object' && option) ? option : {}),{"selector":selector});
			if (!data){
				 $this.data('bootui.jqgrid', (data = new $.bootui.widget.JQGrid(this, options)));
			}
			if (typeof option == 'string'){
				//调用函数
				data[option].apply(data, [].concat(args || []) );
			}
		});
	};
	
	$.fn.jqGridWrap.defaults = $.extend({},BaseJqGrid||{},{
		/*版本号*/
		version:'1.0.2',
		/*组件进行渲染前的回调函数：如重新加载远程数据并合并到本地数据*/
		beforeRender: $.noop,
		/*组件渲染出错后的回调函数*/
		errorRender: $.noop,
		/*组件渲染完成后的回调函数*/
		afterRender: $.noop, 
		/*字段配置信息请求参数*/
		remoteURL:_path+"/xtgl/zdpz_cxZdpzList.html",	/*服务端数据请求地址*/
		remoteParams:{},	/*服务端数据请求参数*/
		remoteMap	:$.noop,/*回调函数重新查询grid时候的数据*/
		/*是否启用自定义列*/
		customColumn:true,
		tips		:"当前功能可自定义字段显示顺序等,参见列表左下角第一个按钮!",
		/*===================设置按钮被点击时的回调函数================*/
		onSettingClick:function(paramMap,callbackFunc){
			$.showDialog(_path+"/xtgl/zdpz_cxZdpz.html","设置列信息",{
				"width":"800px",
				"data":paramMap,
				"modalName":"zdpzModal",
				"buttons":{
					success : {
						label : "确  定",
						className : "btn-primary",
						callback : function() {
							var $this = this;
							/*获得选择数据*/
							var result = $this.content.saveZdpz(function(){
								callbackFunc.call($this);
								$this.close();
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
		}
	});

	$.fn.jqGridWrap.Constructor = $.bootui.widget.JQGrid;
	

}(jQuery));