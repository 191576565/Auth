var s = {
	view: {
		dblClickExpand: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid"
		}
	},
	callback: {
		onDblClick: zTreeOnDblClickSimple,
		onClick: zTreeOnClickSimple
	},
	check: {
		enable: true,
		chkboxType: {
			"Y": "ps",
			"N": "s"
		}
	}
};
function zTreeOnDblClickSimple(event, treeId, treeNode) {
};

function zTreeOnClickSimple(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandNode(treeNode, null, null, null);

	return true;
};

function inittable(){
	$('#table').bootstrapTable('destroy'); 
	$('#table').bootstrapTable({
	    url: 'rolMgmt/showRol',
		method: 'get',    
	 	striped: false,
	 	pagination: true,
	 	sortable: false,
	 	queryParams: function queryParams() {     //设置查询参数  
	        var param = {    
		        sendParam : $("#iptSearch").val(),
		        send : ''
	        };    
       　　	return param;                   
         },	
	 	pageNumber:1,
	 	pageSize: 10,
	 	search: false,
	 	pageList: [10],
	   	clickToSelect: false,
	
	    columns: [{   
	        checkbox: true
	    }, {
	        field: 'domain_name',
	        title: '域名称',
	        align: 'center'	
	    }, {
	        field: 'role_id',
	        title: '角色编码',
	        align: 'center'	
	    }, {
	        field: 'role_name',
	        title: '角色名称',
	        align: 'center'	
	    }, {
	    	field: 'uuid',
	    	title: '操 作',
	    	align: 'center',
	    	width: '188px',
	    	formatter:function(value,row,index){
		    	var e = '<a href="#" id="btn_upt" class="btn btn-info update" onclick="onEdit(\''+ row.uuid +'\',\''+ row.role_id +'\',\''+ row.role_name +'\',\''+ row.domain_uuid +'\',\''+ row.memo +'\')">编辑</a> ';
		    	var d = '<a href="#" class="btn btn-danger delete" onclick="onDel(\''+ row.uuid +'\',\''+ row.role_id +'\',\''+ row.role_name +'\')">删除</a> ';
		    	var f = '<a href="#" onclick="onFun(\''+ row.uuid +'\')" class="btn btn-success">功能</a> ';
		    	return e+d+f;
	    	}
	    },]
	});
};
$(function(){
	$.get("funList/showFunList",function(data){
		var rs=[];
		data.forEach(function(e){
			e.chkDisabled=e.chkdisabled;
			rs.push(e);
		});
		$.fn.zTree.init($("#res"), s, rs); //树
	});
})

//功能
function onFun(id){
		var treeObj = $.fn.zTree.getZTreeObj("res");
		treeObj.checkAllNodes(false); //取消所有勾选
		treeObj.expandAll(true);
		
		//勾选已经存在的资源
		$.ajax({
	    	async:false,
	    	type:"GET",
	    	url:"funList/showExit?uuid="+id,
	    	success:function(d){
				for(var key in d){
					var node =  treeObj.getNodeByParam("id", key, null);
					treeObj.checkNode(node, true, false);
				}
	    	}
	    });
		
		layer.open({
			type : 1,
			content : $('.t'),
			title : '选择功能',
			area : [ '400px', '600px' ],
			btn: ['保存', '取消'],
			yes: function(index, layero){
				//var treeObj = $.fn.zTree.getZTreeObj("res");
				var nodes = treeObj.getCheckedNodes(true);
				var uuid=[];
				nodes.forEach(function(e){
					uuid.push(e.id);
				});
				$.post("funList/funSave?resources="+uuid.join()+"&uuid="+id, function(d){
					if(d){
						layer.closeAll();
						layer.msg('角色资源赋予成功');
					}else {
						layer.msg('角色资源赋予失败');
					}
				});
			},
			btn2: function(index, layero){
				
		    }
		});
}

//layer弹出自定义div__新增
$('#sys_add').on('click', function() {
	$("#sys_add_div #form")[0].reset();
	$('.error').empty();
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '角色信息',
		area: ['400px', '400px'],
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "rolMgmt/save");
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "false"){
					layer.msg('角色编码/角色名已存在');
					return;
				}else{
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
				}
			});
		},
		btn2: function(index, layero){	
		}
	});
	return false;
});

//layer弹出自定义div__修改
function onEdit(id,roleid,rolename,domainuuid,memo) {
	$("#sys_add_div #form #uuid").val(id);
	$("#sys_add_div #form #role_id").val(roleid);
	$("#sys_add_div #form #role_name").val(rolename);
	$("#sys_add_div #form #domain_uuid").val(domainuuid);
	$("#sys_add_div #form #ipt_memo").val(memo);
	$('.error').empty();
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '角色信息',
		area: ['400px', '400px'],
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "rolMgmt/update");
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "true"){
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
				}else{
					layer.msg('修改失败!');
				}
			});
		},
		btn2: function(index, layero){	
		}
	});
};

//批量删除
$('#btn_del').on('click', function(){
	var selectContent = $('#table').bootstrapTable('getSelections');
	var uuids='',role_id='',role_name='';
	selectContent.forEach(function(e,i){
		//逗号分隔拼接字符串,末尾不加逗号
		if(i == (selectContent.length-1)){
			uuids += (e.uuid);
			role_id += (e.role_id);
			role_name += (e.role_name);
		}else{
			uuids += (e.uuid+',');
			role_id += (e.role_id+',');
			role_name += (e.role_name+',');
		}
	});
	if('' == uuids){
		layer.msg('请选择要删除的角色');
	}else{
		layer.confirm('是否删除选定的角色？', 
				{
			　　　　　　title:'提示信息',
				  btn: ['删除','取消'] //按钮
				}, 
				function(){
					$.post('rolMgmt/deleteMore',{UUID:uuids,role_id:role_id,role_name:role_name}, function(d){
						if(d){
							layer.msg('删除成功');
						}else {
							layer.msg('删除失败，检查是否已关联用户');
						}
						$('#table').bootstrapTable('refresh', {silent: true});
					});
				}, 
				function(){
					layer.closeAll();
				}
		);
	}	
})

//删除
function onDel(id,role_id,role_name) {
	layer.confirm('是否删除该角色信息？', 
			{
		　　　　　　title:'提示信息',
			  btn: ['删除','取消'] //按钮
			}, 
			function(){
				$.post('rolMgmt/delete',{UUID:id,role_id:role_id,role_name:role_name}, function(d){
					if(d){
						layer.msg('删除成功');
					}else {
						layer.msg('删除失败，检查是否已关联用户');
					}
					$('#table').bootstrapTable('refresh', {silent: true});
				});
			}, 
			function(){
				layer.closeAll();
			}
		);
};

function validate(){
	//非空验证
	var flag = true;
	$(".notNull").each(function(){
        if(""==$(this).val()){
        	layer.msg($(this).attr('nullName')+"不能为空");
        	flag = false;
        	return false;
        }
    });
	//合法验证
	$("p.error").each(function(){
		if(""!=$(this).text()){
			flag = false;
		}
	});
	return flag;
}

