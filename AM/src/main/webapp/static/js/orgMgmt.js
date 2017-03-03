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
    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.uuid +'\')">删除</a> ';
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
	    	field: 'domain_name',
	    },{
	        field: 'org_unit_id',
	    }, {
	        field: 'org_unit_desc',
	    }, {
	        field: 'up_org_unit_desc',
	    }, 
	    {
	    	field: 'opt',
	    	formatter:function(value,row,index){
	    	var e = '<a href="#" class="btn btn-info update" onclick="edit(\''+ row.id + '\')">编辑</a> ';
	    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.uuid +'\')">删除</a> ';
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
	    	field: 'domain_name',
	    },{
	        field: 'org_unit_id',
	    }, {
	        field: 'org_unit_desc',
	    }, {
	        field: 'up_org_unit_desc',
	    }, 
	    {
	    	field: 'opt',
	    	formatter:function(value,row,index){
	    	var e = '<a href="#" class="btn btn-info update" onclick="edit(\''+ row.id + '\')">编辑</a> ';
	    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.uuid +'\')">删除</a> ';
	    	return e+d;
	    	}
	    },],
	    //无线循环取子表，直到子表里面没有记录
        onExpandRow: function (index, row, $Subdetail) {
            InitSubTable2(index, row, $Subdetail);
        }
	});
}

//表单验证
angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){};
})

//新增机构_弹出层
$('#org_add').on('click', function() {
	//初始化表单
	
	//获取域
	$.getJSON("orgMgmt/getId",function(data){
		console.log(data);
		$.each(data, function(i, item){
			$.each(item, function(key,value){
				if(key === "domain_name"){
					var $scop_n = $("#org_add_div #form #scop_n").val(value);
				}
				if(key === "uuid"){
					var $uuid = $("#org_add_div #form #uuid").val(value);
				}
			})
		})
	})
	//获取机构
	$.getJSON("orgMgmt/getOrg",function(data){
		console.log(data);
		$.each(data, function(i, item){
			var up_id = '';
			$.each(item, function(key,value){
				if(key === "uuid"){
					up_id = value;
				}
			})
			$.each(item, function(key,value){
				if(key === "org_unit_desc"){
					$('#up_org').append("<option value="+up_id+">" + value + "</option>");
				}
			})
		})
	})
	layer.open({
		type: 1,
		content: $('#org_add_div'),
		title: '机构信息',
		area: ['960px', '540px'],
	});
	return false;
});
//新增机构_保存
$('#sub').click(function(){
	$("#form").attr("action", "orgMgmt/save");
	$('#form').ajaxSubmit(function(resultJson){
		window.location.href='orgMgmt';
	});
	return false;//阻止表单默认提交
});

//删除
function del(id) {
	var $ipt_uuid = $("#sys_del_div #del_form #del_uuid").val(id);
	layer.open({
		type: 1,
		content: $('#sys_del_div'),
		title: '系统提示',
		area: ['300px', '100px'],
	});
};
$('#btn_beSure').click(function() {
	$('#del_form').attr("action", "orgMgmt/delete");
//	$('#del_form').submit(function(){
		$('#del_form').ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "false"){
				alert('删除失败');
				return;
			}else{
				window.location.href='orgMgmt';
			}
		});
		return false;
//	});
	
});