var chkUserStr;//校验用户是否唯一变量
var orgList;//onload机构list
var rolList;//onload角色list
function inittable(){
	$('#table').bootstrapTable('destroy');
	$('#table').bootstrapTable({                                                                                                  
	    url: 'usrMgmt/initSel',
		method: 'get', 					//请求方式（*）                                                                                                
		striped: true, 					//是否显示行间隔色
		cache: false, 					//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）                                                               
	 	pagination: true, //是否显示分页（*）                                                                                             
	 	sortable: false, //是否启用排序     
	 	queryParams: function queryParams() {     //设置查询参数  
	        var param = {    
		        sendParam : $("#iptSearch").val(),
		        send : ''
	        };    
	   　　	return param;                   
	     },	
	    queryParamsType: '',
	 	sortOrder: "asc", //排序方式                                            
	//	queryParams: 			oTableInit.queryParams, //传递参数（*）	
		sidePagination:  "client",   			//分页方式：client客户端分页，server服务端分页（*）                                                      
	 	pageNumber:1, //初始化加载第一页，默认第一页                                                                                            
	 	pageSize: 10, //每页的记录行数（*）  
	 	pageList: [10], 		//可供选择的每页的行数（*）
	 	search: false,
	 	strictSearch: false,                                                                                                       
	 	showColumns: false, //是否显示所有的列                                                                                             
	 	showRefresh: false, //是否显示刷新按钮                                                                                             
	 	clickToSelect: false, //是否启用点击选中行                                                                                          
	 	uniqueId: "uuid", //每一行的唯一标识，一般为主键列                                                                                         
	 	showToggle:false, //是否显示详细视图和列表视图的切换按钮                                                                                     
	 	cardView: false, //是否显示详细视图                                                                                               
	                                                                                                                              
	   columns: [ {
			checkbox: true
		},{                                                                                                                      
	        field: 'domain_name',       
	        align: 'center',
	        title: '域名称'                                                                                                        
	    },{                                                                                                                      
	        field: 'org_unit_desc',     
	        align: 'center',
	        title: '机构名称'                                                                                                        
	    },{        	
	        field: 'user_id',        
	        align: 'center',
	        title: '用户编码'                                                                                                         
	    }, {                                                                                                                      
	        field: 'user_name',       
	        align: 'center',
	        title: '用户名称'                                                                                                         
	    }, {                                                                                                                      
	        field: 'role_names',      
	        align: 'center',
	        title: '角色名称'                                                                                                        
	    }, {                                                                                                                      
	        field: 'user_phone',      
	        align: 'center',
	        title: '电话号码'                                                                                                         
	    }, {                                                                                                                      
	        field: 'user_email',      
	        align: 'center',
	        title: '电子信箱'                                                                                                        
	    }, {                                                                                                                         
	    	field: 'uuid',                                                                                                         
	    	title: '操 作',
	    	width: "130px",
	    	formatter:function(value,row,index){                                                                                  
	    	var e = '<a href="#" class="btn btn-info update edit" onclick="edit(\''+ row.uuid +'\',\''
																	    		   + row.user_id +'\',\''
																	    		   + row.user_name +'\',\''
																	    		   + row.domain_uuid +'\',\''
																	    		   + row.org_uuid +'\',\''
																	    		   + row.role_uuids +'\',\''
																	    		   + row.user_phone +'\',\''
																	    		   + row.user_email + '\')">编辑</a> ';                                                      
	    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.uuid +'\',\''+row.user_id +'\',\''+ row.user_name +'\')">删除</a> ';                                                    
	    	return e+d;                                                                                                           
	    	}                                                                                                                     
	    },]                                                                                                                       
	});
};
//域load
$("#domain").load(
	"usrMgmt/selDmn",//url
	//{},//data 可选
	function(data){
		//解析json
		var dataObj=eval("("+data+")");
		$.each(dataObj,function(idx,item){ 
			var option = $("<option></option>").val(item.uuid).text(item.domain_name); 
			$("#domain").append(option); 
		})
	}
);
//机构load
$("#organization").load(
	"usrMgmt/selOrg",//url
	//{},//data 可选
	function(data){
		//解析json，将解析后的json放入orgList中
		orgList=eval("("+data+")");
		$.each(orgList,function(idx,item){ 
			var option = $("<option></option>").val(item.uuid).text(item.org_unit_desc);
			$("#organization").append(option); 
		})
	}
);
//角色load
$("#role").load(
	"usrMgmt/selRol",//url
	//{},//data 可选
	function(data){
		//解析json，将解析后的json放入orgList中
		rolList=eval("("+data+")");
		$.each(rolList,function(idx,item){
			var option = $("<option></option>").val(item.role_uuid).text(item.role_name);
			$("#role").append(option); 
		});
		$("#role").multiselect({
	        enableFiltering: true,
	        filterPlaceholder:'搜索',
	        nSelectedText:'项被选中',
	        includeSelectAllOption:true,
	        selectAllText:'全选/取消全选',
	        allSelectedText:'已选中所有角色',
			nonSelectedText: '请选择角色',
			buttonWidth: '913px',
			enableCaseInsensitiveFiltering: true
		});
	}
	
);
//根据域选择机构的option的方法
var changeDomain = function(domainUUID){
	//清空所有的机构select中的option
	$("#organization").find("option").remove(); 
	//将机构的domain_uuid与域selected的域的uuid做比较，相同的插入机构的select中
	$.each(orgList,function(idx,item){
		if(item.domain_uuid==domainUUID){
			var option = $("<option></option>").val(item.uuid).text(item.org_unit_desc);
			$("#organization").append(option); 
		}
	});
	//清空所有的角色select中的option
	$("#role").find("option").remove(); 
	//将角色的domain_uuid与域selected的域的uuid做比较，相同的插入角色的select中
	$.each(rolList,function(idx,item){
		if(item.domain_uuid==domainUUID){
			var option = $("<option></option>").val(item.role_uuid).text(item.role_name);
			$("#role").append(option); 
		}
	});
	$("#role").multiselect('rebuild');
};
//根据域选择机构的option的事件
$('#domain').change(function(){	
	var domainUUID = $('#domain').val();
	changeDomain(domainUUID);
});
//新增的onclick事件
$('#btn_add').on('click', function() {
	//初始化form
	$("#uuid").val('');
	$("#scopeCode").val('');
	$("#scopeCode").attr("disabled",false);
	$("#usrName").val('');
	$("#domain option:first").prop("selected", 'selected');
	changeDomain($("#domain").val());
	$("#phone").val('');
	$("#email").val('');
	$("#chkUserError").html('');
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '用户信息',
		area: ['960px', '540px'],
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "usrMgmt/insUsr"); 
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) === "true"){
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
					return;
				}
				$.each(resultJson, function(i, item){
					if(item === "roleIsNull"){
						layer.msg('请为该用户分配角色');
					}
				})
			});
		},
		btn2: function(index, layero){	
		}
	});
});
//修改的onclick事件
function edit(uuid,user_id,user_name,domain_uuid,org_uuid,role_uuids,user_phone,user_email) {
	chkUserStr='';
	//初始化form
	$("#uuid").val(uuid);
	$("#scopeCode").val(user_id);
	$("#scopeCode").attr("disabled",true);
	$("#usrName").val(user_name);
	$("#domain").val(domain_uuid);
	changeDomain(domain_uuid);
	$("#organization").val(org_uuid);
	$('#role').multiselect('select', role_uuids.split(","));
	if(user_phone == "null"){
		user_phone = "";
	}
	if(user_email == "null"){
		user_email = "";
	}
	$("#phone").val(user_phone);
	$("#email").val(user_email);
	$("#chkUserError").html('');
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '用户信息',
		area: ['960px', '540px'],
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "usrMgmt/updtUsr"); 
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "false"){
					layer.msg('域编码/域名不能重复');
				}
				if(JSON.stringify(resultJson) == "true"){
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
					return;
				}
				$.each(resultJson, function(i, item){
					if(item === "roleIsNull"){
						layer.msg('请为该用户分配角色');
					}
				})
			});
		},
		btn2: function(index, layero){	
		}
	});
};
//删除的onclick事件
function del(uuid,user_id,user_name) {
	layer.confirm('是否删除该用户信息？', 
			{
		　　　　　　title:'提示信息',
			  btn: ['删除','取消'] //按钮
			}, 
			function(){
				$.post('usrMgmt/delUsr?uuid='+uuid+'&user_id='+user_id+'&user_name='+user_name, function(d){
					if(d){
						layer.msg('删除成功');
					}else {
						layer.msg('删除失败，检查是否已关联机构');
					}
					$('#table').bootstrapTable('refresh', {silent: true});
				});
			}, 
			function(){
				layer.closeAll();
			}
		);
};

