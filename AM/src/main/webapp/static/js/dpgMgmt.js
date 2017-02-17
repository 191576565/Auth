$('#table').bootstrapTable({
    url: 'js/demo/8.json',
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
        field: 'sys_name',
        title: '域名称'
    },{
        field: 'gp_code',
        title: '组编码'
    }, {
        field: 'gp_name',
        title: '组名称'
    }, {
        field: 'bl_user',
        title: '所属用户'
    }, {
    	field: 'opt',
    	title: '操 作',
    	formatter:function(value,row,index){
    		var e = '<a href="#" class="btn btn-info update edit">编辑</a> ';
    		var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
    		var f = '<a href="#" class="btn btn-primary create">数据权限</a> ';
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
		title: '权限组信息',
		area: ['400px', '300px'],
		//		maxmin: true	
	});
});

$('#table').on('click', '.edit', function() {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: '权限组信息',
		area: ['500px', '300px']
	});
	return false;
});

$('#sys_add_div #form').on('click', '#tree', function() {
	$.getJSON("js/demo/8_tree.json",function(result){
		$('#treeview12').treeview({
        	data: result
    	});
	});
	layer.open({
		type: 1,
		content: $('#opn_tree'),
		title: '权限组信息',
		area: ['300px', '600px']
	});
	return false;
});




	
