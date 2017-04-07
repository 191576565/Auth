var s = {
	view : {
		dblClickExpand : false,
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pid"
		}
	},
	callback : {
		onClick : zTreeOnClickSimple
	},
	check : {
		enable : true,
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		}
	}
};

function zTreeOnClickSimple(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandNode(treeNode, null, null, null);

	return true;
};

var valid;
$(function() {
	valid=$('#form').validate();
	//初始化表格
	initdpgMgmtlist();
	
	$.get("dpgMgmt/domaininfo", function(data){
		data.forEach(function(e){
			$('#domain_id').append('<option value="'+e.domain_id+'">'+e.domain_name+'</option>');
		});
	})
	
	$('.users').click(function(){
		var domainId=$('#domain_id').val();
		if(domainId==""){
			layer.tips("请先选择所属域！", '#domain_id');
			return true;
		}
		//生成域下的机构用户树
		$.get('dpgMgmt/userTree?domainUuid='+domainId, function(data){
			$.fn.zTree.init($("#tree"), s, data); //树
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			treeObj.expandAll(true);
		
			var u=users.split(',');
			if(users!="" && users!=null){
				u.forEach(function(e){
					var node = treeObj.getNodeByParam("id", e);
					treeObj.checkNode(node, true, true);
				});
			}
			
			layer.open({
				type : 1,
				content : $('.t'),
				title : '选择所属用户',
				area : [ '400px', '600px' ],
				maxmin: true,
				btn: ['确定', '取消'],
				yes: function(index, layero){
					var nodes = treeObj.getCheckedNodes(true);
					var us=[];
					nodes.forEach(function(e){
						if(e.flag==1){
							us.push(e.id);
						}
					});
					users=us.join();
					if(users!=null && users!=''){
						$('.users').text('编辑所属用户');
					}
					layer.close(index);
				},
				btn2: function(index, layero){}
			});
		})
		
		
	})
})

var table;
function initdpgMgmtlist() {
	table=$('#table')
			.bootstrapTable(
					{
						url : 'dpgMgmt/list',
						// toolbar: '#toolbar', //工具按钮用哪个容器
						striped : true, // 是否显示行间隔色
						pagination : true, // 是否显示分页（*）
						sortable : true, // 是否启用排序
						sortOrder : "asc", // 排序方式
						pageNumber : 1, // 初始化加载第一页，默认第一页
						pageSize : 10, // 每页的记录行数（*）
						pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
						search : false,
						showColumns : false, // 是否显示所有的列
						showRefresh : false, // 是否显示刷新按钮
						clickToSelect : false, // 是否启用点击选中行
						uniqueId : "uuid", // 每一行的唯一标识，一般为主键列
						showToggle : false, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						queryParamsType : '',
						sidePagination : "server",
						queryParams : function queryParams(params) { // 设置查询参数
							var param = {
								pageNumber : params.pageNumber,
								pageSize : params.pageSize,
								user_id : $("#userid").val()
							};
							return param;
						},

						columns : [
								{
									checkbox : true
								},
								{
									field : 'domain_name',
									title : '域名称',
								},
								{
									field : 'group_id',
									title : '组编码',
								},
								{
									field : 'group_desc',
									title : '组名称',
								},
								{
									field : 'opt',
									title : '操 作',
									width:250,
									formatter : function(value, row, index) {
										var e = '<a href="#" id="btn_upt" class="btn btn-info update" onclick="onEdit(\''+ index +'\')">编辑</a> ';
								    	var d = '<a href="#" class="btn btn-danger delete" onclick="onDel(\''+ row.uuid +'\')">删除</a> ';
								    	var f = '<a href="#" onclick="onFun(\''+ row.uuid +'\',\''+ row.domain_id +'\')" class="btn btn-success">数据权限</a> ';
								    	return e+d+f;
									}
								}, ]
					});
};

var users='', chk;//保存所属用户
$('#sys_add').click(function(){
	$("#sys_add_div #form")[0].reset();
	$('#group_id').removeAttr('readonly');
	users='', chk=1;;
	$('.users').text('请选择所属用户');
	$('#form p.success').remove();
	
	//去除上次表单验证的样式
	valid.resetForm();
	$('input').removeClass('error');
	$('select').removeClass('error');
	
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '新增权限组信息',
		area: '400px',
		btn: ['保存', '取消'],
		yes: function(index, layero){
			if($('#form').valid()){
				$.ajax({
					url : "dpgMgmt/saveform?"+$('#form').serialize()+"&guserid="+users,
					dataType : "json",
					type : 'post',
					contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
					success : function(data) {
						if (data.status == 'success') {
							layer.msg("数据保存成功");
						} else {
							layer.msg("数据保存失败");
						}
						 refresh();
					},
					error : function(
							XMLHttpRequest,
							textStatus, errorThrown) {
						layer.msg("数据被城管抓走了！");
					}
				});
			}
		},
		btn2: function(index, layero){}
	});
	return false;
});