//用户唯一校验
$("#scopeCode").blur(function(){
	$.ajax({
		type:"post",
		url:"usrMgmt/chkUserId",
		data: {"scopeCode":$("#scopeCode").val()},
		success: function(data) {
			chkUserStr = data.chkUserId?'':'该用户已存在';
			 $("#chkUserError").html(chkUserStr);
        }
	})
});

//批量删除
$('#btn_del').click(function(){
	//获得bootstraptable，如果 要解析，用json.stringify(data)方法解析。
	var selectContent = $('#table').bootstrapTable('getSelections');
	//获得UUID[]，传到后台
	var arrUUID = $.map(selectContent,function(row){return row.uuid});
	//获得userName，confirm的时候用
	var arrUser = $.map(selectContent,function(row){return row.user_name});
	if(arrUUID.length == 0){
		layer.msg('请选择要删除的用户信息');
	}else{
		layer.confirm('是否删除所选用户信息？', 
				{
			　　　　　　title:'提示信息',
				  btn: ['删除','取消'] //按钮
				}, 
				function(){
					$.get("usrMgmt/batchDel",{"uuid[]":arrUUID,"arrUser[]":arrUser},function(data){
						if(data){
							layer.msg('删除成功')
						}
						$('#table').bootstrapTable('refresh', {silent: true});
					});
				}, 
				function(){
					layer.closeAll();
				}
			);
	}
});

//批量重置密码
$('#btn_reset').click(function(){
	//获得bootstraptable，如果 要解析，用json.stringify(data)方法解析。
	var selectContent = $('#table').bootstrapTable('getSelections');
	//获得UUID[]，传到后台
	var arrUUID = $.map(selectContent,function(row){return row.uuid});
	//获得userName，confirm的时候用
	var arrUser = $.map(selectContent,function(row){return row.user_name});
	//确认删除
	if(0 == arrUUID.length){
		layer.msg('请选择要重置的用户');
	}else{
		layer.confirm('是否重置选中用户的密码？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.post('usrMgmt/batchReset', {"uuid[]":arrUUID},function(d){
					if(d){
						layer.msg('重置密码成功');
					}else {
						layer.msg('重置密码失败');
					}
				});
				$('#table').bootstrapTable('refresh', {silent: true});
			}, function(){
				layer.close(layer.index);
			});
	}
	
});

function validate(){
	//非空验证
	var flag = true;
	if($('#organization')[0].options.length == 0){
		layer.msg($('#organization').attr('nullName')+"不能为空");
		flag = false;
		return false;
	}
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
