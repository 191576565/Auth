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
	sidePagination: 		"client",   			//分页方式：client客户端分页，server服务端分页（*）                                                      
 	pageNumber:1, //初始化加载第一页，默认第一页                                                                                            
 	pageSize: 5, //每页的记录行数（*）                                                                                                 
 	pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）                                                                              
 	strictSearch: true,                                                                                                       
 	showColumns: false, //是否显示所有的列                                                                                             
 	showRefresh: false, //是否显示刷新按钮                                                                                             
 	clickToSelect: true, //是否启用点击选中行                                                                                          
 	uniqueId: "ID", //每一行的唯一标识，一般为主键列                                                                                         
 	showToggle:false, //是否显示详细视图和列表视图的切换按钮                                                                                     
 	cardView: false, //是否显示详细视图                                                                                               
                                                                                                                              
   columns: [ {                                                                                                                      
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
    }                                                                                                                            
    ,                                                                                                                         
    {                                                                                                                         
    	field: 'opt',                                                                                                         
    	title: '操 作',                                                                                                         
    	formatter:function(value,row,index){                                                                                  
    	var e = '<a href="#" class="btn btn-info update edit" onclick="edit(\''+ row.id + '\')">编辑</a> ';                                                      
    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';                                                        
    	var f = '<a href="#" class="btn btn-primary create" onclick="del(\''+ row.id +'\')">角色</a> ';                                                   
    	return e+d+f;                                                                                                           
    	}                                                                                                                     
    },]                                                                                                                       
});

$('#btn_add').on('click', function() {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '用户信息',
		area: ['960px', '540px'],
	});
});

$('#table').on('click', '.edit', function(value,row,index) {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '用户信息',
		area: ['960px', '540px'],
	});
	return false;
});

angular.module('myApp', [])
	.controller('SignUpController',function($scope){
		$scope.userdata = {};
		$scope.submitForm = function(){
			console.log($scope.userdata)
			if($scope.signUpForm.$invalid){
				alert('请检查您的信息!');
			}
		}
	});
//校验用户是否唯一变量
var chkUserStr;
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
/*
$("#form").submit(function(){
	$.ajax({
		type:"post",
		url:"usrMgmt/insUsr",
		data: $("#form").serialize(),
		success: function(data) {
			alert("data");
        }
	})
});*/

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
		$("#form").attr("action", "sysMgmt/update");
		$('#form').submit(function(){
			$(this).ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "true"){
					window.location.href='usrMgmt';
					alert('修改成功');
				}else{
					alert('修改失败!');
				}
			});
			return false;//阻止表单默认提交
		});
	}
});

