$('#table').bootstrapTable({
    url: 'rolMgmt/showRol',
 	toolbar: '#toolbar',	
	method: 'get',    
 	striped: false,
 	pagination: true,
 	sortable: false,
 	pageNumber:1,
 	pageSize: 10,
 	search: true,
 	pageList: [10, 25, 50, 100],
   	clickToSelect: false,

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
	    	var e = '<a href="#" id="btn_upt" class="btn btn-info update" onclick="onEdit(\''+ row.uuid +'\',\''+ row.role_id +'\',\''+ row.role_name +'\',\''+ row.domain_uuid +'\')">编辑</a> ';
	    	var d = '<a href="#" class="btn btn-danger delete" onclick="onDel(\''+ row.uuid +'\')">删除</a> ';
//	    	var f = '<a href="funList/showFunList?uuid='+row.uuid+'" class="btn btn-success">功能</a> ';
	    	var f = '<a href="#" onclick="onFun(\''+ row.uuid +'\')" class="btn btn-success">功能</a> ';
	    	return e+d+f;
    	}
    },]
});

function onFun(id){
	$.get("funList/showFunList?uuid="+id,function(result){
		layer.open({
			type: 1,
			content: (result),
			title: false,
			maxmin: true, //开启最大化最小化按钮
		});
	});
	
}

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
		$('#form').ajaxSubmit(function(resultJson){
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


