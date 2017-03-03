var chkUserStr;//校验用户是否唯一变量
var orgList;//onload机构list
var rolList;//onload角色list

$('#table').bootstrapTable({                                                                                                  
    url: 'usrMgmt/initSel',
	method: 'get', 					//请求方式（*）                                                                                                
 	toolbar: '#toolbar', //工具按钮用哪个容器                       
	striped: false, 					//是否显示行间隔色
	cache: false, 					//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）                                                               
 	pagination: true, //是否显示分页（*）                                                                                             
 	sortable: false, //是否启用排序                                                                                                 
 	sortOrder: "asc", //排序方式                                            
//	queryParams: 			oTableInit.queryParams, //传递参数（*）	
	sidePagination:  "client",   			//分页方式：client客户端分页，server服务端分页（*）                                                      
 	pageNumber:1, //初始化加载第一页，默认第一页                                                                                            
 	pageSize: 5, //每页的记录行数（*）                                                                                                 
 	pageList: [5, 10, 25, 50, 100], //可供选择的每页的行数（*）
 	search: true,
 	strictSearch: true,                                                                                                       
 	showColumns: false, //是否显示所有的列                                                                                             
 	showRefresh: false, //是否显示刷新按钮                                                                                             
 	clickToSelect: true, //是否启用点击选中行                                                                                          
 	uniqueId: "uuid", //每一行的唯一标识，一般为主键列                                                                                         
 	showToggle:false, //是否显示详细视图和列表视图的切换按钮                                                                                     
 	cardView: false, //是否显示详细视图                                                                                               
                                                                                                                              
   columns: [ {
		checkbox: true
	},{                                                                                                                      
        field: 'domain_name',                                                                                                        
        title: '域'                                                                                                        
    },{                                                                                                                      
        field: 'org_unit_desc',                                                                                                        
        title: '机 构'                                                                                                        
    },{        	
        field: 'user_id',                                                                                                      
        title: '用户编码'                                                                                                         
    }, {                                                                                                                      
        field: 'user_name',                                                                                                    
        title: '用户名称'                                                                                                         
    }, {                                                                                                                      
        field: 'role_names',                                                                                                     
        title: '角色名称'                                                                                                        
    }, {                                                                                                                      
        field: 'user_phone',                                                                                                    
        title: '电话号码'                                                                                                         
    }, {                                                                                                                      
        field: 'user_email',                                                                                                   
        title: '电子信箱'                                                                                                        
    }, {                                                                                                                         
    	field: 'uuid',                                                                                                         
    	title: '操 作',                                                                                                         
    	formatter:function(value,row,index){                                                                                  
    	var e = '<a href="#" class="btn btn-info update edit" onclick="edit(\''+ row.uuid +'\',\''
																    		   + row.user_id +'\',\''
																    		   + row.user_name +'\',\''
																    		   + row.domain_uuid +'\',\''
																    		   + row.org_uuid +'\',\''
																    		   + row.user_phone +'\',\''
																    		   + row.user_email + '\')">编辑</a> ';                                                      
    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.uuid +'\')">删除</a> ';                                                    
    	return e+d;                                                                                                           
    	}                                                                                                                     
    },]                                                                                                                       
});
//域load
$("#domain").load(
	"usrMgmt/selDmn",//url
	//{},//data 可选
	function(data){
		//解析json
		var dataObj=eval("("+data+")");
		$.each(dataObj,function(idx,item){ 
			var option = $("<option>").val(item.uuid).text(item.domain_name); 
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
			var option = $("<option>").val(item.uuid).text(item.org_unit_desc).attr("data-domain", item.domain_uuid);
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
			var option = $("<option>").val(item.uuid).text(item.role_name).attr("data-domain", item.domain_uuid);
			$("#role").append(option); 
		})
	}
);
//新增的onclick事件
$('#btn_add').on('click', function() {
	//初始化form
	var $uuid = $("#uuid").val('');
	var $scopeCode = $("#scopeCode").val('');
	$("#scopeCode").attr("disabled",false);
	var $usrName = $("#usrName").val('');
	var $domain = $("#domain option:first").prop("selected", 'selected');
	changeDomain($("#domain").val());
	var $phone = $("#phone").val('');
	var $email = $("#email").val('');
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '用户信息',
		area: ['960px', '540px'],
	});
});
//修改的onclick事件
function edit(uuid,user_id,user_name,domain_uuid,org_uuid,user_phone,user_email) {
	chkUserStr='';
	//初始化form
	var $uuid = $("#uuid").val(uuid);
	var $scopeCode = $("#scopeCode").val(user_id);
	$("#scopeCode").attr("disabled",true);
	var $usrName = $("#usrName").val(user_name);
	var $domain = $("#domain").val(domain_uuid);
	changeDomain(domain_uuid);
	var $organization = $("#organization").val(org_uuid);
	var $phone = $("#phone").val(user_phone);
	var $email = $("#email").val(user_email);
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '用户信息',
		area: ['960px', '540px'],
	});
};
//删除的onclick事件
function del(uuid) {
	//确认删除
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if(confirm(msg)){
		$.post("usrMgmt/delUsr",{"uuid":uuid});
		window.location.href='usrMgmt';	
	}else{
		return false;
	}
};

angular.module('myApp', []).controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){}
});
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
$('#sub').click(function(){
	//新增操作
	if($("#uuid").val() == ''){
		$("#form").attr("action", "usrMgmt/insUsr"); 
		$('#form').submit(function(){
			if(chkUserStr==''){
				$(this).ajaxSubmit(function(){});	
				window.location.href='usrMgmt';	
			}else{
				return false;
			}
		});
	}
	//修改操作
	if($("#uuid").val() != ''){
		$("#form").attr("action", "usrMgmt/updtUsr");
		$('#form').submit(function(){
			$(this).ajaxSubmit(function(){});	
			window.location.href='usrMgmt';	
		});
	}
});
//根据域选择机构的option的方法
var changeDomain = function(domainUUID){
	//清空所有的机构select中的option
	$("#organization").find("option").remove(); 
	//将机构的domain_uuid与域selected的域的uuid做比较，相同的插入机构的select中
	$.each(orgList,function(idx,item){
		if(item.domain_uuid==domainUUID){
			var option = $("<option>").val(item.uuid).text(item.org_unit_desc).attr("data-domain", item.domain_uuid);
			$("#organization").append(option); 
		}
	});
};
//根据域选择机构的option的事件
$('#domain').change(function(){	
	var domainUUID = $('#domain').val();
	changeDomain(domainUUID);
});
//批量删除
$('#btn_del').click(function(){
	//获得bootstraptable，如果 要解析，用json.stringify(data)方法解析。
	var selectContent = $('#table').bootstrapTable('getSelections');
	//获得UUID[]，传到后台
	var arrUUID = $.map(selectContent,function(row){return row.uuid});
	//获得userName，confirm的时候用
	var arrUser = $.map(selectContent,function(row){return row.user_name});
	//确认删除
	var msg = "请再次确认您要删除的用户：\n"+arrUser+"\n";
	if(confirm(msg)){
		$.get("usrMgmt/batchDel",{"uuid[]":arrUUID},function(data){
			if(data){
				window.location.href='usrMgmt';	
			}
		});
	}else{
		return false;
	}
});