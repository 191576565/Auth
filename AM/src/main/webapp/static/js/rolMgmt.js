$('#table').bootstrapTable({
    url: 'static/js/demo/4.json',
// 	toolbar: '#toolbar', //工具按钮用哪个容器
 	striped: true, //是否显示行间隔色
 	pagination: true, //是否显示分页（*）
 	sortable: false, //是否启用排序
 	sortOrder: "asc", //排序方式
 	pageNumber:1, //初始化加载第一页，默认第一页
 	pageSize: 5, //每页的记录行数（*）
 	pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
// 	strictSearch: true,
// 	showColumns: true, //是否显示所有的列
// 	showRefresh: true, //是否显示刷新按钮
   	clickToSelect: true, //是否启用点击选中行
 	uniqueId: "ID", //每一行的唯一标识，一般为主键列
// 	showToggle:true, //是否显示详细视图和列表视图的切换按钮
// 	cardView: false, //是否显示详细视图

    columns: [{
        field: 'id',
        title: '域 ID',
        checkbox: true
    }, {
        field: 'sysName',
        title: '域名称'
    }, {
        field: 'roleId',
        title: '角色ID'
    }, {
        field: 'name',
        title: '角色名称'
    }, {
    	field: 'opt',
    	title: '操 作',
    	formatter:function(value,row,index){
    	var e = '<a href="#" class="btn btn-info update edit">编辑</a> ';
    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
    	var f = '<a href="funList" class="btn btn-primary create" onclick="del(\''+ row.id +'\')">功能</a> ';
    	return e+d+f;
    	}
    },]
});

//yeqc
//layer弹出自定义div
$('#sys_add').on('click', function(){
	layer.open({
		type:1,
		content: $('#sys_add_div'),
//		skin: 'layui-layer-molv',
		title:'角色信息',
		area: ['640px', '360px'],
//		maxmin: true	
	});
});

$('#table').on('click', '.edit', function() {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
//		skin: 'layui-layer-molv',
		title: '角色信息',
		area: ['640px', '360px']
	});
	return false;
});

angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){
		if($scope.signUpForm.$invalid)
			alert('请检查您的信息!');
		else{
			console.log($scope.userdata);
			window.location.href='rolMgmt';
//			alert('提交成功!');
		}
	}
})