function onEdit(index){
	chk=0;
	$('#group_id').attr('readonly', 'readonly');
	var info=table.bootstrapTable('getData')[index];
	for(var key in info){
		$('#'+key).val(info[key]);
	}
	
	users=info.users==null?"":info.users;
	if(users=="" || users==null){
		$('.users').text('请选择所属用户');
	}else {
		$('.users').text('编辑所属用户');
	}
	
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '编辑权限组信息',
		area: '400px',
		btn: ['保存', '取消'],
		yes: function(index, layero){
			if($('#form').valid()){
				$.ajax({
					 url: "dpgMgmt/updateform?"+$('#form').serialize()+"&guserid="+users,
					 dataType: "json",
					 type:'post',
					 contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					 success: function (data) {
						 if(data.status=='success'){
							 layer.msg("数据保存成功");
						 }else{
							 layer.msg("数据保存失败");
						 }
						 refresh();
					 },
					 error: function (XMLHttpRequest, textStatus, errorThrown) {
						 layer.msg("数据被城管抓走了！");
					 }
				});
			}
		},
		btn2: function(index, layero){}
	});
}

function onDel(uuid){
	layer.confirm('是否删除该权限组？', {
		  btn: ['删除','取消'] //按钮
		}, function(){
			$.ajax({
				 url: "dpgMgmt/delform",
				 dataType: "json",
				 data:{'uuid':uuid},
				 success: function (data) {
					 if(data.status=='success'){
						 layer.msg("删除成功");
					 }else{
						 layer.msg("删除失败！");
					 }
					 refresh();
				 },
				 error: function (XMLHttpRequest, textStatus, errorThrown) {
					 layer.msg("对象被城管抓走了！");
				 }
			});
		}, function(){
			layer.close(layer.index);
		});
}

//批量删除
$('#delete').click(function(){
	var selRow = $("#table").bootstrapTable('getSelections');
	 var uuid = new Array();
	 if(selRow.length>0){
		 $.each(selRow, function() {
		 uuid.push(this.uuid);
		 });
	 }else {
		 layer.msg("请选择要删除的权限组！");
		 return ;
	 }
	 
	 layer.confirm('是否删除选中的权限组？', {
		  btn: ['删除','取消'] //按钮
		}, function(){
			$.ajax({
				 url: "dpgMgmt/delform",
				 dataType: "json",
				 data:{'uuid':uuid.toString()},
				 success: function (data) {
					 if(data.status=='success'){
						 layer.msg("删除成功！");
					 }else{
						 layer.msg("删除失败咯！");
					 }
					 refresh();
				 },
				 error: function (XMLHttpRequest, textStatus, errorThrown) {
					 layer.msg("对象被城管抓走了！");
				 }
			 });
		}, function(){
			layer.close(layer.index);
		});
});

function onFun(uuid,domainid){
		$.ajax({
	        type: "POST",
	        url: "gouMgmt/accept",
	        cache : false,
	        data: {"uuid":uuid,"domainid":domainid},
	        dataType: 'json',
	        success: function (message) {
	            if (message > 0) {
	                window.location.href = "gouMgmt";
	            }
	        },
	        error: function (message) {
	           alert("提交数据失败！");
	        }
	    });
}

function refresh(){
	 layer.closeAll();
	 table.bootstrapTable('refresh');
}

$("input").blur(function(){
	valid.element( this );
});


jQuery.validator.addMethod("chkGroudId", function(value, element) {  
	var dd=$('#domain_id').val();
	if(dd==""){
		layer.tips("请先选择所属域！", '#domain_id');
		return true;
	}
	
	var flag=false, d={};
    if(chk!=0){ 
    	 //新增需要校验编码是否重复
    	d={domaininfo:dd,groupid:value, chk:1}
    	$.ajax({
	   		 async:false,
	   		 url: "dpgMgmt/verifygroupid",
	   		 data:d,
	   		 dataType: "json",
	   		 success: function (data) {
	   			 if(data.status=='success'){
	   				 flag=true;
	   			 }
	   		 }
	   	});
    }else {
    	return true;
    }
    
    return this.optional(element) || flag;
}, "<i class='fa fa-times-circle'></i>所属域下已存在该组编码，不能重复");


$('#domain_id').change(function(){
	users = "";
});

