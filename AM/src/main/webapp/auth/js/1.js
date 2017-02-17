//$(function () {
// 
// //1.初始化Table
// var oTable = new TableInit();
// oTable.Init();
// 
// //2.初始化Button的点击事件
// var oButtonInit = new ButtonInit();
// oButtonInit.Init();
// 
//});
// 
// 
//var TableInit = function () {
//	var oTableInit = new Object();
// 	//初始化Table
// 	oTableInit.Init = function () {
// 		$('#table').bootstrapTable({
// 			url: 'demo/1.json', //请求后台的URL（*）
//// 			method: 'get', //请求方式（*）
//// 			toolbar: '#toolbar', //工具按钮用哪个容器
//// 			striped: true, //是否显示行间隔色
//// 			cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
//// 			pagination: true, //是否显示分页（*）
//// 			sortable: false, //是否启用排序
//// 			sortOrder: "asc", //排序方式
//// 			queryParams: oTableInit.queryParams,//传递参数（*）
//// 			sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
//// 			pageNumber:1, //初始化加载第一页，默认第一页
//// 			pageSize: 10, //每页的记录行数（*）
//// 			pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
//// 			search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
//// 			strictSearch: true,
//// 			showColumns: true, //是否显示所有的列
//// 			showRefresh: true, //是否显示刷新按钮
//// 			minimumCountColumns: 2, //最少允许的列数
//// 			clickToSelect: true, //是否启用点击选中行
//// 			height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
//// 			uniqueId: "ID", //每一行的唯一标识，一般为主键列
//// 			showToggle:true, //是否显示详细视图和列表视图的切换按钮
//// 			cardView: false, //是否显示详细视图
//// 			detailView: false, //是否显示父子表
// 			columns: [{
// 				checkbox: true
// 				}, {
// 				field: 'Name',
// 				title: '部门名称'
// 				}, {
// 				field: 'ParentName',
// 				title: '上级部门'
// 				}, {
// 				field: 'Level',
// 				title: '部门级别'
// 				}, {
// 				field: 'Desc',
// 				title: '描述'
// 			}, ]
// 		});
// 	};
// 
// 	//得到查询的参数
// 	oTableInit.queryParams = function (params) {
// 		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
// 			limit: params.limit, //页面大小
// 			offset: params.offset, //页码
// 			departmentname: $("#txt_search_departmentname").val(),
// 			statu: $("#txt_search_statu").val()
// 		};
// 		return temp;
// 	};
// 	return oTableInit;
//};
// 
// 
//var ButtonInit = function () {
//	var oInit = new Object();
//	var postdata = {};
// 
//	oInit.Init = function () {
// 	//初始化页面上面的按钮事件
//	};
// 
//	return oInit;
//};

$('#table').bootstrapTable({
    url: 'js/demo/1.json',
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
// 	clickToSelect: true, //是否启用点击选中行
 	uniqueId: "ID", //每一行的唯一标识，一般为主键列
// 	showToggle:true, //是否显示详细视图和列表视图的切换按钮
// 	cardView: false, //是否显示详细视图

    columns: [{
        field: 'id',
        title: '域 ID',
        checkbox: true
    }, {
        field: 'code',
        title: '域编码'
    },{
        field: 'name',
        title: '域 名'
    }, {
        field: 'sort',
        title: '排 序'
    }, {
    	field: 'opt',
    	title: '操 作',
    	formatter:function(value,row,index){
    		var e = '<a href="#" class="btn btn-info update edit">编辑</a> ';
    		var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
    		var f = '<a href="2-org.html" class="btn btn-primary create">机构</a> ';
    		return e+d+f;
    	}
    },]
});

//yeqc
//layer弹出自定义div

$('#sys_add').on('click', function() {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: '系统信息',
		area: ['400px', '300px'],
		//		maxmin: true	
	});
});

$('#table').on('click', '.edit', function() {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: '系统信息',
		area: ['500px', '300px']
	});
	return false;
});


	
