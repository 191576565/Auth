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
	search: 				false, 					//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
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
			var e = '<a href="#" class="btn btn-info update" onclick="onEdit(\''+ row.uuid +'\',\''+ row.domain_id +'\',\''+ row.domain_name +'\',\''+ row.sort_id +'\')">编辑</a> ';
			var d = '<a href="#" class="btn btn-danger delete">删除</a> ';
			var f = '<a href="orgMgmt" class="btn btn-success">机构</a> ';
			return e + d + f;
		}
	}, ]
});

//layer弹出自定义div
$('#sys_add').on('click', function() {
	var $ipt_code = $("#sys_add_div #form #ipt_code").val('');
	var $ipt_name = $("#sys_add_div #form #ipt_name").val('');
	var $ipt_sort = $("#sys_add_div #form #ipt_sort").val('');
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['640px', '360px'],
	});
});

function onEdit(id,code,name,sort) {
//	alert(id + ' ' + code + ' ' + name + ' ' + sort);
	var $ipt_code = $("#sys_add_div #form #ipt_code").val(code);
	var $ipt_name = $("#sys_add_div #form #ipt_name").val(name);
	var $ipt_sort = $("#sys_add_div #form #ipt_sort").val(sort);
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['640px', '360px'],
	});
};

angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){
		if($scope.signUpForm.$invalid)
			alert('请检查您的信息!');
		else{
			console.log($scope.userdata);
			window.location.href='sysMgmt';
//			alert('提交成功!');
		}
	}
})
