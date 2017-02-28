$('#table').bootstrapTable({
	url: 					'sysMgmt/sysData', 		//请求后台的URL（*）
	method: 				'get', 					//请求方式（*）
	toolbar: 				'#toolbar', 			//工具按钮用哪个容器
	striped: 				false, 					//是否显示行间隔色
	cache: 					false, 					//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination: 			true, 					//是否显示分页（*）
	sortable: 				false, 					//是否启用排序
//	sortOrder: 				"asc", 					//排序方式
//	queryParams: 			oTableInit.queryParams, //传递参数（*）	
	sidePagination: 		"client",   			//分页方式：client客户端分页，server服务端分页（*）
	pageNumber: 			1, 						//初始化加载第一页，默认第一页
//	pageSize: 				5, 						//每页的记录行数（*）
	pageList: 				[10, 25, 50, 100], 		//可供选择的每页的行数（*）
	search: 				true, 					//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	showColumns: 			false, 					//是否显示所有的列
	showRefresh: 			false, 					//是否显示刷新按钮
	minimumCountColumns: 	2, 						//最少允许的列数
	clickToSelect: 			false, 					//是否启用点击选中行
//	height: 				400,      				//行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
	uniqueId: 				"ID", 					//每一行的唯一标识，一般为主键列
	showToggle: 			false, 					//是否显示详细视图和列表视图的切换按钮
	cardView: 				false, 					//是否显示详细视图
	detailView: 			false,     				//是否显示父子表

	columns: [{
		checkbox: true
	}, {
		field: 'domain_id',
		title: '域编码'
	}, {
		field: 'domain_name',
		title: '域 名'
	}, {
		field: 'sort_id',
		title: '排 序'
	}, {
		field: 'uuid',
		title: '操 作',
		formatter: function(value, row, index) {
			var e = '<a href="#" class="btn btn-info update" onclick="onEdit(\''+ row.uuid +'\',\''+ row.domain_id +'\',\''+ row.domain_name +'\',\''+ row.sort_id +'\',\''+ row.memo +'\')">编辑</a> ';
			var d = '<a href="#" class="btn btn-danger delete" onclick="onDel(\''+ row.uuid +'\')">删除</a> ';
			var f = '<a href="orgMgmt" class="btn btn-success">机构</a> ';
			return e + d + f;
		}
	}, ]
	
});

//批量删除
$('#btn_del').on('click', function(){
	var selectContent = $('#table').bootstrapTable('getSelections');
	var len = selectContent.length;//获取对象数组的长度
	var arr = new Array();//初始化数组
	var i = 0;//初始化数组下标
	var sendData = '';//初始化发送数据
	var sep = ",";
	$.each(selectContent, function(index, data){//遍历对象数组
		//遍历对象,拼接数据
		$.each(data, function(key, value){
			if(key === "uuid"){
				sendData += value;
			}
		})
		if(index < len-1){
			sendData += sep;
		}else{
			sendData = sendData;
		}
		i++;
	})
	if(sendData == ''){
		alert('请选择一条数据');
	}else{
//		alert(sendData);
		$.ajax({
	        type: "POST",
	        url: "sysMgmt/deleteMore",
	        cache : false,
	        data: {"uuid":sendData},
	        dataType: 'json',
	        success: function (message) {
	            if (message > 0) {
	                alert("删除成功！");
	                window.location.href = "sysMgmt";
	            }
	        },
	        error: function (message) {
	           alert("提交数据失败！");
	        }
	    });
	}	
})

//layer弹出自定义div__新增
$('#sys_add').on('click', function() {
	var $ipt_uuid = $("#sys_add_div #form #uuid").val('');
	var $ipt_code = $("#sys_add_div #form #ipt_code").val('');
	var $ipt_name = $("#sys_add_div #form #ipt_name").val('');
	var $ipt_sort = $("#sys_add_div #form #ipt_sort").val('');
	var $ipt_memo = $("#sys_add_div #form #ipt_memo").val('');
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['768px', '432px'],
	});
	return false;
});

//layer弹出自定义div__修改
function onEdit(id,code,name,sort,memo) {
//	alert(id + ' ' + code + ' ' + name + ' ' + sort);
	var $ipt_uuid = $("#sys_add_div #form #uuid").val(id);
	var $ipt_code = $("#sys_add_div #form #ipt_code").val(code);
	var $ipt_name = $("#sys_add_div #form #ipt_name").val(name);
	var $ipt_sort = $("#sys_add_div #form #ipt_sort").val(sort);
	var $ipt_memo = $("#sys_add_div #form #ipt_memo").val(memo);
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['768px', '432px'],
	});
};

//删除
function onDel(id) {
	var $ipt_uuid = $("#sys_del_div #del_form #del_uuid").val(id);
	layer.open({
		type: 1,
		content: $('#sys_del_div'),
		title: '系统提示',
		area: ['300px', '100px'],
	});
};
$('#btn_beSure').click(function() {
	$('#del_form').attr("action", "sysMgmt/delete");
	$('#del_form').submit(function(){
		$(this).ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "false"){
				alert('删除失败');
				return;
			}else{
				window.location.href='sysMgmt';
			}
		});
		return false;
	});
	
});

//表单验证
angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){};
})

//"#sub"是隐藏在div中的表单的"保存"按钮
$('#sub').click(function(){
	//新增操作
	if($("#sys_add_div #form #uuid").val() == ''){
		$("#form").attr("action", "sysMgmt/save");
//		$('#form').submit(function(){
			$('#form').ajaxSubmit(function(resultJson){
				//回调操作
				if(JSON.stringify(resultJson) == "false"){
					layer.open({
						type: 1,
						content: '域编码/域名重复，新增失败!',
						title: '新增失败',
						area: ['200px', '200px'],
					});
				}else{
					window.location.href='sysMgmt';
				}
			});
			return false;//阻止表单默认提交
//		});
	}
	
	//修改操作
	if($("#sys_add_div #form #uuid").val() != ''){
		$("#form").attr("action", "sysMgmt/update");
		$('#form').submit(function(){
			$(this).ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "true"){
					window.location.href='sysMgmt';
				}else{
					alert('修改失败!');
				}
			});
			return false;//阻止表单默认提交
		});
	}
	
	
});
