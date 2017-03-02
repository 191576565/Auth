$('#table')
		.bootstrapTable(
				{
					url : 'resMgmt/get',
					toolbar : '#toolbar', // 工具按钮用哪个容器
					striped : true, // 是否显示行间隔色
					pagination : true, // 是否显示分页（*）
					sortable : false, // 是否启用排序
					sortOrder : "asc", // 排序方式
					pageNumber : 1, // 初始化加载第一页，默认第一页
					pageSize : 10, // 每页的记录行数（*）
					pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
					strictSearch : true,
					showColumns : false, // 是否显示所有的列
					showRefresh : false, // 是否显示刷新按钮
					clickToSelect : false, // 是否启用点击选中行
					uniqueId : "ID", // 每一行的唯一标识，一般为主键列
					showToggle : false, // 是否显示详细视图和列表视图的切换按钮
					cardView : false, // 是否显示详细视图

					columns : [
							{								
								checkbox : true
							},
							{
								field : 'res_id',
								title : '资源编码'
							},
							{
								field : 'res_name',
								title : '资源名称'
							},
							{
								field : 'res_up_uuid',
								title : '上级资源'
							},
							{
								field : 'res_url',
								title : '资源URL'
							},
							{
								field : 'res_type',
								title : '资源类型'
							},
							{
								field : 'res_class',
								title : '资源CSS'
							},
							{
								field : 'res_color',
								title : '资源颜色'
							},
							{
								field : 'res_icon',
								title : '资源ICON'
							},
							{
								field : 'sort_id',
								title : '排序编号'
							},
							{
								field : 'opt',
								title : '操 作',
								events : 'operateEvents',
								formatter : function(value, row, index) {
									var e = '<a href="#"  class="btn btn-info edit">编辑</a> ';
									var d = '<a href="#"  class="btn btn-danger delete">删除</a> ';
									var f = '<a href="2.html" onclick="del(\''
											+ row.id + '\')">角色列表</a> ';
									return e + d;
								}
							}, ]
				});

$('#res_add').on('click', function() {
	layer.open({
		type : 1,
		content : $('#sys_add_div'),
		// skin: 'layui-layer-molv',
		title : '资源信息-新增',
		area : [ '640px', '360px' ],
	// maxmin: true
	});
});

window.operateEvents = {
	'click .edit' : function(e, value, row, index) {
		// 修改操作
		var $uuid = $("#sys_add_div #form #uuid").val(row.uuid);
		var $res_id = $("#sys_add_div #form #res_id").val(row.res_id);
		var $res_name = $("#sys_add_div #form #res_name").val(row.res_name);

		var $res_up_uuid = $("#sys_add_div #form #res_up_uuid").val(row.res_up_uuid);
		var $res_url = $("#sys_add_div #form #res_url").val(row.res_url);
		var $res_class = $("#sys_add_div #form #res_class").val(row.res_class);
		var $res_icon = $("#sys_add_div #form #res_icon").val(row.res_icon);
		var $sort_id = $("#sys_add_div #form #sort_id").val(row.sort_id);
		console.log(row.res_name);

		layer.open({
			type : 1,
			content : $('#sys_add_div'),
			title : '资源信息-编辑',
			area : [ '640px', '360px' ],
		});
	},
	'click .delete' : function(e, value, row, index) {
		// 删除操作
		console.log(row.uuid);
		var $deluuid = $("#sys_del_div #del_form #deluuid").val(row.uuid);
		layer.open({
			type : 1,
			content : $('#sys_del_div'),
			title : '资源信息-删除',
			area : [ '640px', '360px' ],
		});
	}
}

$('#resdelete').click(function() {
	
		$("#del_form").attr("action", "resMgmt/delete");
		$('#del_form').submit(function() {
			$(this).ajaxSubmit(function(resultJson) {
				// 回调操作
				if (JSON.stringify(resultJson) == "false") {
					console.log("resultJson==false")
					layer.open({
						type : 1,
						content : '删除失败!',
						title : '删除失败',
						area : [ '200px', '200px' ],
					});
					return false;
				} else {
					console.log("resultJson==true")
					window.location.href = 'resMgmt';
				}
			});

			return false;// 阻止表单默认提交
		});
		
});

$('#resave').click(function() {
	// 新增操作
	if($("#sys_add_div #form #uuid").val() == ''){
	$("#form").attr("action", "resMgmt/save");
	$('#form').submit(function() {
		$(this).ajaxSubmit(function(resultJson) {

			// 回调操作
			if (JSON.stringify(resultJson) == "false") {
				console.log("resultJson==false")
				layer.open({
					type : 1,
					content : '角色编码/角色名重复，新增失败!',
					title : '新增失败',
					area : [ '200px', '200px' ],
				});
				return false;
			} else {
				console.log("resultJson==true")
				window.location.href = 'resMgmt';
			}
		});

		return false;// 阻止表单默认提交
	});
	}
	//修改操作
	if($("#sys_add_div #form #uuid").val() != ''){
		$("#form").attr("action", "resMgmt/put");
		$('#form').submit(function(){
			$(this).ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "true"){
					window.location.href='resMgmt';				
				}else{
					layer.open({
						type : 1,
						content : '资源信息-编辑',
						title : '编辑失败',
						area : [ '200px', '200px' ],
					});
				}
			});
			return false;//阻止表单默认提交
		});
	}
});