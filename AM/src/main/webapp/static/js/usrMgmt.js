$('#table').bootstrapTable({                                                                                                  
    url: 'static/js/demo/3-user.json',                                                                                                
 	toolbar: '#toolbar', //工具按钮用哪个容器                                                                                          
 	striped: true, //是否显示行间隔色                                                                                                 
 	pagination: true, //是否显示分页（*）                                                                                             
 	sortable: false, //是否启用排序                                                                                                 
 	sortOrder: "asc", //排序方式                                                                                                  
 	pageNumber:1, //初始化加载第一页，默认第一页                                                                                            
 	pageSize: 5, //每页的记录行数（*）                                                                                                 
 	pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）                                                                              
 	strictSearch: true,                                                                                                       
 	showColumns: false, //是否显示所有的列                                                                                             
 	showRefresh: false, //是否显示刷新按钮                                                                                             
 	clickToSelect: false, //是否启用点击选中行                                                                                          
 	uniqueId: "ID", //每一行的唯一标识，一般为主键列                                                                                         
 	showToggle:false, //是否显示详细视图和列表视图的切换按钮                                                                                     
 	cardView: false, //是否显示详细视图                                                                                               
                                                                                                                              
   columns: [ {                                                                                                                      
        field: 'ORG_UUID',                                                                                                        
        title: '机构编码'                                                                                                        
    },{        	
        field: 'USER_ID',                                                                                                      
        title: '用户编码'                                                                                                         
    }, {                                                                                                                      
        field: 'USER_NAME',                                                                                                    
        title: '用户名称'                                                                                                         
    }, {                                                                                                                      
        field: 'ROLES',                                                                                                     
        title: '角色名称'                                                                                                        
    }, {                                                                                                                      
        field: 'USER_PHONE',                                                                                                    
        title: '电话号码'                                                                                                         
    }, {                                                                                                                      
        field: 'USER_EMAIL',                                                                                                   
        title: '电子信箱'                                                                                                        
    }                                                                                                                            
    ,                                                                                                                         
    {                                                                                                                         
    	field: 'opt',                                                                                                         
    	title: '操 作',                                                                                                         
    	formatter:function(value,row,index){                                                                                  
    	var e = '<a href="#" class="btn btn-info update" onclick="edit(\''+ row.id + '\')">编辑</a> ';                                                      
    	var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';                                                        
    	var f = '<a href="#" class="btn btn-primary create" onclick="del(\''+ row.id +'\')">角色</a> ';                                                   
    	return e+d+f;                                                                                                           
    	}                                                                                                                     
    },]                                                                                                                       
});                                                                                                                           
