$('#table').bootstrapTable({
    url: 'rolMgmt/showRol',
// 	toolbar: '#toolbar', //工具按钮用哪个容器
	method: 'get', 					//请求方式（*）    
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
        checkbox: true
    }, {
        field: 'domain_name',
        title: '域名称'
    }, {
        field: 'role_id',
        title: '角色ID'
    }, {
        field: 'role_name',
        title: '角色名称'
    }, {
    	field: 'uuid',
    	title: '操 作',
    	formatter:function(value,row,index){
    	var e = '<a href="#" id="btn_upt" class="btn btn-info update" onclick="onEdit(\''+ row.uuid +'\',\''+ row.role_id +'\',\''+ row.role_name +'\',\''+ row.domain_uuid +'\')">编辑</a>';
    	var d = '<a href="#" class="btn btn-danger delete" onclick="onDel(\''+ row.uuid +'\')">删除</a> ';
    	var f = '<a href="funList/showFunList?uuid='+row.uuid+'" class="btn btn-primary create">功能</a> ';
    	return e+d+f;
    	}
    },]
});


//layer弹出自定义div__新增
$('#sys_add').on('click', function() {
	var $role_uuid = $("#sys_add_div #form #uuid").val('');
	var $role_id = $("#sys_add_div #form #role_id").val('');
	var $role_name = $("#sys_add_div #form #role_name").val('');
	var $domain_uuid=$("#sys_add_div #form #domain_uuid").val('');
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['768px', '432px'],
	});
	return false;
});

//layer弹出自定义div__修改
function onEdit(id,roleid,rolename,domainuuid) {
	//alert(id + ' ' + roleid + ' ' + rolename + ' ' + domainuuid);
	var $uuid = $("#sys_add_div #form #uuid").val(id);
	var $role_id = $("#sys_add_div #form #role_id").val(roleid);
	var $role_name = $("#sys_add_div #form #role_name").val(rolename);
	var $domain_uuid = $("#sys_add_div #form #domain_uuid").val(domainuuid);
	
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['768px', '432px'],
	});
};

//删除
function onDel(id) {
	var $role_uuid = $("#sys_del_div #del_form #role_uuid").val(id);
	layer.open({
		type: 1,
		content: $('#sys_del_div'),
		title: '系统提示',
		area: ['300px', '100px'],
	});
};
$('#btn_beSure').click(function() {
	$('#del_form').attr("action", "rolMgmt/delete");
	$('#del_form').submit(function(){
		$(this).ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "false"){
				alert('更新失败');
				return;
			}else{
				window.location.href='rolMgmt';
			}
		});
		return false;
	});
	
});







angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){}
})



$('#sub').click(function(){
	//新增操作
	if($("#sys_add_div #form #uuid").val() == ''){
		$("#form").attr("action", "rolMgmt/save");
		
		$('#form').submit(function(){
			
			$(this).ajaxSubmit(function(resultJson){
			
				//回调操作
				if(JSON.stringify(resultJson) == "false"){
					layer.open({
						type: 1,
						content: '角色编码/角色名重复，新增失败!',
						title: '新增失败',
						area: ['200px', '200px'],
					});
					return;
				}else{
					
					window.location.href='rolMgmt';
				}
			});
					
			return false;//阻止表单默认提交
		});
	}
	
	//修改操作
	if($("#sys_add_div #form #uuid").val() != ''){
		$("#form").attr("action", "rolMgmt/update");
		$('#form').submit(function(){
			$(this).ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "true"){
					window.location.href='rolMgmt';
					//alert('修改成功');
				}else{
					alert('修改失败!');
				}
			});
			return false;//阻止表单默认提交
		});
	}
	
	
});


