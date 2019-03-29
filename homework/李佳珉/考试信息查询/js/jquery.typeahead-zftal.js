jQuery(function($){
	
	var typeaheadMap = {
		"xs":_path+"/query/query_cxXsxxPairPaged.html",
		"js":_path+"/query/query_cxJsxxPairPaged.html",
		"xy":_path+"/query/query_cxXyxxPairPaged.html",
		"xx":_path+"/query/query_cxXxxPairPaged.html",
		"jg":_path+"/query/query_cxJgxxPairPaged.html",
		"kkbm":_path+"/query/query_cxKkbmPairPaged.html",
		"zy":_path+"/query/query_cxZyxxPairPaged.html",
		"zyfx":_path+"/query/query_cxZyfxPairPaged.html",
		"bj":_path+"/query/query_cxBjxxPairPaged.html",
		"kc":_path+"/query/query_cxKcxxPairPaged.html",
		"pyfan":_path+"/query/query_cxPyfanPairPaged.html"
	};

	/**
	 * 在input 添加属性参数：  
	 * 【必要】
	 * data-toggle="autocomplete" 
	 * data-autocomplete-type="xs" ：根据上面typeaheadMap对象的键而定
	 * 【非必要】
	 * data-autocomplete-setvalue="key或value"
	 * data-autocomplete-realvalue="key或value"
	 * data-autocomplete-action
	 * data-autocomplete-format
	 */
	$(document).off('focus.data-api', '[data-toggle*="autocomplete"]').on('focus.widget.data-api', '[data-toggle*="autocomplete"]', function (event,type) {
		if(event.currentTarget = this){
			
			if(!$.founded(type)){
				type  = $(this).data("autocomplete-type");
			}
			//if(!$.founded(type)){
			//	throw new Error(' 必须传递请求数据类型的参数 ;在元素上添加 data-type="?" 或者 $(element).trigger("focus",[?])  ! ');
			//}
			var auto_minLength = $(this).data("autocomplete-minLength")|| 1;
			var auto_items = $(this).data("autocomplete-items")||10;
			var auto_delay = $(this).data("autocomplete-delay")|| 300;
			var set_value = $(this).data("autocomplete-setvalue")||"value";
			var real_value = $(this).data("autocomplete-realvalue")||"key";
			var format = $(this).data("autocomplete-format")||"value（key）";
			var action = $(this).data("autocomplete-action")||"";
			
			//绑定AutoComplete组件
			$(this).typeahead({
				//当前文本输入框中字符串达到该属性值时才进行匹配处理，默认：1
				minLength	:	auto_minLength,
				//自动完成提示的最大结果集数量
				items		:	auto_items,
				//指定延时毫秒数后，才正真向后台请求数据，以防止输入过快导致频繁向后台请求，默认：300
				delay		:	auto_delay, 
				//query表示当前文本输入框中的字符串，可在该方法中通过ajax向后台请求数据（数组形式的json对象），然后将返回的对象作为process的参数
			   	source		:	function(query,process){
					
			      	var matchCount = this.options.items;
					var requestMap = {
						"queryModel.currentPage": 1,
						"queryModel.showCount"	: matchCount,
						"key"					: query
					};
					if($.founded(type) && $.founded(typeaheadMap[type])){
						//返回结果集最大数量
				      	$.post(typeaheadMap[type],requestMap,function(respData){
			                return process(respData);
			            });
					}else if($.founded(action)){
						//返回结果集最大数量
				      	$.post(action,requestMap,function(respData){
			                return process(respData);
			            });
					}else{
						 process({"key":"无请求URL","value":"无请求URL"});
					}
		        },
				//对返回数据的具体某个json对象转化为字符串格式，用以显示在提示列表中，返回值：字符串
		        formatItem	:	function(item){
		            return format.replace(/value/g, item["value"]).replace(/key/g, item["key"]);
		        },
				//选中提示列表某项 时，设置文本输入框中显示的值以及实际需要获取的值。返回值格式：
				//{'data-value':item["输入框显示值的item属 性"],'real-value':item["实际需要获取值的item属性"]}，后期可通过文本输入框的real-value属性获取该值
		        setValue	:	function(item){
		            return {'data-value':item[set_value],'real-value':item[real_value]};
		        }
			 });
		}
	})
	//邮件自动补全
	.off('focus.data-api', '[data-toggle*="autoEmail"]').on('focus.widget.data-api', '[data-toggle*="autoEmail"]', function (event,type) {
		if(event.currentTarget = this){
			var $this = this;
			var auto_minLength = $(this).data("autocomplete-minLength")|| 1;
			var auto_items = $(this).data("autocomplete-items")|| 25;
			var auto_delay = $(this).data("autocomplete-delay")|| 300;
			//返回结果集最大数量
	      	$.post(_path + "/xtgl/comm_cxEmailPostfixList.html" ,function(data){
				if($.founded(data) ){
					//绑定AutoComplete组件
					$($this).typeahead({
						//当前文本输入框中字符串达到该属性值时才进行匹配处理，默认：1
						minLength	:	auto_minLength,
						//自动完成提示的最大结果集数量
						items		:	auto_items,
						//指定延时毫秒数后，才正真向后台请求数据，以防止输入过快导致频繁向后台请求，默认：300
						delay		:	auto_delay, 
						//query表示当前文本输入框中的字符串，可在该方法中通过ajax向后台请求数据（数组形式的json对象），然后将返回的对象作为process的参数
					   	source		:	function(query,process){
							var dataList = [];
							$.each(data||[],function(i,postfix){
								var tmp =  $($this).val() + "@" + postfix;
								dataList.push({"key":tmp,"value":tmp});
							});
							process(dataList);
				        },
						//对返回数据的具体某个json对象转化为字符串格式，用以显示在提示列表中，返回值：字符串
				        formatItem	:	function(item){
				            return item["value"];
				        },
						//选中提示列表某项 时，设置文本输入框中显示的值以及实际需要获取的值。返回值格式：
						//{'data-value':item["输入框显示值的item属 性"],'real-value':item["实际需要获取值的item属性"]}，后期可通过文本输入框的real-value属性获取该值
				        setValue	:	function(item){
				            return {'data-value':item["key"],'real-value':item["value"]};
				        }
					 });
				}
            });
		}
	});
});