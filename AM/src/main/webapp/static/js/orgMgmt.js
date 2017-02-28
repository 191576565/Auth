$('#table').bootstrapTable({
    url: 					'orgMgmt/orgData',
    method: 				'get', 					//请求方式（*）
	toolbar: 				'#toolbar', 			//工具按钮用哪个容器
	striped: 				true, 					//是否显示行间隔色
	cache: 					false, 					//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination: 			false, 					//是否显示分页（*）
	sortable: 				false, 					//是否启用排序
	sidePagination: 		"client",   			//分页方式：client客户端分页，server服务端分页（*）
	search: 				false, 					//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	showColumns: 			false, 					//是否显示所有的列
	showRefresh: 			false, 					//是否显示刷新按钮
	minimumCountColumns: 	2, 						//最少允许的列数
	clickToSelect: 			false, 					//是否启用点击选中行
	showToggle: 			false, 					//是否显示详细视图和列表视图的切换按钮
	cardView: 				false, 					//是否显示详细视图
	detailView: 			true,     				//是否显示父子表

    columns: [{
    	field: 'domain_name',
    	title: '域',
    },{
        field: 'org_unit_id',
        title: '机构ID',
    }, {
        field: 'org_unit_desc',
        title: '机构描述'	,
    }, {
        field: 'up_org_unit_desc',
        title: '上级机构',
    }, 
    {
    	field: 'opt',
    	title: '操 作',
    	formatter:function(value,row,index){
    	var e = '<a href="#" class="btn btn-info update" onclick="edit(\''+ row.id + '\')">编辑</a> ';
    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
    	return e+d;
    	}
    },],
    //注册加载子表的事件。注意下面的三个参数!
    onExpandRow: function (index, row, $detail) {
    	InitSubTable(index, row, $detail);
    }
});

InitSubTable = function (index, row, $detail) {
	var cur_table = $detail.html('<table></table>').find('table');
	$(cur_table).bootstrapTable({
		url: 'orgMgmt/subData',
		method: 'get',
		detailView: true,
		striped: true,
		columns: [{
	    	field: 'domain_uuid',
	    },{
	        field: 'org_unit_id',
	    }, {
	        field: 'org_unit_desc',
	    }, {
	        field: 'org_up_uuid',
	    }, {
	        field: 'is_enable',
	    },
	    {
	    	field: 'opt',
	    	formatter:function(value,row,index){
	    	var e = '<a href="#" class="btn btn-info update" onclick="edit(\''+ row.id + '\')">编辑</a> ';
	    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
	    	return e+d;
	    	}
	    },],
        onExpandRow: function (index, row, $Subdetail) {
            InitSubTable2(index, row, $Subdetail);
        }
	});
}

InitSubTable2 = function (index, row, $detail) {
	var cur_table = $detail.html('<table></table>').find('table');
	$(cur_table).bootstrapTable({
		url: 'orgMgmt/subData2?up_uuid='+row.uuid,
		method: 'get',
		detailView: true,
		striped: true,
		columns: [{
	    	field: 'domain_uuid',
	    },{
	        field: 'org_unit_id',
	    }, {
	        field: 'org_unit_desc',
	    }, {
	        field: 'org_up_uuid',
	    }, {
	        field: 'is_enable',
	    },
	    {
	    	field: 'opt',
	    	formatter:function(value,row,index){
	    	var e = '<a href="#" class="btn btn-info update" onclick="edit(\''+ row.id + '\')">编辑</a> ';
	    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
	    	return e+d;
	    	}
	    },],
	    //无线循环取子表，直到子表里面没有记录
        onExpandRow: function (index, row, $Subdetail) {
            InitSubTable2(index, row, $Subdetail);
        }
	});
}